package uni.employee_search_system.employee_search_system.models.dtos.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
@ToString
public class UpdateReqDto {

	private boolean update;
	private String ssn;
	private String address;
	private String sex;
	private String salary;

}
