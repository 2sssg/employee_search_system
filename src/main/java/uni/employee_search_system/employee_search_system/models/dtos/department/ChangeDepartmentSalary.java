package uni.employee_search_system.employee_search_system.models.dtos.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ChangeDepartmentSalary {

	private String departmentNumber;
	private String salary;

	public ChangeDepartmentSalary(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}
}
