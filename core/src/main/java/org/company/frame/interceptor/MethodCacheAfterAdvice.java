package org.company.frame.interceptor;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.ehcache.Cache;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
    
public class MethodCacheAfterAdvice implements AfterReturningAdvice, InitializingBean    
{    
    
    private Cache cache;    
    
    public void setCache(Cache cache) {    
        this.cache = cache;    
    }    
    
    public MethodCacheAfterAdvice() {    
        super();    
    }    
    @Transactional
    public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {    
        String className = arg3.getClass().getName();    
        List list = cache.getKeys();    
        System.out.println("invoking...");
        System.out.println("removing cache key:"+className+".*");
        for(int i = 0;i<list.size();i++){    
            String cacheKey = String.valueOf(list.get(i));    
            if(cacheKey.startsWith(className)){    
            	System.out.println("remving key:"+cacheKey);
                cache.remove(cacheKey);    
            }    
        }    
    }    
    
    public void afterPropertiesSet() throws Exception {    
        Assert.notNull(cache, "Need a cache. Please use setCache(Cache) create it.");    
    }      
}   