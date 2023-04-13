package hello.proxy.config.v4_postprocessor.postprocessor;

import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.config.V1Config;
import hello.proxy.config.V2Config;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({V1Config.class, V2Config.class})
public class BeanPostProcessorConfig {

	// BeanPostProcessor과 구현체는 빈으로 등록만 하면 내부에서 자동으로 동작한다.
	@Bean
	public PackageLogTraceProxyPostProcessor packageLogTraceProxyPostProcessor(LogTrace logTrace){
		return new PackageLogTraceProxyPostProcessor("hello.proxy.app", getAdvisor(logTrace));
	}

	public Advisor getAdvisor(LogTrace logTrace){
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*", "save*", "order*");
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		return new DefaultPointcutAdvisor(pointcut, advice);
	}
}
