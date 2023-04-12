package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

	/**
	 * 실행 로직 !
	 * 1. 클라이언트가 동적 프록시의 call() 메서드 실행
	 * 2. 동적 프록시가 InvocationHandler의 invoke() 메서드를 실행할 수 있는데, 구현체인 TimeInvocationHandler가 있기 때문에, TimeInvocationHandler의  invoke를 실행할 수 있게 된다.
	 * 3. TimeInvocationHandler가 내부 로직을 실행하고, invoke() 메서드를 호출하여 실제 객체의 call() 메서드가 호출된다.
	 */


	@Test
	void dynamicA(){
		AInterface target = new AImpl();
		TimeInvocationHandler timeInvocationHandler = new TimeInvocationHandler(target);

		AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, timeInvocationHandler);
		proxy.call();
		log.info("Target Class = {}", target.getClass());
		log.info("Proxy Class = {}", proxy.getClass());
	}

	@Test
	void dynamicB(){
		BInterface target = new BImpl();
		TimeInvocationHandler timeInvocationHandler = new TimeInvocationHandler(target);

		BInterface proxy = (BInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{BInterface.class}, timeInvocationHandler);
		proxy.call();
		log.info("Target Class = {}", target.getClass());
		log.info("Proxy Class = {}", proxy.getClass());
	}
}
