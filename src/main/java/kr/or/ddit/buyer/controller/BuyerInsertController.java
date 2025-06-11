package kr.or.ddit.buyer.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import lombok.RequiredArgsConstructor;


/**
 *  /buyer/buyerInsert.do(GET, POST)
 */
@Controller
@RequestMapping("/buyer/buyerInsert.do")
@RequiredArgsConstructor
public class BuyerInsertController {
	private final BuyerService service;
	
	static final String MODEL_NAME = "buyer";
	
	@ModelAttribute(MODEL_NAME)
	public BuyerVO buyer() {
		return new BuyerVO();
	}
	
	@GetMapping
	public String formUI() {
		return "buyer/buyerForm";
	}
	
	@PostMapping
	public String insertBuyer(
		@Validated(InsertGroup.class) @ModelAttribute(MODEL_NAME) BuyerVO buyer, BindingResult errors
		, RedirectAttributes redirectAttributes
	) {
		String lvn;
		if(errors.hasErrors()) {
			redirectAttributes.addFlashAttribute(MODEL_NAME, buyer);
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODEL_NAME;
			redirectAttributes.addFlashAttribute(errorsName, errors);
			lvn = "redirect:/buyer/buyerInsert.do";
		} else {		
			service.createBuyer(buyer);
			lvn = "redirect:/buyer/buyerList.do";
		}
		return lvn;
	}
	

}
