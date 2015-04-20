package org.company.frame.generic.dao;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

/**
 * @Deprecated
 */
@SuppressWarnings(value = { "unchecked", "unused" })
@Repository("GenericDaoImpl")
public class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext
	protected EntityManager em;

	private Class<T> persistentClass;

	public GenericDaoImpl() {

	}

	public Class<T> getPersistentClass() {
		return this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T save(Object entity) throws Exception {
		try {
			em.persist(entity);
			return (T) entity;
		} catch (Exception e) {
			throw new Exception(getPersistentClass().getName()
					+ "查询数据库出错，请确认sql语句是否正确或者链接是否正确。");
		}
	}

	public T update(Object entity) throws Exception {
		try {

			Object object = em.merge(entity);
			return (T) object;
		} catch (Exception e) {
			throw new Exception(getPersistentClass().getName()
					+ "查询数据库出错，请确认sql语句是否正确或者链接是否正确。");
		}
	}

	public void delete(Object entity) throws Exception {
		try {
			Object object = em.merge(entity);
			em.remove(object);
		} catch (Exception e) {
			throw new Exception(getPersistentClass().getName()
					+ "查询数据库出错，请确认sql语句是否正确或者链接是否正确。");
		}
	}

	public T findById(String id) throws Exception {
		try {
			return (T) em.find(this.getPersistentClass(), id);
		} catch (Exception e) {
			throw new Exception(getPersistentClass().getName()
					+ "查询数据库出错，请确认sql语句是否正确或者链接是否正确。");
		}
	}

	public List<T> findAll() throws Exception {
		List<T> resultList = null;
		try {
			String sql = "from " + getPersistentClass().getName() + " obj ";
			TypedQuery<T> query = em
					.createQuery(sql, this.getPersistentClass());
			resultList = query.getResultList();
		} catch (Exception e) {
			throw new Exception(getPersistentClass().getName()
					+ "查询数据库出错，请确认sql语句是否正确或者链接是否正确。");
		}
		if (resultList == null || (resultList != null && resultList.size() < 1)) {
			// throw new BusinessWifiException("查询结果为空");
			return resultList;
		} else
			return resultList;

	}

	@Override
	public List<T> queryByStringInMap(HashMap<String, List<Object>> map)
			throws Exception {
		List<T> resultList = null;
		try {
			StringBuilder sql = new StringBuilder("SELECT o from "
					+ getPersistentClass().getName() + " o WHERE ");
			StringBuilder conStr = new StringBuilder("");
			// 拼接条件
			for (String key : map.keySet()) {
				List<Object> value = map.get(key);
				conStr = conStr.append(String.format(" o.%s in (:%s) and", key,
						key));
			}
			// 去掉最后一个and
			sql.append(conStr.toString().substring(0, conStr.length() - 4));
			Query query = em.createQuery(sql.toString());
			for (String key : map.keySet()) {
				query = query.setParameter(key, map.get(key));
			}
			resultList = query.getResultList();
		} catch (Exception e) {
			throw new Exception(getPersistentClass().getName()
					+ "查询数据库出错，请确认sql语句是否正确或者链接是否正确。");
		}
		if (resultList == null || (resultList != null && resultList.size() < 1)) {
			// throw new BusinessWifiException("查询结果为空");
			return resultList;
		} else
			return resultList;

	}

	@Override
	public List<T> queryByString(String sql) throws Exception {
		List<T> resultList = null;
		try {
			resultList = (List<T>) em.createQuery(sql).getResultList();
		} catch (Exception e) {
			throw new Exception(getPersistentClass().getName()
					+ "查询数据库出错，请确认sql语句是否正确或者链接是否正确。");
		}
		if (resultList == null || (resultList != null && resultList.size() < 1)) {
			// throw new BusinessWifiException("查询结果为空");
			return resultList;
		} else
			return resultList;

	}

	@Override
	public List<T> queryByStringEqualMap(HashMap<String, Object> map)
			throws Exception {
		List<T> resultList = null;
		try {
			StringBuilder sql = new StringBuilder("SELECT o from "
					+ getPersistentClass().getName() + " o WHERE ");
			StringBuilder conStr = new StringBuilder("");
			// 拼接条件
			for (String key : map.keySet()) {
				Object value = map.get(key);
				conStr = conStr.append(String.format(" o.%s = :%s and", key,
						key));
			}
			// 去掉最后一个and
			sql.append(conStr.toString().substring(0, conStr.length() - 4));
			Query query = em.createQuery(sql.toString());
			for (String key : map.keySet()) {
				query = query.setParameter(key, map.get(key));
			}
			resultList = query.getResultList();
		} catch (Exception e) {
			throw new Exception("查询数据库出错，请确认sql语句是否正确或者链接是否正确。"
					+ e.getMessage());
		}
		if (resultList == null || (resultList != null && resultList.size() < 1)) {
			return resultList;
			// throw new BusinessWifiException("查询结果为空");
		} else
			return resultList;
	}

	@Override
	public void clearTable() throws Exception {
		List<T> resultList = null;
		try {
			String sql = "from " + getPersistentClass().getName() + " obj ";
			TypedQuery<T> query = em
					.createQuery(sql, this.getPersistentClass());
			resultList = query.getResultList();
			for (T obj : resultList) {
				delete(obj);
			}
		} catch (Exception e) {
			throw new Exception("查询数据库出错，请确认sql语句是否正确或者链接是否正确。");
		}

	}

	@Override
	public List<T> findAll(int pageIndex, int resultPerPage) throws Exception {
		List<T> resultList = null;
		try {
			String sql = "from " + getPersistentClass().getName() + " obj ";
			TypedQuery<T> query = em
					.createQuery(sql, this.getPersistentClass())
					.setFirstResult(pageIndex * resultPerPage)
					.setMaxResults(resultPerPage);
			resultList = query.getResultList();
		} catch (Exception e) {
			throw new Exception(getPersistentClass().getName()
					+ "查询数据库出错，请确认sql语句是否正确或者链接是否正确。");
		}
		if (resultList == null || (resultList != null && resultList.size() < 1)) {
			// throw new BusinessWifiException("查询结果为空");
			return resultList;
		} else
			return resultList;

	}

	@Override
	public List<T> queryByStringInMap(HashMap<String, List<Object>> map,
			int pageIndex, int resultPerPage) throws Exception {
		List<T> resultList = null;
		try {
			StringBuilder sql = new StringBuilder("SELECT o from "
					+ getPersistentClass().getName() + " o WHERE ");
			StringBuilder conStr = new StringBuilder("");
			// 拼接条件
			for (String key : map.keySet()) {
				List<Object> value = map.get(key);
				conStr = conStr.append(String.format(" o.%s in (:%s) and", key,
						key));
			}
			// 去掉最后一个and
			sql.append(conStr.toString().substring(0, conStr.length() - 4));
			Query query = em.createQuery(sql.toString())
					.setFirstResult(pageIndex * resultPerPage)
					.setMaxResults(resultPerPage);
			for (String key : map.keySet()) {
				query = query.setParameter(key, map.get(key));
			}
			resultList = query.getResultList();
		} catch (Exception e) {
			throw new Exception(getPersistentClass().getName()
					+ "查询数据库出错，请确认sql语句是否正确或者链接是否正确。");
		}
		if (resultList == null || (resultList != null && resultList.size() < 1)) {
			// throw new BusinessWifiException("查询结果为空");
			return resultList;
		} else
			return resultList;
	}

	@Override
	public List<T> queryByString(String sql, int pageIndex, int resultPerPage)
			throws Exception {
		List<T> resultList = null;
		try {
			resultList = (List<T>) em.createQuery(sql)
					.setFirstResult(pageIndex * resultPerPage)
					.setMaxResults(resultPerPage).getResultList();
		} catch (Exception e) {
			throw new Exception(getPersistentClass().getName()
					+ "查询数据库出错，请确认sql语句是否正确或者链接是否正确。");
		}
		if (resultList == null || (resultList != null && resultList.size() < 1)) {
			// throw new BusinessWifiException("查询结果为空");
			return resultList;
		} else
			return resultList;
	}

	@Override
	public List<T> queryByStringEqualMap(HashMap<String, Object> map,
			int pageIndex, int resultPerPage) throws Exception {
		List<T> resultList = null;
		try {
			StringBuilder sql = new StringBuilder("SELECT o from "
					+ getPersistentClass().getName() + " o WHERE ");
			StringBuilder conStr = new StringBuilder("");
			// 拼接条件
			for (String key : map.keySet()) {
				Object value = map.get(key);
				conStr = conStr.append(String.format(" o.%s = :%s and", key,
						key));
			}
			// 去掉最后一个and
			sql.append(conStr.toString().substring(0, conStr.length() - 4));
			Query query = em.createQuery(sql.toString())
					.setFirstResult(pageIndex * resultPerPage)
					.setMaxResults(resultPerPage);
			for (String key : map.keySet()) {
				query = query.setParameter(key, map.get(key));
			}
			resultList = query.getResultList();
		} catch (Exception e) {
			throw new Exception("查询数据库出错，请确认sql语句是否正确或者链接是否正确。"
					+ e.getMessage());
		}
		if (resultList == null || (resultList != null && resultList.size() < 1)) {
			return resultList;
			// throw new BusinessWifiException("查询结果为空");
		} else
			return resultList;
	}

}