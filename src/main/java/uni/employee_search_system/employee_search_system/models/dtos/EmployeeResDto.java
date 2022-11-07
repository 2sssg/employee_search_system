package uni.employee_search_system.employee_search_system.models.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class EmployeeResDto {

	private String name;
	private String ssn;
	private String bdate;
	private String address;
	private String sex;
	private Double salary;
	private String supervisor;
	private String department;

}
