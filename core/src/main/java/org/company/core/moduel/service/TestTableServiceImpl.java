package org.company.core.moduel.service;

import java.util.List;

import javax.annotation.Resource;

import org.company.core.moduel.dao.TestTableDao;
import org.company.core.moduel.domain.TestTable;
import org.springframework.cache.annotation.Cacheable;
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
	public TestTable findById(long id) throws Exception {
		return testTableDao.findById(id);
	}
	@Transactional
	@Override
	public void clearTable() throws Exception {
		testTableDao.clearTable();
	}

	
	@Override
	public List<TestTable> findByName(String name) throws Exception {
		return testTableDao.findByName(name);
	}
	@Transactional
	@Override
	public boolean batchAdd(int time) throws Exception {
		return testTableDao.batchAdd(time);
	}
	@Transactional
	@Override
	public boolean updateName(String oldName, String newName) throws Exception {
		TestTable t = testTableDao.findByName(oldName).get(0);
		t.setName(newName);
		testTableDao.save(t);
		return false;
	}



}
