package uni.employee_search_system.employee_search_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uni.employee_search_system.employee_search_system.models.dtos.SearchReqDto;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute(new SearchReqDto());

		return "index";
	}
}
