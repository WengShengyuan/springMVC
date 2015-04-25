import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.company.core.common.utils.DataSourceUtil;
import org.company.frame.pool.ASEPool;
import org.company.frame.pool.MysqlPool;


public class DsUtilTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Connection conn1 = MysqlPool.getInstance().getConnection();
			Connection conn2 = ASEPool.getInstance().getConnection();
			List<Map<String, Object>> ls = DataSourceUtil.query(conn1 , "SELECT * FROM test_table");
			int r = DataSourceUtil.execute(conn2, "DELETE FROM test_table WHERE name='name556'"); 
			List<Map<String, Object>> c = DataSourceUtil.query(conn2, "SELECT COUNT(*) FROM test_table");
			System.out.println(ls.size()+"..."+r+"..."+c); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
