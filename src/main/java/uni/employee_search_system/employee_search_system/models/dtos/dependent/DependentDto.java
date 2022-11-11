package uni.employee_search_system.employee_search_system.models.dtos.dependent;


import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DependentDto {

	private String dependentName;
	private String sex;
	private String bdate;
	private String relationship;

	public DependentDto(ResultSet rs) {

		try {
			this.dependentName = rs.getString("Dependent_name");
			this.sex = rs.getString("Sex");
			this.bdate = rs.getString("Bdate");
			this.relationship = rs.getString("Relationship");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
