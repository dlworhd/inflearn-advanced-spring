package hello.proxy.app.config.v1_proxy;

import hello.proxy.app.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.app.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.app.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.v1.*;
import hello.proxy.v3.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

	@Bean
	public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
		OrderControllerV1Impl orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));
		return new OrderControllerInterfaceProxy(orderControllerV1, logTrace);
	}

	@Bean
	public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
		OrderServiceV1Impl orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
		return new OrderServiceInterfaceProxy(orderServiceV1, logTrace);
	}

	@Bean
	public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace){
		OrderRepositoryV1Impl orderRepositoryV1 = new OrderRepositoryV1Impl();
		return new OrderRepositoryInterfaceProxy(orderRepositoryV1, logTrace);
	}
}
