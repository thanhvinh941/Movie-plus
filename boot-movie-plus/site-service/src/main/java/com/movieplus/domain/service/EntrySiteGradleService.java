package com.movieplus.domain.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.movieplus.controller.external.operator.EntrySeatGradleController.EntrySeatGradleRequest;
import com.movieplus.controller.external.operator.EntrySiteGradleController.EntrySiteGradleRequest;
import com.movieplus.domain.entity.SiteGradle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntrySiteGradleService {

	private final JpaRepository<SiteGradle, String> repository;

	public List<String> execute(EntrySiteGradleRequest request) {
		List<SiteGradle> siteGradles = request.getSiteGradeName().stream().map(mgn -> {
			SiteGradle siteGradle = new SiteGradle();
			siteGradle.setMemberVisibleFlg(1);
			siteGradle.setSiteGradeName(mgn);
			return siteGradle;
		}).toList();

		List<SiteGradle> gradles = repository.saveAll(siteGradles);

		return gradles.stream()
				.map(SiteGradle::getId)
				.toList();
	}

}
