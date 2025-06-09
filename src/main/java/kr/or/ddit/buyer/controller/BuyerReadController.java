package kr.or.ddit.buyer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/buyer")
public class BuyerReadController {
	private BuyerService service;
	
	@Autowired
	public void setService(BuyerService service) {
		this.service = service;
	}
	
	@GetMapping("buyerList.do")
	public String listHandler(Model model) {
		List<BuyerVO> buyerList = service.readBuyerList();
		model.addAttribute("buyerList", buyerList);
		return "buyer/buyerList";
	}
	
	// logical view name이 명시되지 않은 경우,
	// RequestTOViewTranslator가 작동하게 됨.
	// ex) request url 이 lvn 으로 반영됨. 
	@GetMapping("buyerDetail.do")
	public void detailHandler(
		String what
		, Model model
	) {
		BuyerVO buyer = service.readBuyer(what).get();
		
		model.addAttribute("buyer", buyer);
//		return "buyer/buyerDetail";
	}
}
