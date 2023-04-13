package hello.proxy.config.v1_proxy;

import hello.proxy.app.v2.*;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcretProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

	@Bean
	public OrderControllerV2 orderControllerV2(LogTrace trace){
		OrderControllerV2 orderControllerV2 = new OrderControllerV2(orderServiceV2(trace));

		 return new OrderControllerConcreteProxy(orderControllerV2, trace);
	}

	@Bean
	public OrderServiceV2 orderServiceV2(LogTrace trace){

		OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepositoryV2(trace));
		return new OrderServiceConcretProxy(orderServiceV2, trace);
	}

	@Bean
	public OrderRepositoryV2 orderRepositoryV2(LogTrace trace){

		OrderRepositoryV2 orderRepositoryV2 = new OrderRepositoryV2();
		return new OrderRepositoryConcreteProxy(orderRepositoryV2, trace);
	}
}
