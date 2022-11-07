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


@Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class Employee {

	private String fname;
	private String minit;
	private String lname;
	private String ssn;
	private Date bdate;
	private String address;
	private String sex;
	private int salary;
	private String superSsn;
	private int dno;


	public String toQuery() {
		String select = "SELECT * ";
		String from = "FROM EMPLOYEES ";
		StringBuilder where = toWhereQuery();

		return select + from + where.toString();

	}



	public String toQuery(List<String> wants) {
		StringBuilder selectBuilder = new StringBuilder("SELECT ");
		wants.forEach(r -> selectBuilder.append(r).append(", "));

		String select = selectBuilder.substring(0, selectBuilder.length() - 2);
		String from = "FROM EMPLOYEES ";
		StringBuilder where = toWhereQuery();

		return select + from + where;
	}

	private StringBuilder toWhereQuery() {
		StringBuilder whereQuery = new StringBuilder();
		if (fname != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND fname LIKE " : "WHERE fname LIKE ")
					.append(fname)
					.append(" ");
		}

		if (minit != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND minit LIKE " : "WHERE minit LIKE ")
					.append(minit)
					.append(" ");
		}

		if (lname != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND lname LIKE " : "WHERE lname LIKE ")
					.append(lname)
					.append(" ");
		}

		if (ssn != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND ssn LIKE " : "WHERE ssn LIKE ")
					.append(ssn)
					.append(" ");
		}

		if (bdate != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND bdate LIKE " : "WHERE bdate LIKE ")
					.append(bdate)
					.append(" ");
		}

		if (address != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND address LIKE " : "WHERE address LIKE ")
					.append(address)
					.append(" ");
		}

		if (sex != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND sex LIKE " : "WHERE sex LIKE ")
					.append(sex)
					.append(" ");
		}

		if (salary != 0) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND salary = " : "WHERE salary = ")
					.append(salary)
					.append(" ");
		}

		if (superSsn != null) {
			whereQuery
					.append(whereQuery.toString().contains("WHERE") ? "AND superSsn LIKE " : "WHERE superSsn LIKE ")
					.append(superSsn)
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

	//TODO to entity
}
