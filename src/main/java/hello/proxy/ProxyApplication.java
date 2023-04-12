package hello.proxy;

import hello.proxy.app.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import hello.proxy.app.config.v3_proxyfactory.ProxyFactoryConfigV1;
import hello.proxy.app.config.v3_proxyfactory.ProxyFactoryConfigV2;
import hello.proxy.config.DynamicProxyBasicConfig;
import hello.proxy.v3.LogTrace;
import hello.proxy.v3.ThreadLocalTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(ProxyFactoryConfigV2.class)
@SpringBootApplication(scanBasePackages = "hello.proxy.app.config.v3_proxyfactory")
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	// 모든 예제에서 사용하기 위함
	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalTrace();
	}
}
