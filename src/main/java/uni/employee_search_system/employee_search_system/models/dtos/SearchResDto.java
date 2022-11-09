package uni.employee_search_system.employee_search_system.models.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
@ToString
public class SearchResDto {

	private List<String> departmentList;

	private List<String> sexList;

	private List<String> supervisorList;
}
