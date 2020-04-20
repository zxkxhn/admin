package com.zxk.admin;

import cn.hutool.core.util.IdUtil;
import com.ss.core.frmawork.linklog.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.zxk.*")
public class Application{

	public static void main(String[] args) {
		MDC.put(Constants.LOG_TRACE_ID, IdUtil.fastSimpleUUID());
		SpringApplication.run(Application.class, args);
		log.info("启动成功!!");
	}

}
