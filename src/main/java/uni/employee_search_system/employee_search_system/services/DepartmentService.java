package uni.employee_search_system.employee_search_system.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.employee_search_system.employee_search_system.dao.Jdbc;
import uni.employee_search_system.employee_search_system.models.dtos.department.ChangeDepartmentSalary;
import uni.employee_search_system.employee_search_system.models.dtos.department.DepartmentEmployeeDto;
import uni.employee_search_system.employee_search_system.models.dtos.department.DepartmentEmployeeInformationDto;

@Service
@RequiredArgsConstructor
public class DepartmentService {

	private final Jdbc jdbc;

	public List<DepartmentEmployeeInformationDto> getAllDepartmentEmployee() {

		List<DepartmentEmployeeInformationDto> departmentEmployeeInformationDtoList = new ArrayList<>();
		ResultSet rs = jdbc.executeQuery("SELECT * FROM DEPARTMENT");
		try {
			while (rs.next()) {
				String departmentName = rs.getString("Dname");
				String departmentNumber = rs.getString("Dnumber");
				departmentEmployeeInformationDtoList
						.add(new DepartmentEmployeeInformationDto(departmentName, departmentNumber));
				int dno = rs.getInt("Dnumber");
				ResultSet emp = jdbc.executeQuery("SELECT e1.fname, e1.minit, e1.lname, e1.Ssn, e1.Bdate, e1.Salary, "
						+ "e1.Address, e1.Sex, e2.fname as sfname, e2.minit as sminit, e2.lname as slname "
						+ "FROM EMPLOYEE as e1 "
						+ "LEFT JOIN EMPLOYEE as e2 ON e1.super_Ssn LIKE e2.Ssn "
						+ "WHERE e1.dno = " + dno);
				while (emp.next()) {
					departmentEmployeeInformationDtoList
							.get(departmentEmployeeInformationDtoList.size() - 1)
							.getDepartmentEmployeeDtoList()
							.add(new DepartmentEmployeeDto(emp));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return departmentEmployeeInformationDtoList;
	}

	public List<ChangeDepartmentSalary> getChangeDepartmentSalaryList(
			List<DepartmentEmployeeInformationDto> departmentEmployeeInformationDtos) {

		List<ChangeDepartmentSalary> changeDepartmentSalaryList = new ArrayList<>();

		departmentEmployeeInformationDtos.forEach(dept -> {
			changeDepartmentSalaryList.add(new ChangeDepartmentSalary(dept.getDepartmentNumber()));
		});
		return changeDepartmentSalaryList;
	}

	public void updateDepartmentSalary(String salary, String department) {

		String modifyTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
				.format(new Timestamp(System.currentTimeMillis()));
		jdbc.executeUpdate("UPDATE EMPLOYEE "
				+ "SET Salary = " + salary + " , modify_time = '" + modifyTime + "' "
				+ "WHERE dno LIKE '" + department + "'" );
	}
}
