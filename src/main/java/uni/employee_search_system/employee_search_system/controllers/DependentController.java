package uni.employee_search_system.employee_search_system.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uni.employee_search_system.employee_search_system.models.dtos.department.DepartmentEmployeeInformationDto;
import uni.employee_search_system.employee_search_system.services.DepartmentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dependent/*")
public class DependentController {


	private final DepartmentService departmentService;

	@GetMapping("/")
	public String employeeByDependent(Model model) {

		List<DepartmentEmployeeInformationDto> departmentEmployeeInformationDto =
				departmentService.getAllDepartmentEmployee();
		model.addAttribute("DepartmentEmployeeInformationDto", departmentEmployeeInformationDto);

		return "employee_dependent_info";
	}


}
