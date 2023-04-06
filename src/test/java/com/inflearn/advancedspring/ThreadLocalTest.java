package com.inflearn.advancedspring;

import com.inflearn.advancedspring.util.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalTest {

	ThreadLocalService threadLocalService = new ThreadLocalService();

	@Test
	void threadLocalTest(){

		// 실행하기 전 세팅
		Runnable userA = () -> {
			threadLocalService.logic("userA");
		};
		Runnable userB = () -> {
			threadLocalService.logic("userB");
		};

		// 실행할 로직을 가지고 Thread 생성
		Thread threadA = new Thread(userA);
		Thread threadB = new Thread(userB);


		// Thread 이름 지정
		threadA.setName("Thread-A");
		threadB.setName("Thread-B");

		threadA.start();
		sleep(100);
		threadB.start();
		sleep(2000);

		log.info("main exit");
	}

	private void sleep(int mills){
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
