package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class DecoratorPatternClientTest {

	@Test
	void decoratorPatternTest(){
		Component realComponent = new RealComponent();
		DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(realComponent);

		decoratorPatternClient.execute();
		decoratorPatternClient.execute();
		decoratorPatternClient.execute();
	}

}