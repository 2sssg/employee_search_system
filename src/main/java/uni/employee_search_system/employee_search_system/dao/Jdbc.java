package uni.employee_search_system.employee_search_system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConfigurationProperties(prefix = "my-db")
@Getter @Setter
public class Jdbc {

	private Connection conn;
	private String url;
	private String username;
	private String password;

	// 빈이 생성될 때 connection을 미리 설정해놓음
	@PostConstruct
	private void init() {
		try {
			conn = DriverManager.getConnection(this.url, this.username, this.password);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	// Bean Cycle이 다 됐을 때 connection을 끊는다.
	@PreDestroy
	private void destroy() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet executeQuery(String query){
		if (this.conn == null) {
			try {
				this.conn = DriverManager.getConnection(this.url, this.username, this.password);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		Statement stmt;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		try {
			System.out.println(query);
			ResultSet resultSet = stmt.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int executeUpdate(String query) {
		if (this.conn == null) {
			try {
				this.conn = DriverManager.getConnection(this.url, this.username, this.password);
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}

		Statement stmt;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

		try {
			int updateCount = stmt.executeUpdate(query);
			System.out.println(query);
			return updateCount;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
