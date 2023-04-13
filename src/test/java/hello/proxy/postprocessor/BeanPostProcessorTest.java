package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class BeanPostProcessorTest {

	@Test
	void postProcessor(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

		// beanA라는 이름으로 B를 가져올 수 있는 이유는 AToBPostProcessor 덕분임
		// beanA(A클래스가) B로 형변환이 되면서 가능해진 것
		B beanA = applicationContext.getBean("beanA", B.class);
		beanA.helloB();

		Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(A.class));

	}


	@Slf4j
	@Configuration
	static class BeanPostProcessorConfig{


		@Bean(name = "beanA")
		public A a(){
			return new A();
		}

		@Bean
		public AToBPostProcessor aToBPostProcessor(){
			return new AToBPostProcessor();
		}
	}

	@Slf4j
	static class A{
		public void helloA() {
			log.info("hello A");
		}
	}

	@Slf4j
	static class B{
		public void helloB() {
			log.info("hello B");
		}
	}


	// Bean이 등록이 될 때 A로 형변환이 가능하면 B로 바꿔서 반환, 아니면 그대로 반환
	@Slf4j
	static class AToBPostProcessor implements BeanPostProcessor {

		// Bean initialization 후에 실행
		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			log.info("beanName = {}, bean = {}", beanName, bean);
			if(bean instanceof A){
				return new B();
			}
			return bean;
		}
	}


}
