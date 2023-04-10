package hello.proxy.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

	public void execute(Callback callback){
		Long startTime = System.currentTimeMillis();
		callback.call();
		Long endTime = System.currentTimeMillis();
		Long resultTime = endTime - startTime;
		log.info("ResultTime = {}ms", resultTime);
	}
}
