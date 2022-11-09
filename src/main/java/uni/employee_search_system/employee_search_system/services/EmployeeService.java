package uni.employee_search_system.employee_search_system.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.employee_search_system.employee_search_system.dao.Jdbc;
import uni.employee_search_system.employee_search_system.models.dtos.EmployeeResDto;
import uni.employee_search_system.employee_search_system.models.dtos.search.SearchReqDto;
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

	public List<Employee> findByDepartment(String departmentCondition) {

		ResultSet rs;
		int dno;
		try {
			rs = jdbc.executeQuery("SELECT Dnumber FROM DEPARTMENT WHERE Dname LIKE '" + departmentCondition + "'");
			rs.next();
			dno = rs.getInt("Dnumber");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		rs = jdbc.executeQuery(Employee.builder()
				.dno(dno)
				.build()
				.toQuery());
		List<Employee> employees = new ArrayList<>();
		try {
			while (rs.next()) {
				employees.add(new Employee(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
	public List<Employee> findByDepartment(String departmentCondition, List<String> wants) {

		ResultSet rs;
		int dno;
		try {
			rs = jdbc.executeQuery("SELECT Dnumber FROM DEPARTMENT WHERE Dname LIKE " + departmentCondition);
			rs.next();
			dno = rs.getInt("Dnumber");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		rs = jdbc.executeQuery(Employee.builder()
				.dno(dno)
				.build()
				.toQuery(wants));
		List<Employee> employees = new ArrayList<>();
		try {
			while (rs.next()) {
				employees.add(new Employee(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public List<Employee> findBySex(String sexCondition) {

		ResultSet resultSet = jdbc.executeQuery(Employee.builder().sex(sexCondition).build().toQuery());
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

	public List<Employee> findBySex(String sexCondition, List<String> wants) {

		ResultSet resultSet = jdbc.executeQuery(Employee.builder().sex(sexCondition).build().toQuery(wants));
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

	public List<Employee> findBySalary(int salaryCondition) {
		ResultSet resultSet = jdbc.executeQuery(Employee.builder().salary(salaryCondition).build().toQuery());
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

	public List<Employee> findBySalary(int salaryCondition, List<String> wants) {
		ResultSet resultSet = jdbc.executeQuery(Employee.builder().salary(salaryCondition).build().toQuery(wants));
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

	public List<Employee> findByBdate(String bdateCondition) {

		if (bdateCondition.length() == 1) bdateCondition = "0"+bdateCondition;
		ResultSet resultSet = jdbc.executeQuery(Employee.builder().bdate(bdateCondition).build().toQuery());
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

	public List<Employee> findByBdate(String bdateCondition, List<String> wants) {

		if (bdateCondition.length() == 1) bdateCondition = "0" + bdateCondition;
		ResultSet resultSet = jdbc.executeQuery(Employee.builder().bdate(bdateCondition).build().toQuery(wants));
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

	public List<Employee> findBySupervisor(String supervisorCondition) {

		String[] name = supervisorCondition.split(" ");
		String fname = name[0];
		String minit = name[1];
		String lname = name[2];
		ResultSet rs = jdbc.executeQuery(Employee
				.builder()
				.fname(fname)
				.minit(minit)
				.lname(lname)
				.build().toQuery());


		String ssn;
		try {
			rs.next();
			ssn = rs.getString("Ssn");

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		ResultSet resultSet = jdbc.executeQuery(Employee.builder().superSsn(ssn).build().toQuery());
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

	public List<Employee> findBySupervisor(String supervisorCondition, List<String> wants) {

		String[] name = supervisorCondition.split(" ");
		String fname = name[0];
		String minit = name[1];
		String lname = name[2];
		ResultSet rs = jdbc.executeQuery(Employee
				.builder()
				.fname(fname)
				.minit(minit)
				.lname(lname)
				.build().toQuery());


		String ssn;
		try {
			rs.next();
			ssn = rs.getString("Ssn");

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		ResultSet resultSet = jdbc.executeQuery(Employee.builder().ssn(ssn).build().toQuery(wants));
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
