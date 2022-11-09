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
import uni.employee_search_system.employee_search_system.models.dtos.SearchResDto;
import uni.employee_search_system.employee_search_system.models.vo.Employee;
import uni.employee_search_system.employee_search_system.services.EmployeeService;
import uni.employee_search_system.employee_search_system.services.IndexService;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final Jdbc jdbc;

	private final EmployeeService employeeService;

	private final IndexService indexService;

	@PostMapping
	public String findEmployee(Model model, SearchReqDto searchReqDto) {
		System.out.println("model : " + model);
		SearchResDto searchResDto = new SearchResDto();
		searchResDto.setSexList(List.of("M", "F"));
		searchResDto.setDepartmentList(indexService.getDepartmentList());
		searchResDto.setSupervisorList(indexService.getSupervisorList());
		model.addAttribute("searchResDto", searchResDto);
		String employees;
		String searchCondition = searchReqDto.getSearchCondition();
		if (searchCondition.equals("all"))
			employees = findAllEmployee(model, searchReqDto);
		else if (searchCondition.equals("department"))
			employees = findDepartmentEmployee(model, searchReqDto);
		else if (searchCondition.equals("sex"))
			employees = findSexEmployee(model, searchReqDto);
		else if (searchCondition.equals("salary"))
			employees = findSalaryEmployee(model, searchReqDto);
		else if (searchCondition.equals("bdate"))
			employees = findBdateEmployee(model, searchReqDto);
		else
			employees = findSupervisorEmployee(model, searchReqDto);
		System.out.println("model : " + model);
		return employees;
	}

	private String findSupervisorEmployee(Model model, SearchReqDto searchReqDto) {
		return null;
	}

	private String findBdateEmployee(Model model, SearchReqDto searchReqDto) {
		return null;
	}

	private String findSalaryEmployee(Model model, SearchReqDto searchReqDto) {
		return null;
	}

	private String findSexEmployee(Model model, SearchReqDto searchReqDto) {
		return null;
	}

	private String findDepartmentEmployee(Model model, SearchReqDto searchReqDto) {
		return null;
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
