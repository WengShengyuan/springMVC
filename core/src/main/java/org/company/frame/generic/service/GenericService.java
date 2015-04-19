package org.company.frame.generic.service;

import java.util.List;

public interface GenericService<T> {
	   public T save(T entity) throws Exception ;  
	   
	    public void delete(T entity) throws Exception;  
	    
	    public T update(T entity) throws Exception;  
	  
	    public List<T> findAll() throws Exception;  
	    
	    public T findById(String id) throws Exception; 
	    
	    public void clearTable() throws Exception;
}
