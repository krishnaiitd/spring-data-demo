package com.saas.avocado.springdatademo.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCaching
public class SpringDataDemoApplication {

	Logger logger = LoggerFactory.getLogger(SpringDataDemoApplication.class);

	@Value("${async.thread.core.pool.size:5}")
	protected int corePollSize;

	@Value("${async.thread.max.pool.size:15}")
	protected int maxPoolSize;

	@Value("${async.thread.linked.blocking.queue.capacity:50}")
	protected int queueCapacity;

	@Bean("threadPoolTaskExecutor")
	public ThreadPoolExecutor getAsyncExecutor() {

		logger.info("corePollSize {}", corePollSize);
		logger.info("maxPoolSize {}", maxPoolSize);
		logger.info("queueCapacity {}", queueCapacity);

		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				corePollSize,
				maxPoolSize,
				1000,
				TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(queueCapacity),
				new ThreadPoolExecutor.CallerRunsPolicy()
		);

		return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemoApplication.class, args);
	}

}
