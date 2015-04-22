package org.company.core.common.interceptor;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
  
public class MethodCacheInterceptor implements MethodInterceptor, InitializingBean {  
  
    private Cache cache;  
  
    /** 
     * 设置缓存名 
     */  
    public void setCache(Cache cache) {  
        this.cache = cache;  
    }  
  
    /** 
     * 检查是否提供必要参数。 
     */  
    public void afterPropertiesSet() throws Exception {  
        Assert.notNull(cache, "A cache is required. Use setCache(Cache) to provide one.");  
    }  
  
    /** 
     * 主方法 如果某方法可被缓存就缓存其结果 方法结果必须是可序列化的(serializable) 
     */  
    public Object invoke(MethodInvocation invocation) throws Throwable {  
        String targetName = invocation.getThis().getClass().getName();  
        String methodName = invocation.getMethod().getName();  
        Object[] arguments = invocation.getArguments();  
        Object result;  
  
        String cacheKey = getCacheKey(targetName, methodName, arguments);  
        System.out.println("invoking...");
        Element element = cache.get(cacheKey);  
        System.out.println("cache Key:"+cacheKey);
        if (element == null) {  
        	System.out.println("miss");
            result = invocation.proceed();  
            element = new Element(cacheKey, (Serializable) result);  
            cache.put(element);  
            return element.getValue();  
        } else {
        	System.out.println("hit");
        	return element.getValue();  
        }
        
    }  
  
    /** 
     * creates cache key: targetName.methodName.argument0.argument1... 
     */  
    private String getCacheKey(String targetName, String methodName, Object[] arguments) {  
        StringBuffer sb = new StringBuffer();  
        sb.append(targetName).append(".").append(methodName);  
        if ((arguments != null) && (arguments.length != 0)) {  
            for (int i = 0; i < arguments.length; i++) {  
                sb.append(".").append(arguments[i]);  
            }  
        }  
  
        return sb.toString();  
    }  
}  