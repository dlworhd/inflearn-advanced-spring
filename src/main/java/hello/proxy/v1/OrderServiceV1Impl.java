package hello.proxy.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1Impl implements OrderServiceV1 {

	private final OrderRepositoryV1 orderRepositoryV1;



	@Override
	public void orderItem(String itemId) {
		orderRepositoryV1.save(itemId);
	}
}
