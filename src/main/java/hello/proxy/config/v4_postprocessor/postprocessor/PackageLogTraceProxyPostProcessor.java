package hello.proxy.config.v4_postprocessor.postprocessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
@RequiredArgsConstructor
public class PackageLogTraceProxyPostProcessor implements BeanPostProcessor {

	private final String basePackage;
	private final Advisor advisor;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		log.info("Param BeanName = {}, Bean = {}", beanName, bean.getClass());
		String packageName = bean.getClass().getPackageName();
		if(!packageName.startsWith(basePackage)){
			return bean;
		};

		// Bean은 싱글톤이라 그냥 넣는지?
		ProxyFactory proxyFactory = new ProxyFactory(bean);
		proxyFactory.addAdvisor(advisor);

		Object proxy = proxyFactory.getProxy();

		log.info("Create Proxy target = {}, proxy = {}", bean.getClass(), proxy.getClass());

		return proxy;
	}
}
