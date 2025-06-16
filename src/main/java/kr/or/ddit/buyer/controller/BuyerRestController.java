package kr.or.ddit.buyer.controller;

import java.net.Authenticator;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping({"/rest/buyer", "/ajax/buyer"})
public class BuyerRestController {
	private final BuyerService service;
	
	@GetMapping
	public List<BuyerVO> getBuyerList(Authentication authentication) {
		log.info("authentication : {}", authentication.getAuthorities());
		return service.readBuyerList();
	}
	@GetMapping("/{what}")
	public BuyerVO detail(
		@PathVariable("what") String what	
	) {
		return service.readBuyer(what).get();
	}
}
