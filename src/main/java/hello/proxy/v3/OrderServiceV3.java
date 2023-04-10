package hello.proxy.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

	private final OrderRepositoryV3 orderRepository;

	public void save(String itemId) {
			orderRepository.save(itemId);
	}
}
