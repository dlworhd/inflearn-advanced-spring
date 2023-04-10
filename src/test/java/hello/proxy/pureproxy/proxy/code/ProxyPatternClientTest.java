package hello.proxy.pureproxy.proxy.code;

import org.junit.jupiter.api.Test;

class ProxyPatternClientTest {

	@Test
	void noProxyTest(){
		RealSubject realSubject = new RealSubject();
		ProxyPatternClient proxyPatternClient = new ProxyPatternClient(realSubject);

		proxyPatternClient.execute();
		proxyPatternClient.execute();
		proxyPatternClient.execute();
	}

}