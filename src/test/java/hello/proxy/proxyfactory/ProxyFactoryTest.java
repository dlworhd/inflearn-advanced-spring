package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceInterface;
import hello.proxy.common.service.ServiceInterfaceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class ProxyFactoryTest {


	@Test
	@DisplayName("인터페이스가 있으면 JDK 동적 프록시 적용")
	void jdkTest() {
		ServiceInterface target = new ServiceInterfaceImpl();

		// 이 때 target(사용할 클래스를 넣음)
		ProxyFactory proxyFactory = new ProxyFactory(target);

		// 적용시킬 부가 기능 추가
		proxyFactory.addAdvice(new TimeAdvice());

		// Advice가 적용된 Proxy 객체 반환
		ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

		log.info("Target Class = {}", target.getClass());
		log.info("ServiceInterfaceProxy Class = {}", proxy.getClass());

		proxy.save();

		Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
		Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
		Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
	}

	@Test
	@DisplayName("구체 클래스에 CGLIB 사용")
	void cglibTest() {
		// 구체 클래스
		ConcreteService target = new ConcreteService();

		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.addAdvice(new TimeAdvice());

		ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

		proxy.save();

		Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
		Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
		Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
	}

	@Test
	@DisplayName("인터페이스를 구현하고 있다고 하더라도 CGLIB을 사용, 인터페이스 기반 프록시가 아닌 클래스 기반 프록시를 사용")
	void proxyTargetTest() {
		ServiceInterface target = new ServiceInterfaceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.addAdvice(new TimeAdvice());

		// 인터페이스를 구현하고 있다고 하더라도 CGLIB으로 사용 가능 -> CGLIB 강제 사용
		proxyFactory.setProxyTargetClass(true);

		ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

		log.info("Target Class = {}", target.getClass());
		log.info("Proxy Class = {}", proxy.getClass());

		proxy.save();

		Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
		Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
		Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();

	}
}
