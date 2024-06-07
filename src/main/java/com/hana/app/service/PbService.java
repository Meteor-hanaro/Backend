package com.hana.app.service;

import org.springframework.stereotype.Service;

import com.hana.app.data.entity.Pb;
import com.hana.app.repository.PbRepository;
import com.hana.exception.NotFoundException;
import com.hana.response.ErrorType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PbService {

	private final PbRepository pbRepository;

	public Pb findByPbId(Long pbId) {
		Pb pb = pbRepository.findById(pbId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND));
		return pb;
	}
}
