package hello.proxy.v1;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1Impl implements OrderRepositoryV1{

	@Override
	public void save(String itemId){
		if(itemId.equals("ex")){
			throw new IllegalStateException("예외 발생");
		}
		sleep(1000);
	}

	private void sleep(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
