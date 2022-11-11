package uni.employee_search_system.employee_search_system.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.employee_search_system.employee_search_system.dao.Jdbc;
import uni.employee_search_system.employee_search_system.models.dtos.dependent.DependentDto;
import uni.employee_search_system.employee_search_system.models.dtos.dependent.DependentEmployeeDto;

@Service
@RequiredArgsConstructor
public class DependentService {

	private final Jdbc jdbc;

	public List<DependentEmployeeDto> getAllDependentEmployee() {

		List<DependentEmployeeDto> dependentEmployeeDtoList = new ArrayList<>();

		ResultSet rs = jdbc.executeQuery("SELECT * FROM EMPLOYEE");

		try {
			while (rs.next()) {
				String ssn = rs.getString("Ssn");

				ResultSet dependentCount =
						jdbc.executeQuery("SELECT COUNT(*) FROM DEPENDENT WHERE Essn LIKE '" + ssn + "'");
				dependentCount.next();
				if (dependentCount.getInt(1) == 0) continue;

				dependentEmployeeDtoList.add(new DependentEmployeeDto(rs));
				ResultSet dependent = jdbc.executeQuery("SELECT * FROM DEPENDENT WHERE Essn LIKE '" + ssn + "'");
				while (dependent.next()) {
					dependentEmployeeDtoList
							.get(dependentEmployeeDtoList.size() - 1)
							.getDependentDtoList()
							.add(new DependentDto(dependent));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return dependentEmployeeDtoList;
	}
}
