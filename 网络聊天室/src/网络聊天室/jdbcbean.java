package 网络聊天室;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.mysql.jdbc.ResultSetMetaData;

public class jdbcbean {
	private static String username;
	private static String password;
	private static String driver;
	private static String url;
	private static Connection connection;
	private static PreparedStatement pstat;
	private static ResultSet resultset;
	private static int i;
	static {
		InputStream in = jdbcbean.class.getResourceAsStream("jdbc.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			username = prop.getProperty("jdbc.username");
			password = prop.getProperty("jdbc.password");
			driver = prop.getProperty("jdbc.driver");
			url = prop.getProperty("jdbc.url");

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			throw new RuntimeException("读取数据库配置文件异常！", e);
		}

	}

	// public jdbcbean() {

	// }
	// *****数据库连接*******
	public static Connection getconnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return connection;
	}

	public static ResultSet cha(String sql) throws SQLException {
		pstat = connection.prepareStatement(sql);
		resultset = pstat.executeQuery();
		return resultset;

	}

	public static int updata(String sql) throws SQLException {
		pstat = connection.prepareStatement(sql);
		i = pstat.executeUpdate();
		return i;

	}

	public List<Map<String, Object>> result(String sql, List<?> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		resultset = pstat.executeQuery(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstat.setObject(index++, params.get(i));
			}
		}
		ResultSetMetaData metadata = (ResultSetMetaData) resultset.getMetaData();
		int len = metadata.getColumnCount();
		while (resultset.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < len; i++) {
				String name = metadata.getCatalogName(i + 1);
				Object value = resultset.getObject(name);
				if (value == null) {
					value = "";
				}
				map.put(name, value);
			}
			list.add(map);
		}
		return list;
	}

	public static void clean() throws SQLException {
		if (resultset != null) {
			resultset.close();
		}
		if (pstat != null) {
			pstat.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

}
