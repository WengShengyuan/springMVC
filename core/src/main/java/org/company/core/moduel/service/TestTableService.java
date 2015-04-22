package org.company.core.moduel.service;

import java.util.List;

import org.company.core.moduel.domain.TestTable;
import org.company.frame.generic.service.GenericService;

public interface TestTableService extends GenericService<TestTable>{

	public boolean batchAdd(int time) throws Exception;
	
	public List<TestTable> findByName(String name) throws Exception;
	
	public boolean updateName(String oldName, String newName) throws Exception;
		
}
