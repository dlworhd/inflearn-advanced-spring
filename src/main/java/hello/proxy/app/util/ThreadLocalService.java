package hello.proxy.app.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

	private ThreadLocal<String> nameStore = new ThreadLocal<>();

	public String logic(String name) {
		log.info("저장 Name = {} -> NameStore -> {}", name, nameStore.get());
		nameStore.set(name);
		sleep(1000);
		log.info("NameStore -> {}", nameStore.get());
		return nameStore.get();
	}

	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
