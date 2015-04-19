package org.company.core.common.comstatic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value="ConfigStatic") 
public class ConfigStatic {

	@Value("${TESTPARAM}")  
    private String TESTPARAM;

	public String getTESTPARAM() {
		return TESTPARAM;
	}

	public void setTESTPARAM(String tESTPARAM) {
		TESTPARAM = tESTPARAM;
	}  
  
    
}
