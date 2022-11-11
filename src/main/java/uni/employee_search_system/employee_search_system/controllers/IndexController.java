package uni.employee_search_system.employee_search_system.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uni.employee_search_system.employee_search_system.models.dtos.ModifyEmployeeReqDto;
import uni.employee_search_system.employee_search_system.models.dtos.search.SearchReqDto;
import uni.employee_search_system.employee_search_system.models.dtos.search.SearchResDto;
import uni.employee_search_system.employee_search_system.services.IndexService;

@Controller
@RequiredArgsConstructor
public class IndexController {

	private final IndexService indexService;

	@GetMapping("/")
	public String index(Model model) {
		SearchResDto searchResDto = new SearchResDto();
		System.out.println("model : " + model);
		searchResDto.setSexList(List.of("M", "F"));
		searchResDto.setDepartmentList(indexService.getDepartmentList());
		searchResDto.setSupervisorList(indexService.getSupervisorList());

		model.addAttribute("searchResDto", searchResDto);

		if (!model.containsAttribute("ModifyEmployeeReqDto")) {
			model.addAttribute("ModifyEmployeeReqDto", new ModifyEmployeeReqDto());
		}

		if (!model.containsAttribute("searchReqDto"))
			model.addAttribute("searchReqDto",new SearchReqDto());

		System.out.println("model : " + model);
		return "index";
	}
}
