package com.movieplus.domain.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.domain.common.MovieConstance.KeyTypeReturn;
import com.movieplus.domain.common.dto.ShowTimeDto;
import com.movieplus.domain.entity.RoomInfo;
import com.movieplus.domain.entity.ShowTime;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.RoomInfoService;
import com.movieplus.domain.service.ShowTimeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetShowTimeDetailInfoService {

	private String CLASS_NAME = "GetShowTimeDetailInfoService";
	
	private final ShowTimeService showTimeService;
	private final RoomInfoService roomInfoService;
	
	public Map<String, List<ShowTimeDto>> getShowTimeDetail(String siteId, String movieId, KeyTypeReturn keyTypeReturn){
		List<ShowTime> showTimes = getShowTime(siteId, movieId);
		List<String> roomIds = showTimes.stream().map(ShowTime::getRoomId).toList();
		List<RoomInfo> roomInfos = getRoomInfo(roomIds);
		Map<String, RoomInfo> roomResourceMap = roomInfos.stream().collect(Collectors.toMap(RoomInfo::getId, Function.identity()));
		
		return showTimes.stream()
				.collect(Collectors.groupingBy(
						keyTypeReturn.equals(KeyTypeReturn.MOVIE) ? ShowTime::getMovieId : ShowTime::getSiteId,
						Collectors.mapping(stl -> {
							return getShowTimeResponse(stl, roomResourceMap);
						}, Collectors.toList())));
	}
	
	private ShowTimeDto getShowTimeResponse(ShowTime showTimeResouce, Map<String, RoomInfo> roomResourceMap) {
		ShowTimeDto showTimeDto =  new ShowTimeDto();
		showTimeDto.setEndTime(showTimeResouce.getEndTime());
		showTimeDto.setStartTime(showTimeResouce.getStartTime());
		showTimeDto.setId(showTimeResouce.getId());
		
		RoomInfo roomInfoResource = roomResourceMap.getOrDefault(showTimeResouce.getRoomId(), null);
		ShowTimeDto.RoomInfo room = new ShowTimeDto.RoomInfo();
		BeanUtils.copyProperties(roomInfoResource, room);
		showTimeDto.setRoomInfo(room);
		
		return showTimeDto;
	}

	private List<RoomInfo> getRoomInfo(List<String> roomIds) {
		if(roomIds.isEmpty()) {
			return List.of();
		}
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = String.format(" id in (%s)", GeneratorCommonUtil.joiningListString(roomIds));
		apiRequest.setConditionStr(conditionStr);
		
		try {
			return roomInfoService.getRoomInfo(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getRoomInfo", CLASS_NAME, e);
		}
		return List.of();
	}

	private List<ShowTime> getShowTime(String siteId, String movieId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		LocalDate nowStamp = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String nowStr = nowStamp.format(dateTimeFormatter);
		
		String conditionStr = "";
		if(!Objects.isNull(siteId)) {
			conditionStr += String.format(" site_id = '%s' AND", siteId);			
		}
		
		if(!Objects.isNull(movieId)) {			
			conditionStr += String.format(" movie_id = '%s' AND", movieId);
		}
		conditionStr += String.format(" start_time >= '%s'", nowStr);
		apiRequest.setConditionStr(conditionStr);
		
		try {
			return showTimeService.getShowTime(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getShowTime", CLASS_NAME, e);
		}
		return List.of();
	}
}
