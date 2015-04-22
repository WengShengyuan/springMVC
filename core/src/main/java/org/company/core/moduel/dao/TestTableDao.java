package org.company.core.moduel.dao;

import java.util.List;

import org.company.core.moduel.domain.TestTable;
import org.company.frame.generic.dao.GenericDao;

public interface TestTableDao extends GenericDao<TestTable>{

	public boolean batchAdd(int time) throws Exception;
	
	public List<TestTable> findByName(String name) throws Exception;
	
}
