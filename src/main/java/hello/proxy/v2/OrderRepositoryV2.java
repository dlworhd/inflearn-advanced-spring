package hello.proxy.v2;


import hello.proxy.app.util.TraceId;
import hello.proxy.app.util.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

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
