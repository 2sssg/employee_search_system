package uni.employee_search_system.employee_search_system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.employee_search_system.employee_search_system.commons.application.DBProperty;

@Slf4j
public class Jdbc {
	@Autowired DBProperty dbProperty;
	private Statement stmt;
	private Jdbc() {
		log.info(dbProperty.toString());
		String url = dbProperty.getUrl();
		try {
			Connection con = DriverManager.getConnection(url,dbProperty.getUsername(),dbProperty.getPassword());
			stmt = con.createStatement();
		} catch(Exception e) {
			log.error("     Connection Error");
			e.printStackTrace();
		}
	}
	private static class InnerClass {
		private static final Jdbc jdbc = new Jdbc();
	}

	public static Jdbc getInstance(){
		return InnerClass.jdbc;
	}

	public ResultSet exeSQuery(String query){
		ResultSet resultSet=null;
		try{
			resultSet = stmt.executeQuery(query);
		}catch (Exception e){
			log.error("        query Error");
			e.printStackTrace();
		}
		return resultSet;
	}

	public void exeUQuery(String query){
		try{
			stmt.executeUpdate(query);
		}catch (Exception e){
			log.error("        query Error");
			e.printStackTrace();
		}
	}
}
