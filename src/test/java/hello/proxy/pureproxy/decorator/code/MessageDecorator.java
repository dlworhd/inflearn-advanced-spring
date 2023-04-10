package hello.proxy.pureproxy.decorator.code;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MessageDecorator implements Component {
	private final Component component;

	@Override
	public String operation() {
		log.info("Message Decorator 실행");
		String result = component.operation();
		String decoResult = "****" + result + "****";
		log.info("Message DecoResult 전 = {}, 후 = {}", result, decoResult);
		return decoResult;
	}
}
