package uni.employee_search_system.employee_search_system.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.employee_search_system.employee_search_system.dao.Jdbc;
import uni.employee_search_system.employee_search_system.models.dtos.EmployeeResDto;
import uni.employee_search_system.employee_search_system.models.dtos.SearchReqDto;
import uni.employee_search_system.employee_search_system.models.vo.Employee;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final Jdbc jdbc;

	public List<Employee> findByAll() {
		ResultSet resultSet = jdbc.executeQuery(new Employee().toQuery());

		List<Employee> employees = new ArrayList<>();
		try {
			while (resultSet.next()) {
				employees.add(new Employee(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	public List<Employee> findByAll(List<String> wants) {
		String query = new Employee().toQuery(wants);
		ResultSet resultSet = jdbc.executeQuery(query);

		List<Employee> employees = new ArrayList<>();
		try {
			while (resultSet.next()) {
				employees.add(new Employee(resultSet, wants));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	public List<EmployeeResDto> employeeResDtoList(List<Employee> employeeList, SearchReqDto searchReqDto) {

		List<EmployeeResDto> ret = new ArrayList<>();
		for(Employee employee: employeeList) {
			ret.add(
					EmployeeResDto.builder()
							.name(searchReqDto.isName() ?
									employee.getFname() + " " + employee.getMinit() + " " + employee.getLname() :
									null)
							.ssn(employee.getSsn())
							.bdate(employee.getBdate())
							.address(employee.getAddress())
							.sex(employee.getSex())
							.salary(employee.getSalary())

							.useName(searchReqDto.isName())
							.useSsn(searchReqDto.isSsn())
							.useBdate(searchReqDto.isBdate())
							.useAddress(searchReqDto.isAddress())
							.useSex(searchReqDto.isSex())
							.useSalary(searchReqDto.isSalary())
							.useDepartment(searchReqDto.isDepartment())
							.useSupervisor(searchReqDto.isSupervisor())
							.build()
			);
			if (searchReqDto.isSupervisor() && employee.getSuperSsn() != null) {
				ResultSet resultSet = jdbc.executeQuery(
						"SELECT Fname, Minit, Lname FROM EMPLOYEE WHERE ssn LIKE " + employee.getSuperSsn());
				ret.get(ret.size() - 1).setSupervisor(resultSet);
			}
			if (searchReqDto.isDepartment()) {
				ResultSet resultSet = jdbc.executeQuery(
						"SELECT Dname FROM DEPARTMENT WHERE Dnumber = " + employee.getDno());
				ret.get(ret.size() - 1).setDepartment(resultSet);
			}
		}

		return ret;
	}


}
