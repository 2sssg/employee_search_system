package uni.employee_search_system.employee_search_system.controllers;

import java.util.List;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uni.employee_search_system.employee_search_system.models.dtos.EmployeeResDto;
import uni.employee_search_system.employee_search_system.models.dtos.search.SearchReqDto;
import uni.employee_search_system.employee_search_system.models.dtos.search.SearchResDto;
import uni.employee_search_system.employee_search_system.models.vo.Employee;
import uni.employee_search_system.employee_search_system.services.EmployeeService;
import uni.employee_search_system.employee_search_system.services.IndexService;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	private final IndexService indexService;

	@PostMapping
	public String findEmployee(Model model, SearchReqDto searchReqDto, RedirectAttributes redirectAttributes) {
		System.out.println("model : " + model);
		SearchResDto searchResDto = new SearchResDto();
		searchResDto.setSexList(List.of("M", "F"));
		searchResDto.setDepartmentList(indexService.getDepartmentList());
		searchResDto.setSupervisorList(indexService.getSupervisorList());
		model.addAttribute("searchResDto", searchResDto);
		String searchCondition = searchReqDto.getSearchCondition();
		if (searchCondition.equals("all"))
			findAllEmployee(model, searchReqDto);
		else if (searchCondition.equals("department"))
			findDepartmentEmployee(model, searchReqDto);
		else if (searchCondition.equals("sex"))
			findSexEmployee(model, searchReqDto);
		else if (searchCondition.equals("salary"))
			findSalaryEmployee(model, searchReqDto);
		else if (searchCondition.equals("bdate"))
			findBdateEmployee(model, searchReqDto);
		else
			findSupervisorEmployee(model, searchReqDto);
		System.out.println("model : " + model);
		for(Entry<String, Object> en : model.asMap().entrySet()) {
			redirectAttributes.addFlashAttribute(en.getKey(), en.getValue());
		}
		System.out.println("model : " + redirectAttributes);
		return "redirect:/";
	}

	@PostMapping("/re")
	public String putEmployee(Model model) {
		return null;
	}

	private void findSupervisorEmployee(Model model, SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> employeeList = null;
		String supervisorCondition = searchReqDto.getSupervisorCondition();
		if (wants == null) {
			employeeList = employeeService.findBySupervisor(supervisorCondition);
		} else {
			employeeList = employeeService.findBySupervisor(supervisorCondition, wants);
		}

		List<EmployeeResDto> employeeResDtos = employeeService.employeeResDtoList(employeeList, searchReqDto);
		model.addAttribute(employeeResDtos);
	}

	private void findBdateEmployee(Model model, SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> employeeList = null;
		String bdateCondition = searchReqDto.getBdateCondition();
		if (wants == null) {
			employeeList = employeeService.findByBdate(bdateCondition);
		} else {
			employeeList = employeeService.findByBdate(bdateCondition, wants);
		}

		List<EmployeeResDto> employeeResDtos = employeeService.employeeResDtoList(employeeList, searchReqDto);
		model.addAttribute(employeeResDtos);
	}

	private void findSalaryEmployee(Model model, SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> employeeList = null;
		String salaryCondition = searchReqDto.getSalaryCondition();
		if (wants == null) {
			employeeList = employeeService.findBySalary(Integer.parseInt(salaryCondition));
		} else {
			employeeList = employeeService.findBySalary(Integer.parseInt(salaryCondition), wants);
		}

		List<EmployeeResDto> employeeResDtos = employeeService.employeeResDtoList(employeeList, searchReqDto);
		model.addAttribute(employeeResDtos);
	}

	private void findSexEmployee(Model model, SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> employeeList = null;
		String sexCondition = searchReqDto.getSexCondition();
		if (wants == null) {
			employeeList = employeeService.findBySex(sexCondition);
		} else {
			employeeList = employeeService.findBySex(sexCondition, wants);
		}

		List<EmployeeResDto> employeeResDtos = employeeService.employeeResDtoList(employeeList, searchReqDto);
		model.addAttribute(employeeResDtos);
	}

	private void findDepartmentEmployee(Model model, SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> employeeList = null;
		String departmentCondition = searchReqDto.getDepartmentCondition();
		if (wants == null) {
			employeeList = employeeService.findByDepartment(departmentCondition);
		} else {
			employeeList = employeeService.findByDepartment(departmentCondition, wants);
		}

		List<EmployeeResDto> employeeResDtos = employeeService.employeeResDtoList(employeeList, searchReqDto);
		model.addAttribute(employeeResDtos);
	}

	private void findAllEmployee(Model model, SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> allEmployee = null;
		if (wants == null) {
			allEmployee = employeeService.findByAll();
		} else {
			allEmployee = employeeService.findByAll(wants);
		}

		List<EmployeeResDto> employeeResDtos = employeeService.employeeResDtoList(allEmployee, searchReqDto);
		model.addAttribute(employeeResDtos);
	}


}
