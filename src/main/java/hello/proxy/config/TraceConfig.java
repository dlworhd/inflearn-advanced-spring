package hello.proxy.config;

import hello.proxy.v3.LogTrace;
import hello.proxy.v3.ThreadLocalTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceConfig {
	@Bean
	LogTrace logInterface(){
		return new ThreadLocalTrace();
	}
}
