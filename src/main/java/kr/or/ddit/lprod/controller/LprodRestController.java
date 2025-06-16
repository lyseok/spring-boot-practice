package kr.or.ddit.lprod.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.vo.LprodVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping({"/rest/lprod", "/ajax/lprod"})
public class LprodRestController{
	private final LprodService service;
	
	@GetMapping
	public List<LprodVO> lprodList(Authentication authentication) {
		log.info("인증 객체 : {}", authentication);
		log.info("authentication : {}", authentication.getAuthorities());
		return service.readLprodList();
	}
}
