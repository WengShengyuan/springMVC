package org.company.core.moduel.dao;

import java.util.HashMap;
import java.util.List;

import org.company.core.moduel.domain.TestTable;
import org.company.frame.generic.dao.GenericDaoImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("TestTableDaoImpl")
public class TestTableDaoImpl extends GenericDaoImpl<TestTable> implements
		TestTableDao {

	@Override
	public boolean batchAdd(int time) throws Exception {

		for (int i = 0; i < time; i++) {
			TestTable t = new TestTable();
			t.setName(String.format("name%s", i));
			save(t);
		}
		return true;
	}

	@Override
	public List<TestTable> findByName(String name) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);

		return queryByStringEqualMap(map);

	}

}
