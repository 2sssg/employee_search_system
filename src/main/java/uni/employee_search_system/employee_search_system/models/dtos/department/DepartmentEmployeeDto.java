package uni.employee_search_system.employee_search_system.models.dtos.department;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.transform.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class DepartmentEmployeeDto {

	private String name;
	private String ssn;
	private String bdate;
	private String address;
	private String sex;
	private String salary;
	private String supervisor;

	public DepartmentEmployeeDto(ResultSet rs) {
		try {
			this.name = rs.getString("fname") + " "
					+ rs.getString("minit") + " "
					+ rs.getString("lname");
			this.ssn = rs.getString("Ssn");
			this.bdate = rs.getString("Bdate");
			this.address = rs.getString("Address");
			this.sex = rs.getString("Sex");
			this.salary = rs.getString("Salary");
			if (rs.getString("sfname") != null) {
				this.supervisor = rs.getString("sfname") + " "
						+ rs.getString("sminit") + " "
						+ rs.getString("slname");
			} else {
				this.supervisor = "";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
