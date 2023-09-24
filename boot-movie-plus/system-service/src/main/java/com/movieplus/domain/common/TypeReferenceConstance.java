package com.movieplus.domain.common;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.movieplus.domain.common.dto.ShowTimeDto;
import com.movieplus.domain.dto.SiteInfoDTO;

public class TypeReferenceConstance {

	public static TypeReference<SiteInfoDTO> siteType = new TypeReference<SiteInfoDTO>() {};
	public static TypeReference<List<SiteInfoDTO>> siteListType = new TypeReference<List<SiteInfoDTO>>() {};
	public static TypeReference<Map<String, List<ShowTimeDto>>> commonShowTime = new TypeReference<Map<String,List<ShowTimeDto>>>() {};
}
