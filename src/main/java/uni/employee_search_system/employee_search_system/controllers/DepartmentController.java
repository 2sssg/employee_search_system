package uni.employee_search_system.employee_search_system.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uni.employee_search_system.employee_search_system.models.dtos.department.ChangeDepartmentSalary;
import uni.employee_search_system.employee_search_system.models.dtos.department.DepartmentEmployeeInformationDto;
import uni.employee_search_system.employee_search_system.services.DepartmentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {


	private final DepartmentService departmentService;

	@GetMapping
	public String employeeByDepartment(Model model) {

		List<DepartmentEmployeeInformationDto> departmentEmployeeInformationDtos =
				departmentService.getAllDepartmentEmployee();

		model.addAttribute("DepartmentEmployeeInformationDtos", departmentEmployeeInformationDtos);
		return "department_employee_info";
	}

	@PostMapping("/change_salary")
	public String modifyDepartmentSalary(Model model, @RequestBody String urlQuery) {

		String[] split = urlQuery.split("&");
		String department = split[0].split("=")[1];
		String salary = split[1].split("=")[1];

		departmentService.updateDepartmentSalary(department, salary);

		return "redirect:/department";
	}


}
