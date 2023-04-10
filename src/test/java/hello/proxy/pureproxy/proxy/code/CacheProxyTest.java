package hello.proxy.pureproxy.proxy.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheProxyTest {

	@Test
	void noProxyTest(){
		RealSubject realSubject = new RealSubject();
		ProxyPatternClient proxyPatternClient = new ProxyPatternClient(realSubject);
		proxyPatternClient.execute();
		proxyPatternClient.execute();
		proxyPatternClient.execute();
	}

	@Test
	void cacheProxyTest(){
		RealSubject realSubject = new RealSubject();
		CacheProxy cacheProxy = new CacheProxy(realSubject);
		ProxyPatternClient proxyPatternClient = new ProxyPatternClient(cacheProxy);
		proxyPatternClient.execute();
		proxyPatternClient.execute();
		proxyPatternClient.execute();
	}

}