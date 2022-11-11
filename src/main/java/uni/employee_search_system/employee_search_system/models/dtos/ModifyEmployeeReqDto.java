package uni.employee_search_system.employee_search_system.models.dtos;


import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@ToString
public class ModifyEmployeeReqDto {

	private String updateCondition;
	private String address;
	private String sex;
	private String salary;
	private List<EmployeeReqDto> employeeReqDtoList = new ArrayList<>();



	public ModifyEmployeeReqDto(List<EmployeeResDto> employeeResDtoList) {
		for (EmployeeResDto employeeResDto : employeeResDtoList) {
			this.employeeReqDtoList.add(new EmployeeReqDto(employeeResDto.getSsn()));
		}
	}

}
