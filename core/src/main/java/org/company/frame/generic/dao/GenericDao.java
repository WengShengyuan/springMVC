package org.company.frame.generic.dao;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface GenericDao<T> {  
    public T save(T entity) throws Exception ;  
  
    public void delete(T entity) throws Exception ;  
    
    public T update(T entity) throws Exception ;  
  
    public List<T> findAll () throws Exception;  
    
    public T findById(String id) throws Exception ;  
    
    public List<T> queryByStringInMap(HashMap<String, List<Object>> map)throws Exception ;
    
    public List<T> queryByString(String sql) throws Exception ;
    
    public List<T> queryByStringEqualMap (HashMap<String, Object> map)  throws Exception;
    
    /**
     * 清空表
     * @throws Exception
     */
    public void clearTable() throws Exception;
    
    
}  