package com.movieplus.config.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.config.common.util.GeneratorUtil.ExternalAPI;
import com.movieplus.config.common.util.GeneratorUtil.ExternalAPI.ExternalApiResponse;
import com.movieplus.domain.common.MessageManager;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class DynamicMasterEntityController {

	private final ObjectMapper objectMapper;
	private final MessageManager messageManager;
	private final CustomRepository customRepository;

	@Getter
	@Setter
	public static class GetDynamicMasterEntityRequest {
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
	public static class EntryDynamicMasterEntityRequest {
		private String tableName;
		private Map<String, Object> records;
	}

	@Getter
	@Setter
	public static class ChangeDynamicMasterEntityRequest {
		private String tableName;
		private Map<String, Object> records;
		private String id;
	}

	@Getter
	@Setter
	public static class UDeleteDynamicMasterEntityRequest {
		private String tableName;
		private String id;
	}

	@RequestMapping(path = "/getDynamicMasterEntity", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ExternalApiResponse<List<?>>> getDynamicMasterEntity(@RequestBody String requestStr) {
		final String[] logTitle = { "getDynamicMasterEntity" };
		GetDynamicMasterEntityRequest request = new GetDynamicMasterEntityRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<GetDynamicMasterEntityRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			List<?> resultList = customRepository.selectByCondition(request.getTableName(), request.getConditionStr(),
					request.getListFields(), request.getOrderBys(), request.getLimit(), request.offset,
					request.isForUpdate());
			return GeneratorUtil.ExternalAPI.createSuccessResponse(resultList);
		} catch (ClientException e) {
			log.error("{} ClientException fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(e.getMessage()));
		} catch (Exception e) {
			log.error("{} Exception fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorServerResponse(List.of(e.getMessage()));
		}
	}

	@RequestMapping(path = "/entryDynamicMasterEntity", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ExternalApiResponse<String>> entryDynamicMasterEntity(@RequestBody String requestStr) {
		final String[] logTitle = { "entryDynamicMasterEntity" };
		EntryDynamicMasterEntityRequest request = new EntryDynamicMasterEntityRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<EntryDynamicMasterEntityRequest>() {
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

	@RequestMapping(path = "/changeDynamicMasterEntity", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ExternalApiResponse<String>> changeDynamicMasterEntity(@RequestBody String requestStr) {
		final String[] logTitle = { "changeDynamicMasterEntity" };
		ChangeDynamicMasterEntityRequest request = new ChangeDynamicMasterEntityRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<ChangeDynamicMasterEntityRequest>() {
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

	@RequestMapping(path = "/deleteDynamicMasterEntity", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ExternalApiResponse<Boolean>> deleteDynamicMasterEntity(@RequestBody String requestStr) {
		final String[] logTitle = { "deleteDynamicMasterEntity" };
		UDeleteDynamicMasterEntityRequest request = new UDeleteDynamicMasterEntityRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<UDeleteDynamicMasterEntityRequest>() {
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
