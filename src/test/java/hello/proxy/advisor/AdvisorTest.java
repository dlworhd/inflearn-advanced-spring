package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceInterface;
import hello.proxy.common.service.ServiceInterfaceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;

@Slf4j
public class AdvisorTest {


	@Test
	void advisorTest(){
		ServiceInterface target = new ServiceInterfaceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		// 항상 PointCut이 True로 반환되게 임시 설정
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
		proxyFactory.addAdvisor(advisor);
		ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

		proxy.save();
		proxy.find();
	}


	@Test
	void pointCutTest(){
		ServiceInterface target = new ServiceInterfaceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new CustomPointCut(), new TimeAdvice());
		proxyFactory.addAdvisor(advisor);

		ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
		proxy.save();
		proxy.find();


	}


	static class CustomPointCut implements Pointcut{

		@Override
		public ClassFilter getClassFilter() {
			return ClassFilter.TRUE;
		}

		@Override
		public MethodMatcher getMethodMatcher() {
			return new CustomMethodMatcher();
		}



	}

	static class CustomMethodMatcher implements MethodMatcher{
		String matchName = "save";

		// 해당 matches에 넘어온 정보를 가지고 어드바이스를 적용할지 안 할지 판단할 수 있다.
		// save() 메서드를 호출하면서 포인트 컷에 물어본다. save() 메서드에 어드바이스를 적용할지 안 할지
		// True를 반환하는 경우에 부가 기능을 적용한다.
		// 그 이후에 실제 save() 메서드를 호출함
		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			boolean result = method.getName().equals(matchName);
			log.info("PointCut 호출: Method = {}, Target Class = {}", method.getName(), targetClass);
			log.info("PointCut 결과", result);
			return result;
		}

		// True인 경우에 matches가 대신 호출된다. isRuntime이 false인 경우 정적 클래스라고 가정을 하게 되기 때문에 캐싱을 하지만,
		// False인 경우에는 매개변수가 동적으로 변한다고 생각하기 때문에 캐싱을 하지 않음
		@Override
		public boolean isRuntime() {
			return false;
		}

		@Override
		public boolean matches(Method method, Class<?> targetClass, Object... args) {
			return false;
		}
	}


	@Test
	void advisorTest2(){
		ServiceInterface target = new ServiceInterfaceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("save");
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());
		proxyFactory.addAdvisor(advisor);

		ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

		proxy.save();
		proxy.find();

	}


	@Test
	void multiAdviceTest1(){

		ServiceInterface target = new ServiceInterfaceImpl();
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();

		pointcut.setMappedName("save");

		ProxyFactory proxyFactory1 = new ProxyFactory(target);
		DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(pointcut, new Advice1());
		proxyFactory1.addAdvisor(advisor1);
		ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

		ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
		DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(pointcut, new Advice2());
		proxyFactory2.addAdvisor(advisor2);
		ServiceInterface proxy2 = (ServiceInterface) proxyFactory1.getProxy();

		proxy2.save();
		proxy2.find();


	}


	@Slf4j
	static class Advice1 implements MethodInterceptor {

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			log.info("Advice1 호출");
			return invocation.proceed();
		}
	}

	@Slf4j
	static class Advice2 implements MethodInterceptor {

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			log.info("Advice2 호출");
			return invocation.proceed();
		}
	}

	// AOP 마다 프록시가 계속 생성되는 게 아니라 여러 Advisor를 적용하더라도 프록시는 하나만 쓴다. 주의 !

	@Test
	void multiAdviceTest2(){
		// 원하는 만큼 advisor 추가 가능
		DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
		DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());

		ServiceInterface target = new ServiceInterfaceImpl();
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.addAdvisor(advisor1);
		proxyFactory.addAdvisor(advisor2);

		ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
		proxy.save();
		proxy.find();

	}

}
