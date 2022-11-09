package uni.employee_search_system.employee_search_system.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.employee_search_system.employee_search_system.dao.Jdbc;

@Service
@RequiredArgsConstructor
public class IndexService {

	private final Jdbc jdbc;

	public List<String> getDepartmentList() {

		Set<String> departmentSet = new HashSet<>();
		ResultSet resultSet = jdbc.executeQuery("SELECT * FROM DEPARTMENT");
		try {
			while (resultSet.next()) {
				departmentSet.add(resultSet.getString("Dname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<>(departmentSet);
	}

	public List<String> getSupervisorList() {

		Set<String> supervisorSet = new HashSet<>();
		ResultSet resultSet = jdbc.executeQuery("SELECT e2.Fname, e2.Minit, e2.Lname "
				+ "FROM EMPLOYEE as e1 "
				+ "JOIN EMPLOYEE as e2 "
				+ "ON e1.Ssn = e2.Super_ssn "
				+ "WHERE e1.Super_ssn IS NOT NULL");
		try {
			while (resultSet.next()) {
				supervisorSet.add(resultSet.getString("Fname") + " " +
						resultSet.getString("Minit") + " " +
						resultSet.getString("Lname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<>(supervisorSet);
	}
}
