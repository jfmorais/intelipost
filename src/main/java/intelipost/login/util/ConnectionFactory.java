package intelipost.login.util;

import java.sql.Connection;
import java.sql.DriverManager;
import oracle.jdbc.driver.OracleDriver;

public abstract class ConnectionFactory {

	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection("string para conexao com banco");
			return con;
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}
}
