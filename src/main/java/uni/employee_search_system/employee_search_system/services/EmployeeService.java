package uni.employee_search_system.employee_search_system.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.employee_search_system.employee_search_system.dao.Jdbc;
import uni.employee_search_system.employee_search_system.models.dtos.EmployeeReqDto;
import uni.employee_search_system.employee_search_system.models.dtos.EmployeeResDto;
import uni.employee_search_system.employee_search_system.models.dtos.create.CreateEmployeeReqDto;
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

	public List<EmployeeResDto> findSupervisorEmployee(SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> employeeList = null;
		String supervisorCondition = searchReqDto.getSupervisorCondition();
		if (wants == null) {
			employeeList = findBySupervisor(supervisorCondition);
		} else {
			employeeList = findBySupervisor(supervisorCondition, wants);
		}

		return employeeResDtoList(employeeList, searchReqDto);

	}

	public List<EmployeeResDto> findBdateEmployee(SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> employeeList = null;
		String bdateCondition = searchReqDto.getBdateCondition();
		if (wants == null) {
			employeeList = findByBdate(bdateCondition);
		} else {
			employeeList = findByBdate(bdateCondition, wants);
		}

		return employeeResDtoList(employeeList, searchReqDto);

	}

	public List<EmployeeResDto> findSalaryEmployee(SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> employeeList = null;
		String salaryCondition = searchReqDto.getSalaryCondition().equals("") ? "0" : searchReqDto.getSalaryCondition();
		if (wants == null) {
			employeeList = findBySalary(Integer.parseInt(salaryCondition));
		} else {
			employeeList = findBySalary(Integer.parseInt(salaryCondition), wants);
		}

		return employeeResDtoList(employeeList, searchReqDto);

	}

	public List<EmployeeResDto> findSexEmployee(SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> employeeList = null;
		String sexCondition = searchReqDto.getSexCondition();
		if (wants == null) {
			employeeList = findBySex(sexCondition);
		} else {
			employeeList = findBySex(sexCondition, wants);
		}

		return employeeResDtoList(employeeList, searchReqDto);

	}

	public List<EmployeeResDto> findDepartmentEmployee(SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> employeeList = null;
		String departmentCondition = searchReqDto.getDepartmentCondition();
		if (wants == null) {
			employeeList = findByDepartment(departmentCondition);
		} else {
			employeeList = findByDepartment(departmentCondition, wants);
		}

		return employeeResDtoList(employeeList, searchReqDto);

	}

	public List<EmployeeResDto> findAllEmployee(SearchReqDto searchReqDto) {

		List<String> wants = searchReqDto.wantsList();
		List<Employee> allEmployee = null;
		if (wants == null) {
			allEmployee = findByAll();
		} else {
			allEmployee = findByAll(wants);
		}

		return employeeResDtoList(allEmployee, searchReqDto);

	}


	private List<Employee> findByAll(List<String> wants) {

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

	private List<Employee> findByDepartment(String departmentCondition) {

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

	private List<Employee> findByDepartment(String departmentCondition, List<String> wants) {

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

	private List<Employee> findBySex(String sexCondition) {

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

	private List<Employee> findBySex(String sexCondition, List<String> wants) {

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

	private List<Employee> findBySalary(int salaryCondition) {
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

	private List<Employee> findBySalary(int salaryCondition, List<String> wants) {
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

	private List<Employee> findByBdate(String bdateCondition) {

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

	private List<Employee> findByBdate(String bdateCondition, List<String> wants) {

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

	private List<Employee> findBySupervisor(String supervisorCondition) {

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

	private List<Employee> findBySupervisor(String supervisorCondition, List<String> wants) {

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

	private List<EmployeeResDto> employeeResDtoList(List<Employee> employeeList, SearchReqDto searchReqDto) {

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
						"SELECT Fname, Minit, Lname FROM EMPLOYEE WHERE ssn LIKE '" + employee.getSuperSsn() + "'");
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

	public List<String> getModifyingSsn(List<EmployeeReqDto> employeeReqDtoList) {
		List<String> updateTargetList = new ArrayList<>();
		employeeReqDtoList.forEach(employeeReqDto -> {
			if (employeeReqDto.isCheck())
				updateTargetList.add(employeeReqDto.getSsn());
		});

		return updateTargetList;
	}

	public int updateSex(String sex, List<String> modifyingSsn, String modifytime) {

		String update = "UPDATE EMPLOYEE ";
		String set = "SET sex = '" + sex + "' ," + "modify_time = '" + modifytime + "' ";
		String where = getWhereQuery(modifyingSsn);

		return jdbc.executeUpdate(update + set + where);
	}



	public int updateSalary(String salary, List<String> modifyingSsn, String modifytime) {

		double doubleSalary;
		try {
			doubleSalary = Double.parseDouble(salary);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		String update = "UPDATE EMPLOYEE ";
		String set = "SET Salary = '" + doubleSalary + "' ," + "modify_time = '" + modifytime + "' ";
		String where = getWhereQuery(modifyingSsn);

		return jdbc.executeUpdate(update + set + where);
	}

	public int updateAddress(String address, List<String> modifyingSsn, String modifytime) {

		String update = "UPDATE EMPLOYEE ";
		String set = "SET Address = '" + address + "' ," + "modify_time = '" + modifytime + "' ";
		String where = getWhereQuery(modifyingSsn);

		return jdbc.executeUpdate(update + set + where);
	}

	public int deleteEmployees(List<String> deleteSsn) {

		deleteSsn.remove("888665555");
		deleteSsn.forEach(this::modifyToMeetConstraints);
		if (deleteSsn.isEmpty())
			return 0;
		String query = "DELETE "
				+ "FROM EMPLOYEE "
				+ getWhereQuery(deleteSsn);

		return jdbc.executeUpdate(query);
	}

	public void modifyToMeetConstraints(String ssn) {

		// ssn??? ????????? works_on ??? ??????
		String worksOnQuery = "DELETE FROM WORKS_ON WHERE Essn LIKE " + ssn;
		jdbc.executeUpdate(worksOnQuery);

		// ssn??? ????????? dependent ??? ??????
		String dependentQuery = "DELETE FROM DEPENDENT WHERE Essn LIKE " + ssn;
		jdbc.executeUpdate(dependentQuery);

		// ssn??? ???????????? ?????? ???????????? default ssn ?????? ??????
		String departmentQuery = "UPDATE DEPARTMENT SET Mgr_ssn = '888665555' WHERE Mgr_ssn LIKE " + ssn;
		jdbc.executeUpdate(departmentQuery);

		String deleteEmployeeQuery = Employee.builder().ssn(ssn).build().toQuery();
		Employee deleteEmployee;
		try {
			ResultSet rs = jdbc.executeQuery(deleteEmployeeQuery);
			rs.next();
			deleteEmployee = new Employee(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return ;
		}
		// ???????????? ????????? ????????? ??????????????? ???????????? super_ssn???
		// ???????????? ????????? ????????? ??????
		// ex) a -> b -> c ?????? b??? ???????????? a -> c??? ??????????????????
		jdbc.executeUpdate("UPDATE EMPLOYEE "
				+ "SET Super_ssn = " + deleteEmployee.getSuperSsn() + " "
				+ "WHERE Super_ssn = " + deleteEmployee.getSsn());
	}

	private String getWhereQuery(List<String> modifyingSsn) {
		return "WHERE Ssn IN "
				+ modifyingSsn.toString().replace("[", "(").replace("]", ")");
	}

	public boolean validateEmployeeInformation(CreateEmployeeReqDto createEmployeeReqDto) {

		return (validateSsn(createEmployeeReqDto.getSsn())
				&& validateSuperSsn(createEmployeeReqDto.getSuperSsn())
				&& validateDno(createEmployeeReqDto.getDno()))
				&& (!createEmployeeReqDto.getFname().equals(""))
				&& (!createEmployeeReqDto.getMinit().equals(""))
				&& (!createEmployeeReqDto.getLname().equals(""));
	}

	private boolean validateDno(String dno) {

		if (dno.length() == 0) return true;
		ResultSet rs = jdbc.executeQuery("SELECT COUNT(*) FROM DEPARTMENT WHERE Dnumber = " + dno);
		try {
			rs.next();
			return rs.getInt(1) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean validateSuperSsn(String superSsn) {

		if (superSsn.length() == 0) return true;
		if (superSsn.length() != 9) return false;
		ResultSet rs = jdbc.executeQuery("SELECT COUNT(*) FROM EMPLOYEE WHERE Ssn LIKE '" + superSsn + "'");
		try {
			rs.next();
			return rs.getInt(1) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean validateSsn(String ssn) {

		if (ssn.length() != 9) return false;

		ResultSet rs = jdbc.executeQuery("SELECT COUNT(*) FROM EMPLOYEE WHERE Ssn LIKE '" + ssn + "'");
		try {
			rs.next();
			return rs.getInt(1) == 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void createEmployee(CreateEmployeeReqDto createEmployeeReqDto) {

		Employee employee = createEmployeeReqDto.toEmployee();
		String insertQuery = employee.toInsertQuery();
		jdbc.executeUpdate(insertQuery);
	}
}
