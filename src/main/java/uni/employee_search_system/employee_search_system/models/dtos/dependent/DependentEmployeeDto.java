package uni.employee_search_system.employee_search_system.models.dtos.dependent;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class DependentEmployeeDto {

	private String name;
	private String ssn;
	private String bdate;
	private String address;
	private String sex;
	private String salary;
	private List<DependentDto> dependentDtoList = new ArrayList<>();

	public DependentEmployeeDto(ResultSet rs) {
		try {
			this.name = rs.getString("Fname") + " "
					+ rs.getString("Minit") + " "
					+ rs.getString("Lname");

			this.ssn = rs.getString("Ssn");
			this.bdate = rs.getString("Bdate");
			this.address = rs.getString("Address");
			this.sex = rs.getString("Sex");
			this.salary = rs.getString("Salary");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
