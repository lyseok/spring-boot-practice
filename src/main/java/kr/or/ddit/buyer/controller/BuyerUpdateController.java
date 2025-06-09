package kr.or.ddit.buyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.BuyerVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/buyer/buyerUpdate.do")
@RequiredArgsConstructor
public class BuyerUpdateController{
	private final BuyerService service;
	private ErrorsUtils errorsUtils;
	
	static final String MODEL_NAME = "buyer";
	
	@Autowired(required = false)
	public void setErrorsUtils(ErrorsUtils errorsUtils) {
		this.errorsUtils = errorsUtils;
	}
	
	@GetMapping
	public String editForm(String what, Model model) {
		if(!model.containsAttribute(MODEL_NAME)) {
			BuyerVO buyer = service.readBuyer(what).get();
			model.addAttribute(MODEL_NAME, buyer);			
		}
		return "buyer/buyerEdit";
	}
	
	@PostMapping
	public String formProcess(
		String what	
		, @Validated(UpdateGroup.class) @ModelAttribute(MODEL_NAME) BuyerVO buyer, BindingResult errors
		, RedirectAttributes redirectAttributes
	) {
		String lvn;
		if(!errors.hasErrors()) {
			service.modifyBuyer(buyer);
			lvn = "redirect:/buyer/buyerDetail.do?what=" + buyer.getBuyerId();
		} else {
			redirectAttributes.addFlashAttribute(MODEL_NAME,redirectAttributes);
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			lvn = "redirect:/buyer/buyerUpdate.do?what=" + what;
		}
		return lvn;
	}

	
}
