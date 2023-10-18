package com.movieplus.config.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.domain.common.MessageManager;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class DynamicEntityController {
	private final ObjectMapper objectMapper;
	private final MessageManager messageManager;
	private final CustomRepository customRepository;

	@Getter
	@Setter
	public static class GetDynamicEntityRequest {
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
	public static class EntryDynamicEntityRequest {
		private String tableName;
		private Map<String, Object> records;
	}

	@Getter
	@Setter
	public static class ChangeDynamicEntityRequest {
		private String tableName;
		private Map<String, Object> records;
		private String id;
	}

	@Getter
	@Setter
	public static class UDeleteDynamicEntityRequest {
		private String tableName;
		private String id;
	}

	@RequestMapping(path = "/getDynamicEntity", method = RequestMethod.POST)
	@ResponseBody
	public String getDynamicEntity(@RequestBody String requestStr) {
		final String[] logTitle = { "getDynamicEntity" };
		GetDynamicEntityRequest request = new GetDynamicEntityRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<GetDynamicEntityRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(errorMessage));
		}

		try {
			List<?> resultList = customRepository.selectByCondition(request.getTableName(), request.getConditionStr(),
					request.getListFields(), request.getOrderBys(), request.getLimit(), request.offset,
					request.isForUpdate());
			return GeneratorUtil.InternalAPI.getResponseBodySuccess(resultList);
		} catch (ClientException e) {
			log.error("{} ClientException fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(e.getMessage()));
		} catch (Exception e) {
			log.error("{} Exception fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(e.getMessage()));
		}
	}

	@RequestMapping(path = "/entryDynamicEntity", method = RequestMethod.POST)
	public ResponseEntity<?> entryDynamicEntity(@RequestBody String requestStr) throws JsonProcessingException {
		final String[] logTitle = { "entryDynamicEntity" };
		EntryDynamicEntityRequest request = new EntryDynamicEntityRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<EntryDynamicEntityRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			String result = customRepository.insertRecords(request.getTableName(), request.records);
			return GeneratorUtil.ExternalAPI.createSuccessResponse(result);
		} catch (ClientException e) {
			log.error("{} ClientException fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(e.getMessage()));
		} catch (Exception e) {
			log.error("{} Exception fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorServerResponse(List.of(e.getMessage()));
		}

	}

	@RequestMapping(path = "/changeDynamicEntity", method = RequestMethod.POST)
	public ResponseEntity<?> changeDynamicEntity(@RequestBody String requestStr) throws JsonProcessingException {
		final String[] logTitle = { "changeDynamicEntity" };
		ChangeDynamicEntityRequest request = new ChangeDynamicEntityRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<ChangeDynamicEntityRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			String result = customRepository.updateRecords(request.getTableName(), request.records, request.getId());
			return GeneratorUtil.ExternalAPI.createSuccessResponse(result);
		} catch (ClientException e) {
			log.error("{} ClientException fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(e.getMessage()));
		} catch (Exception e) {
			log.error("{} Exception fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorServerResponse(List.of(e.getMessage()));
		}
	}

	@RequestMapping(path = "/deleteDynamicEntity", method = RequestMethod.POST)
	public ResponseEntity<?> deleteDynamicEntity(@RequestBody String requestStr) throws JsonProcessingException {
		final String[] logTitle = { "deleteDynamicEntity" };
		UDeleteDynamicEntityRequest request = new UDeleteDynamicEntityRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<UDeleteDynamicEntityRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			Boolean result = customRepository.deleteById(request.getTableName(), request.getId());
			return GeneratorUtil.ExternalAPI.createSuccessResponse(result);
		} catch (ClientException e) {
			log.error("{} ClientException fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(e.getMessage()));
		} catch (Exception e) {
			log.error("{} Exception fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorServerResponse(List.of(e.getMessage()));
		}
	}
}
