package uni.employee_search_system.employee_search_system.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uni.employee_search_system.employee_search_system.commons.application.DBProperty;
import uni.employee_search_system.employee_search_system.dao.Jdbc;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final DBProperty appProperty;

	@GetMapping
	public String allEmployee(Model model) {
		Jdbc.getInstance();
		return "index";
	}

}
