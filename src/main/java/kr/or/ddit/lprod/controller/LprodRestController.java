package kr.or.ddit.lprod.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.vo.LprodVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/lprod")
public class LprodRestController{
	private final LprodService service;
	
	@GetMapping
	public List<LprodVO> lprodList() {
		return service.readLprodList();
	}
}
