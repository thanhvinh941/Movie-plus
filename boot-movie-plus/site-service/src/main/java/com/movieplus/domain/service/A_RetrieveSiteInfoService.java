package com.movieplus.domain.service;

import java.beans.PropertyDescriptor;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.controller.external.operator.RetrieveSiteInfoDetailController.RetrieveSiteInfoRequest;
import com.movieplus.controller.external.operator.RetrieveSiteInfoDetailController.RetrieveSiteInfoResponse;
import com.movieplus.domain.entity.RoomInfo;
import com.movieplus.domain.entity.SiteInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class A_RetrieveSiteInfoService {

	private final ObjectMapper objectMapper;
	private final CustomRepository customRepository;

	public void execute(RetrieveSiteInfoRequest request, RetrieveSiteInfoResponse response) throws Exception {
		List<?> siteInfoResults = customRepository.selectByCondition(SiteInfo.class,
				String.format("id = '%s'", request.getId()));
		List<?> roomInfoResults = customRepository.selectByCondition(RoomInfo.class,
				String.format("site_id = '%s'", request.getId()));

		List<RetrieveSiteInfoResponse.RoomInfo> roomInfoResponse = roomInfoResults.stream().map(ri -> {
			RetrieveSiteInfoResponse.RoomInfo roomInfo = new RetrieveSiteInfoResponse.RoomInfo();
			Map<String, Object> roomInfoMap = objectMapper.convertValue(ri, new TypeReference<Map<String, Object>>() {});
			for (Map.Entry<String, Object> entry : roomInfoMap.entrySet()) {
				copyProperty(entry, roomInfo);
			}
			return roomInfo;
		}).toList();
		Map<String, Object> siteInfoResultMap = objectMapper.convertValue(siteInfoResults.get(0), Map.class);
		for (Map.Entry<String, Object> entry : siteInfoResultMap.entrySet()) {
			copyProperty(entry, response);
		}
		response.setRoomInfos(roomInfoResponse);
	}

	public void copyProperty(Map.Entry<String, Object> entry, Object trg) {
		try {			
			BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);
			Class<?> propertyType = trgWrap.getPropertyType(entry.getKey());
			Object value = entry.getValue();
			if (propertyType != null && propertyType.equals(LocalDateTime.class) && (entry.getValue() instanceof Timestamp)) {
				value = LocalDateTime.ofInstant(((Timestamp) entry.getValue()).toInstant(), ZoneOffset.ofHours(0));
			}
			trgWrap.setPropertyValue(entry.getKey(), value);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void copyProperties(Object src, Object trg, Iterable<String> props) {

		BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(src);
		BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);

		props.forEach(p -> trgWrap.setPropertyValue(p, srcWrap.getPropertyValue(p)));

	}

	public static void copyProperties2(Object src, Object trg, List<String> list) {
		String[] excludedProperties = Arrays.stream(BeanUtils.getPropertyDescriptors(src.getClass()))
				.map(PropertyDescriptor::getName).filter(name -> !list.contains(name)).toArray(String[]::new);

		BeanUtils.copyProperties(src, trg, excludedProperties);
	}

}
