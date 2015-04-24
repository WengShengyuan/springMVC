
import java.sql.*; 
import java.util.*;
public class Conn2ASE {
    public static void main(String[] args) {
        try {
            Class.forName("com.sybase.jdbc4.jdbc.SybDriver").newInstance();
//            String url = "jdbc:sybase:Tds:198.216.1.3:5100/SYZN_Connect13";// 数据库名
//            sysProps.put("user", "sa"); // 设置数据库访问用户名
//            sysProps.put("password", "glzzjrh"); // 密码
            String url = "jdbc:sybase:Tds:192.168.137.4:5000/LOCALHOST";// 数据库名
            Properties sysProps = System.getProperties();
            sysProps.put("user", "sa"); // 设置数据库访问用户名
            sysProps.put("password", "123456"); // 密码
            Connection conn = DriverManager.getConnection(url, sysProps);
            Statement stmt = conn
                    .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            String sql = "select id,name,crdate from dbo.sysobjects where type='U'"; // 表
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("oject_id:"+rs.getString(1)+",oject_name:"+rs.getString(2)); // 取得第二列的值
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}