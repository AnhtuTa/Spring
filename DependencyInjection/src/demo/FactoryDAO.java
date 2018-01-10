package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoryDAO {
	public static AbstractDAO getDAO() {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream("config.properties");
			prop.load(is);
			String database = prop.getProperty("database");
			switch (database) {
			case "1":
				return new MySQLDAO();
			case "2":
				return new PostgreDAO();
			case "3":
				return new MSSQLDAO();
			
			default:
				throw new NumberFormatException();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
