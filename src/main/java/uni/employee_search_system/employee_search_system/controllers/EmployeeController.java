package uni.employee_search_system.employee_search_system.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uni.employee_search_system.employee_search_system.models.dtos.EmployeeReqDto;
import uni.employee_search_system.employee_search_system.models.dtos.EmployeeResDto;
import uni.employee_search_system.employee_search_system.models.dtos.ModifyEmployeeReqDto;
import uni.employee_search_system.employee_search_system.models.dtos.search.SearchReqDto;
import uni.employee_search_system.employee_search_system.models.dtos.search.SearchResDto;
import uni.employee_search_system.employee_search_system.models.vo.Employee;
import uni.employee_search_system.employee_search_system.services.CommonService;
import uni.employee_search_system.employee_search_system.services.EmployeeService;
import uni.employee_search_system.employee_search_system.services.IndexService;

@Controller
@RequestMapping("/employee/*")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	private final IndexService indexService;

	private final CommonService commonService;

	@PostMapping("/find")
	public String findEmployee(Model model, SearchReqDto searchReqDto, RedirectAttributes redirectAttributes) {
		System.out.println("model : " + model);
		SearchResDto searchResDto = new SearchResDto();
		searchResDto.setSexList(List.of("M", "F"));
		searchResDto.setDepartmentList(indexService.getDepartmentList());
		searchResDto.setSupervisorList(indexService.getSupervisorList());
		model.addAttribute("searchResDto", searchResDto);
		String searchCondition = searchReqDto.getSearchCondition();
		List<EmployeeResDto> ret;
		switch (searchCondition) {
			case "all":
				ret = employeeService.findAllEmployee(searchReqDto);
				break;
			case "department":
				ret = employeeService.findDepartmentEmployee(searchReqDto);
				break;
			case "sex":
				ret = employeeService.findSexEmployee(searchReqDto);
				break;
			case "salary":
				ret = employeeService.findSalaryEmployee(searchReqDto);
				break;
			case "bdate":
				ret = employeeService.findBdateEmployee(searchReqDto);
				break;
			default:
				ret = employeeService.findSupervisorEmployee(searchReqDto);
				break;
		}
		model.addAttribute("EmployeeResDtoList", ret);

		List<EmployeeResDto> employeeResDtoList =
				(List<EmployeeResDto>) model.getAttribute("EmployeeResDtoList");
		ModifyEmployeeReqDto modifyEmployeeReqDto = new ModifyEmployeeReqDto(employeeResDtoList);
		model.addAttribute("ModifyEmployeeReqDto",modifyEmployeeReqDto);

		commonService.convertRedirectModel(model, redirectAttributes);

		return "redirect:/";
	}

	@PostMapping("/update")
	public String updateEmployee(Model model,
			@ModelAttribute("ModifyEmployeeReqDto") ModifyEmployeeReqDto modifyEmployeeReqDto) {

		System.out.println("=========" + modifyEmployeeReqDto);

		List<String> modifyingSsn = employeeService.getModifyingSsn(modifyEmployeeReqDto.getEmployeeReqDtoList());
		if (modifyingSsn.isEmpty())
			return "redirect:/";

		if (modifyEmployeeReqDto.getUpdateCondition().equals("sex")) {
			employeeService.updateSex(modifyEmployeeReqDto.getSex(), modifyingSsn);
		} else if (modifyEmployeeReqDto.getUpdateCondition().equals("salary")) {
			if (employeeService.updateSalary(modifyEmployeeReqDto.getSalary(), modifyingSsn) == -1) {
				// TODO salary가 double값이 아닐 때 exceptionhandling 필요
			}
		} else if (modifyEmployeeReqDto.getUpdateCondition().equals("address")) {
			employeeService.updateAddress(modifyEmployeeReqDto.getAddress(), modifyingSsn);
		}

		return "redirect:/";
	}

	@PostMapping("/delete")
	public String deleteEmployee(Model model,
			@ModelAttribute("ModifyEmployeeReqDto") ModifyEmployeeReqDto modifyEmployeeReqDto) {

		List<String> modifyingSsn = employeeService.getModifyingSsn(modifyEmployeeReqDto.getEmployeeReqDtoList());
		if (modifyingSsn.isEmpty())
			return "redirect:/";

		employeeService.deleteEmployees(modifyingSsn);

		return "redirect:/";
	}



}
