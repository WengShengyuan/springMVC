package org.company.core.moduel.service;

import java.util.List;

import javax.annotation.Resource;

import org.company.core.moduel.dao.TestTableDao;
import org.company.core.moduel.domain.TestTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("TestTableServiceImpl")
public class TestTableServiceImpl implements TestTableService{

	@Resource(name="TestTableDaoImpl")
	private TestTableDao testTableDao;
	
	@Transactional
	@Override
	public TestTable save(TestTable entity) throws Exception {
		return testTableDao.save(entity);
	}

	@Transactional
	@Override
	public void delete(TestTable entity) throws Exception {
		testTableDao.delete(entity);
	}

	@Transactional
	@Override
	public TestTable update(TestTable entity) throws Exception {
		return testTableDao.update(entity);
	}

	@Override
	public List<TestTable> findAll() throws Exception {
		return testTableDao.findAll();
	}

	@Override
	public TestTable findById(String id) throws Exception {
		return testTableDao.findById(id);
	}

	@Transactional
	@Override
	public void clearTable() throws Exception {
		testTableDao.clearTable();
	}

}
