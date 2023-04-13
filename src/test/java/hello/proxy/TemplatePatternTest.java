package hello.proxy;

import hello.proxy.trace.template.AbstractTemplate;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplatePatternTest {

	LogTrace logTrace;

	@Test
	void TemplateInnerClass(){
		AbstractTemplate<String> template1 = new AbstractTemplate(logTrace) {
			@Override
			public String call() {
				return "Business Logic V1 Execute!";
			}
		};

		template1.execute("V1 Execute");

		AbstractTemplate<String> template2 = new AbstractTemplate(logTrace) {
			@Override
			public String call() {
				return "Business Logic V2 Execute!";
			}
		};

		template2.execute("V2 Execute");
	}

}
