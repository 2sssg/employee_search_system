package uni.employee_search_system.employee_search_system.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uni.employee_search_system.employee_search_system.models.dtos.EmployeeResDto;
import uni.employee_search_system.employee_search_system.models.dtos.ModifyEmployeeReqDto;
import uni.employee_search_system.employee_search_system.models.dtos.create.CreateEmployeeReqDto;
import uni.employee_search_system.employee_search_system.models.dtos.search.SearchReqDto;
import uni.employee_search_system.employee_search_system.models.dtos.search.SearchResDto;
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

		List<String> modifyingSsn = employeeService.getModifyingSsn(modifyEmployeeReqDto.getEmployeeReqDtoList());
		if (modifyingSsn.isEmpty())
			return "redirect:/";

		if (modifyEmployeeReqDto.getUpdateCondition().equals("sex")) {
			employeeService.updateSex(modifyEmployeeReqDto.getSex(), modifyingSsn,
					new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(new Timestamp(System.currentTimeMillis())));
		} else if (modifyEmployeeReqDto.getUpdateCondition().equals("salary")) {
			if (employeeService.updateSalary(modifyEmployeeReqDto.getSalary(), modifyingSsn,
					new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(new Timestamp(System.currentTimeMillis()))) == -1) {
				// TODO salary가 double값이 아닐 때 exceptionhandling 필요
			}
		} else if (modifyEmployeeReqDto.getUpdateCondition().equals("address")) {
			employeeService.updateAddress(modifyEmployeeReqDto.getAddress(), modifyingSsn,
					new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(new Timestamp(System.currentTimeMillis())));
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


	@GetMapping("/create")
	public String createEmployee(Model model) {

		model.addAttribute("CreateEmployeeReqDto", new CreateEmployeeReqDto());
		return "create_employee";
	}

	@PostMapping("/create")
	public String createEmployee(Model model,
			@ModelAttribute("CreateEmployeeReqDto") CreateEmployeeReqDto createEmployeeReqDto) {

		if (!employeeService.validateEmployeeInformation(createEmployeeReqDto))
			return "redirect:/";

		employeeService.createEmployee(createEmployeeReqDto);
		return "redirect:/";
	}


}
