package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

	@Test
	public void reflection0(){
		Hello target = new Hello();
		log.info("callA start");
		String resultA = target.callA();
		log.info("result = {}", resultA);

		log.info("callB start");
		String resultB = target.callB();
		log.info("result = {}", resultB);
	}


	@Slf4j
	static class Hello{

		public String callA(){
			log.info("callA()");
			return "A";
		}

		public String callB(){
			log.info("callB()");
			return "B";
		}
	}

	@Test
	void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

		// 클래스에 대한 참조를 얻음
		Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
		Hello target = new Hello();

		// 메서드에 대한 참조를 얻음
		Method methodCallA = classHello.getMethod("callA");

		// invoke는 동적으로 호출하기 위해서 사용함(실제 인스턴스를 invoke() 메서드의 인자로 넣어 호출)
		// callA라는 메서드를 호출한 반환 값을 받는 듯?
		Object resultA = methodCallA.invoke(target);
		log.info("result A = {}", resultA);

		// invoke는 동적으로 호출하기 위해서 사용함(실제 인스턴스를 invoke() 메서드의 인자로 넣어 호출)
		// callB라는 메서드를 호출한 반환 값을 받는 듯?
		Method methodCallB = classHello.getMethod("callB");
		Object resultB = methodCallB.invoke(target);
		log.info("result B = {}", resultB);
	}


	@Test
	void reflection2() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
		Hello target = new Hello();
		Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

		Method methodCallA = classHello.getMethod("callA");
		dynamicCall(methodCallA, target);

		Method methodCallB = classHello.getMethod("callB");
		dynamicCall(methodCallB, target);

	}

	private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
		log.info("start");
		Object result = method.invoke(target);
		log.info("result = {}", result);
	}


}
