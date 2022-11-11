package uni.employee_search_system.employee_search_system.controllers;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uni.employee_search_system.employee_search_system.models.dtos.department.DepartmentEmployeeInformationDto;
import uni.employee_search_system.employee_search_system.models.dtos.dependent.DependentEmployeeDto;
import uni.employee_search_system.employee_search_system.services.DepartmentService;
import uni.employee_search_system.employee_search_system.services.DependentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dependent")
public class DependentController {


	private final DependentService dependentService;

	@GetMapping
	public String employeeByDependent(Model model) {

		List<DependentEmployeeDto> departmentEmployeeInformationDto =
				dependentService.getAllDependentEmployee();

		model.addAttribute("DepartmentEmployeeInformationDto", departmentEmployeeInformationDto);

		return "employee_dependent_info";
	}


}
