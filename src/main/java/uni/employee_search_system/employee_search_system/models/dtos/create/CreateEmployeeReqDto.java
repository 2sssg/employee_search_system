package uni.employee_search_system.employee_search_system.models.dtos.create;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uni.employee_search_system.employee_search_system.models.vo.Employee;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class CreateEmployeeReqDto {

	private String fname;
	private String minit;
	private String lname;
	private String ssn;
	private String bdate;
	private String address;
	private String sex;
	private String salary;
	private String superSsn;
	private String dno;

	public Employee toEmployee() {
		return Employee.builder()
				.fname(fname)
				.minit(minit)
				.lname(lname)
				.ssn(ssn)
				.bdate(bdate.equals("") ? null : bdate)
				.address(address.equals("") ? null : address)
				.sex(sex)
				.salary(Double.parseDouble(salary.equals("") ? "0" : salary))
				.superSsn(superSsn.equals("") ? null : superSsn)
				.dno(Integer.parseInt(dno.equals("") ? "1" : dno))
				.createTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.format(new Timestamp(System.currentTimeMillis())))
				.modifytime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.format(new Timestamp(System.currentTimeMillis())))
				.build();
	}
}
