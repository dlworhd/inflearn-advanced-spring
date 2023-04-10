package hello.proxy.pureproxy.decorator.code;

import org.junit.jupiter.api.Test;

class DecoratorTest {


	@Test
	void decoratorTest(){
		Component component1 = new RealComponent();
		Component component2 = new MessageDecorator(component1);
		DecoratorPatternClient client = new DecoratorPatternClient(component2);

		client.execute();
	}

	@Test
	void timeDecoTest(){
		Component component1 = new RealComponent();
		Component component2 = new MessageDecorator(component1);
		Component component3 = new TimeDecorator(component2);

		DecoratorPatternClient client = new DecoratorPatternClient(component3);
		client.execute();
	}
}