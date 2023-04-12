package hello.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteService {

	void save(){
		log.info("save() 호출");
	}
}
