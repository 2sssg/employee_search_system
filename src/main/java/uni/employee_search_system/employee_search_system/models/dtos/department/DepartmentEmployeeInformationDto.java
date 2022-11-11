package uni.employee_search_system.employee_search_system.models.dtos.department;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class DepartmentEmployeeInformationDto {

	private String departmentName;
	private String departmentNumber;
	private List<DepartmentEmployeeDto> departmentEmployeeDtoList = new ArrayList<>();
	private String salary;


	public DepartmentEmployeeInformationDto(String departmentName, String departmentNumber) {
		this.departmentName = departmentName;
		this.departmentNumber = departmentNumber;
	}
}
