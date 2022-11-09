package uni.employee_search_system.employee_search_system.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uni.employee_search_system.employee_search_system.models.dtos.EmployeeResDto;
import uni.employee_search_system.employee_search_system.models.dtos.SearchReqDto;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(Model model, ArrayList<EmployeeResDto> employeeResDtoList) {
		System.out.println("model: " + model);
		model.addAttribute("searchReqDto",new SearchReqDto());
		model.addAttribute("employeeResDtoList",employeeResDtoList);
		System.out.println("model : " + model);
		System.out.println();
		return "index";
	}
}
