package org.company.frame.generic.service;

import javax.annotation.Resource;

import org.company.frame.generic.dao.GenericDao;
import org.springframework.transaction.annotation.Transactional;



 /**
 *  Class Name: GenericServiceImpl.java
 *  Function: 通用service,可以将通用service方法放入内部,(但是内部调用的是genericDao)
 *  此时genericDao的persistentClass还无法确定,所有有关persistentClass的方法还得自己写
 *  即:调用自己生成的dao如UserDao等
 *  
 *  Modifications:   
 *  
 *  @author "blueSummer"  DateTime 2015-1-6 上午9:15:35    
 *  @version 1.0 
 *  @param <T>
 */
/**
 * @Deprecated
 */
public abstract class GenericServiceImpl<T> implements GenericService<T>{

	@Resource(name = "GenericDaoImpl")
	private GenericDao<T> genericDao;
	
	@Transactional
	@Override
	public T save(T entity) throws Exception {
		return genericDao.save(entity);
	}

	@Transactional
	@Override
	public void delete(T entity) throws Exception {
		genericDao.delete(entity);
	}

	@Transactional
	@Override
	public T update(T entity) throws Exception {
		return genericDao.update(entity);
	}
	
	@Transactional
	@Override
	public void clearTable() throws Exception {
		genericDao.clearTable();
	}

//	@Override
//	public List<T> findAll() {
//		return genericDao.findAll();
//	}
//
//	@Override
//	public T findById(Long id) {
//		return genericDao.findById(id);
//	}

}
