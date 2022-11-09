package uni.employee_search_system.employee_search_system.models.dtos;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class SearchReqDto {

	private String searchCondition;
	private String departmentCondition;
	private String sexCondition;
	private String salaryCondition;
	private String bdateCondition;
	private String supervisorCondition;

	private boolean name;
	private boolean ssn;
	private boolean bdate;
	private boolean address;
	private boolean sex;
	private boolean salary;
	private boolean supervisor;
	private boolean department;

	private boolean isNeedAll() {
		if (!(name || ssn || bdate || address || sex || salary || supervisor || department)) {
			this.name = true;
			this.ssn = true;
			this.bdate = true;
			this.address = true;
			this.sex = true;
			this.salary = true;
			this.supervisor = true;
			this.department = true;
		}
		return (name && ssn && bdate && address && sex && salary && supervisor && department);
	}

	public List<String> wantsList() {

		if (this.isNeedAll()) return null;

		List<String> ret = new ArrayList<>();
		if (name) ret.add("name");
		if (ssn) ret.add("ssn");
		if (bdate) ret.add("bdate");
		if (address) ret.add("address");
		if (sex) ret.add("sex");
		if (salary) ret.add("salary");
		if (supervisor) ret.add("super_ssn");
		if (department) ret.add("dno");
		return ret;
	}

}
