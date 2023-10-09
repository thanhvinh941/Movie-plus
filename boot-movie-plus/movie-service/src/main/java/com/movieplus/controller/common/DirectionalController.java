package com.movieplus.controller.common;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.CustomRepository;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DirectionalController {

	private final String[] logTitle = { "Directional_Controller" };
	private final MessageManager messageManager;
	private final CustomRepository customRepository;

	@Getter
	@Setter
	public static class GetDirectionalRequest {
		private Integer limit;
		private Integer offset;
		private String tableName;
		private String conditionStr;
		private boolean isForUpdate;
		private List<String> listFields;
		private Map<String, String> orderBys;
	}
	
	@Getter
	@Setter
	public static class EntryDirectionalRequest{
		private String tableName;
		private Map<String, Object> records;
	}

	@RequestMapping(path = "/getDirectionalValue", method = RequestMethod.POST)
	@ResponseBody
	public String getDirectionalValue(@RequestBody String requestStr) {
		GetDirectionalRequest request = new GetDirectionalRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<GetDirectionalRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil
					.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}

		try {
			List<?> resultList = customRepository.selectByCondition(request.getTableName(), request.getConditionStr(),
					request.getListFields(), request.getOrderBys(), request.getLimit(), request.offset,
					request.isForUpdate());
			return GeneratorCommonUtil.getResponseBodySuccess(resultList);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@RequestMapping(path = "/entryDirectionalValue", method = RequestMethod.POST)
	@ResponseBody
	public String entryDirectionalValue(@RequestBody String requestStr) {
		EntryDirectionalRequest request = new EntryDirectionalRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<EntryDirectionalRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil
					.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {
			String result = customRepository.insertRecords(request.getTableName(), request.records);
			return GeneratorCommonUtil.getResponseBodySuccess(result);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	
	}
}
