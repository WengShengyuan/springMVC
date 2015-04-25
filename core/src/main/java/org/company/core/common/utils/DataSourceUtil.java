package org.company.core.common.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtil {


	/**
	 * 根据SQL返回查询结果
	 * 
	 * @param con
	 * @param SQL
	 * @return 结果集
	 * @throws Exception
	 */
	public static List<Map<String, Object>> query(Connection con,
			String SQL) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			ResultSet rs = con.createStatement().executeQuery(SQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (null != rs && rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					String name = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(name);
					map.put(name, value);
				}
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} 
	}

	/**
	 * 执行INSERT, UPDATE, DELETE
	 * @param con
	 * @param sql
	 * @return 影响的行数
	 * @throws Exception
	 */
	public static int execute(Connection con, String sql) throws Exception {
		Statement stmt = null;
		int value = -1;
		try {
			stmt = con.createStatement();
			value = stmt.executeUpdate(sql);
			return value;
		}catch(Exception e){
			throw e;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
