package uni.employee_search_system.employee_search_system.models.dtos;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@ToString
public class EmployeeResDto {

	private String name;
	private String ssn;
	private String bdate;
	private String address;
	private String sex;
	private Double salary;
	private String supervisor;
	private String department;

	private boolean check;
	private boolean useName;
	private boolean useSsn;
	private boolean useBdate;
	private boolean useAddress;
	private boolean useSex;
	private boolean useSalary;
	private boolean useSupervisor;
	private boolean useDepartment;

	public void setSupervisor(ResultSet rs) {
		try {
			rs.next();
			StringBuilder superBuilder = new StringBuilder("");
			superBuilder
					.append(rs.getString(1)).append(" ")
					.append(rs.getString(2)).append(" ")
					.append(rs.getString(3));
			this.supervisor = superBuilder.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setDepartment(ResultSet rs) {
		try {
			rs.next();
			this.department = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
