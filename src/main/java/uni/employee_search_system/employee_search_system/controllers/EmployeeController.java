package uni.employee_search_system.employee_search_system.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uni.employee_search_system.employee_search_system.dao.Jdbc;
import uni.employee_search_system.employee_search_system.models.dtos.SearchReqDto;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final Jdbc jdbc;

	@PostMapping
	public String allEmployee(Model model, SearchReqDto searchReqDto) {
		System.out.println(searchReqDto);
		return "redirect:/";
	}

}
