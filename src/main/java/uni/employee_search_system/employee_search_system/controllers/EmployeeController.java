package uni.employee_search_system.employee_search_system.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uni.employee_search_system.employee_search_system.dao.Jdbc;
import uni.employee_search_system.employee_search_system.models.dtos.EmployeeResDto;
import uni.employee_search_system.employee_search_system.models.dtos.SearchReqDto;
import uni.employee_search_system.employee_search_system.models.vo.Employee;
import uni.employee_search_system.employee_search_system.services.EmployeeService;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final Jdbc jdbc;

	private final EmployeeService employeeService;

	@PostMapping
	public String findEmployee(Model model, SearchReqDto searchReqDto) {
		System.out.println("model : " + model);
		String employees = findAllEmployee(model, searchReqDto);
		System.out.println("model : " + model);
		return employees;
	}

	private String findAllEmployee(Model model, SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> allEmployee = null;
		if (wants == null) {
			allEmployee = employeeService.findByAll();
		} else {
			allEmployee = employeeService.findByAll(wants);
		}

		List<EmployeeResDto> employeeResDtos = employeeService.employeeResDtoList(allEmployee, searchReqDto);
		model.addAttribute(employeeResDtos);
		return "index";
	}

}
