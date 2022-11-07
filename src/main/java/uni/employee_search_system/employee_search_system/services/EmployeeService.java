package uni.employee_search_system.employee_search_system.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.employee_search_system.employee_search_system.dao.Jdbc;
import uni.employee_search_system.employee_search_system.models.vo.Employee;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final Jdbc jdbc;

	public List<Employee> findByAll() throws SQLException {
		ResultSet resultSet = jdbc.executeQuery(new Employee().toQuery());

		List<Employee> employees = new ArrayList<>();
		while (resultSet.next()) {
			employees.add(

			);
		}
	}

}
