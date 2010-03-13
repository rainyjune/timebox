/**
 * 
 */
package com.javaeye.sample.security;

import java.util.Map;


/**
 * @author Downpour
 */
public interface SecurityManager {
	    
    public Map<String, String> loadUrlAuthorities();
        
}
