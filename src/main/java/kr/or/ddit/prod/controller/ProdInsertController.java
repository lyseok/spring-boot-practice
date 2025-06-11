package kr.or.ddit.prod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController {
	private final ProdService service;
	private final ErrorsUtils errorsUtils;

	private static final String MODEL_NAME = "prod";
	
	@ModelAttribute(MODEL_NAME)
	public ProdVO prod() {
		return new ProdVO();
	}
	@GetMapping
	public String prodForm() {
		return "prod/prodForm";
	}
	
	@PostMapping
	public String formProcess(
		@Validated(InsertGroup.class) @ModelAttribute(MODEL_NAME) ProdVO prod, BindingResult errors
		, RedirectAttributes redirectAttributes
	) {
		String lvn;
		if(!errors.hasErrors()) {
			service.createProd(prod);
			lvn = "redirect:/prod/prodDetail.do?what="+prod.getProdId();
		} else {
			redirectAttributes.addFlashAttribute("errors", errorsUtils.errorsToMap(errors));
			redirectAttributes.addFlashAttribute(MODEL_NAME, prod);

			lvn = "redirect:/prod/prodInsert.do";
		}
		
		return lvn;
	}
}
