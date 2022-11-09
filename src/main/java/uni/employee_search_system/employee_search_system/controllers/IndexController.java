package uni.employee_search_system.employee_search_system.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uni.employee_search_system.employee_search_system.dao.Jdbc;
import uni.employee_search_system.employee_search_system.models.dtos.SearchReqDto;
import uni.employee_search_system.employee_search_system.models.dtos.SearchResDto;
import uni.employee_search_system.employee_search_system.services.IndexService;

@Controller
@RequiredArgsConstructor
public class IndexController {

	private final IndexService indexService;

	@GetMapping("/")
	public String index(Model model) {
		SearchResDto searchResDto = new SearchResDto();
		searchResDto.setSexList(List.of("M", "F"));
		searchResDto.setDepartmentList(indexService.getDepartmentList());
		searchResDto.setSupervisorList(indexService.getSupervisorList());
		model.addAttribute("searchResDto", searchResDto);
		model.addAttribute("searchReqDto",new SearchReqDto());
		return "index";
	}
}
