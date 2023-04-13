package hello.proxy.config;

import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceConfig {
	@Bean
	LogTrace logInterface(){
		return new ThreadLocalTrace();
	}
}
