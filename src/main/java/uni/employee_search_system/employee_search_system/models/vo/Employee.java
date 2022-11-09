package uni.employee_search_system.employee_search_system.models.vo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
@ToString
public class Employee {

	private String fname;
	private String minit;
	private String lname;
	private String ssn;
	private String bdate;
	private String address;
	private String sex;
	private double salary;
	private String superSsn;
	private int dno;


	public String toQuery() {
		String select = "SELECT * ";
		String from = "FROM EMPLOYEE ";
		StringBuilder where = toWhereQuery();

		return select + from + where.toString();

	}



	public String toQuery(List<String> wants) {
		StringBuilder selectBuilder = new StringBuilder("SELECT ");
		wants.forEach(r -> {
			if (r.equals("name")) {
				selectBuilder.append("Fname, Minit, Lname, ");
			} else {
				selectBuilder.append(r).append(", ");
			}
		});
		selectBuilder.setCharAt(selectBuilder.length() - 2, ' ');
		String select = selectBuilder.toString();
		String from = "FROM EMPLOYEE ";
		StringBuilder where = toWhereQuery();

		return select + from + where;
	}

	private StringBuilder toWhereQuery() {
		StringBuilder whereQuery = new StringBuilder();
		if (fname != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND fname LIKE " : "WHERE fname LIKE ")
					.append("'")
					.append(fname)
					.append("' ");
		}

		if (minit != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND minit LIKE " : "WHERE minit LIKE ")
					.append("'")
					.append(minit)
					.append("'")
					.append(" ");
		}

		if (lname != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND lname LIKE " : "WHERE lname LIKE ")
					.append("'")
					.append(lname)
					.append("'")
					.append(" ");
		}

		if (ssn != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND ssn LIKE " : "WHERE ssn LIKE ")
					.append("'")
					.append(ssn)
					.append("'")
					.append(" ");
		}

		if (bdate != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND bdate LIKE " : "WHERE bdate LIKE ")
					.append("'____-")
					.append(bdate)
					.append("-__'")
					.append(" ");
		}

		if (address != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND address LIKE " : "WHERE address LIKE ")
					.append("'")
					.append(address)
					.append("'")
					.append(" ");
		}

		if (sex != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND sex LIKE " : "WHERE sex LIKE ")
					.append("'")
					.append(sex)
					.append("'")
					.append(" ");
		}

		if (salary != 0) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND salary = " : "WHERE salary >= ")
					.append(salary)
					.append(" ");
		}

		if (superSsn != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND super_Ssn LIKE " : "WHERE super_Ssn LIKE ")
					.append("'")
					.append(superSsn)
					.append("'")
					.append(" ");

		}

		if (dno != 0) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND dno = " : "WHERE dno = ")
					.append(dno)
					.append(" ");
		}
		return whereQuery;
	}

	public Employee(ResultSet rs) {

		try {
			this.fname = rs.getString(1);
			this.minit = rs.getString(2);
			this.lname = rs.getString(3);
			this.ssn = rs.getString(4);
			this.bdate = rs.getString(5);
			this.address = rs.getString(6);
			this.sex = rs.getString(7);
			this.salary = rs.getInt(8);
			this.superSsn = rs.getString(9);
			this.dno = rs.getInt(10);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Employee(ResultSet rs, List<String> wants) {

		for (int i = 0; i < wants.size(); ++i) {
			try {
				switch (wants.get(i)) {
					case "name":
						this.fname = rs.getString("fname");
						this.minit = rs.getString("minit");
						this.lname = rs.getString("lname");
						break;
					case "ssn":
						this.ssn = rs.getString("ssn");
						break;
					case "bdate":
						this.bdate = rs.getString("bdate");
						break;
					case "address":
						this.address = rs.getString("address");
						break;
					case "sex":
						this.sex = rs.getString("sex");
						break;
					case "salary":
						this.salary = rs.getInt("salary");
						break;
					case "super_ssn":
						this.superSsn = rs.getString("super_ssn");
						break;
					case "dno":
						this.dno = rs.getInt("dno");
						break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
