package uni.employee_search_system.employee_search_system.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReqDto {

	private boolean check;
	private String ssn;

	public EmployeeReqDto(String ssn) {
		this.ssn = ssn;
	}
}
