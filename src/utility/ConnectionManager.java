package utility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager {
	public static Properties loadPropertiesFile() throws Exception {
		Properties prop = new Properties();
		InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(in);
		in.close();
		return prop;
	}

	public static Connection getConnection() throws Exception {

		Properties properties = loadPropertiesFile();

		String driver = properties.getProperty("driver");
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");

		Class.forName(driver);
		Connection con = null;
		con = DriverManager.getConnection(url, username, password);
		if (con != null) {
			System.out.println("Connection Established");
		} else {
			System.out.println("Check your Connection");
		}
		return con;
	}
}
