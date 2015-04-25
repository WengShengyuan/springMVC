package org.company.frame.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ASEPool {

	private static ASEPool pool;  
    private ComboPooledDataSource dataSource;  
  
    static {
    	pool = new ASEPool();  
    }
  
    public ASEPool() { 
        dataSource = new ComboPooledDataSource("sybase");  
    }  
  
    public final static ASEPool getInstance() {  
        return pool;  
    }  
  
    public final Connection getConnection() {  
        try {  
            return dataSource.getConnection();  
        } catch (SQLException e) {  
            throw new RuntimeException("无法从数据源获取连接 ", e);  
        }  
    }  
  
    public static void main(String[] args) throws SQLException {  
        Connection con = null;  
        try {  
            con = MysqlPool.getInstance().getConnection();  
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM test_table");  
            while (rs.next()) {  
                System.out.println(rs.getObject(1));  
                System.out.println(rs.getObject(2));  
                  
            }  
        } catch (Exception e) {
        } finally {  
            if (con != null)  
                con.close();  
        }
    }

}
