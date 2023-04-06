package com.inflearn.advancedspring.util;

import com.inflearn.advancedspring.v3.FieldLogTrace;
import com.inflearn.advancedspring.v3.LogTrace;
import com.inflearn.advancedspring.v3.ThreadLocalTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceConfig {
	@Bean
	LogTrace logInterface(){
		return new ThreadLocalTrace();
	}
}
