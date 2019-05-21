package com.auts.lajitong;

import com.alibaba.fastjson.parser.ParserConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
//import org.springframework.web.client.RestTemplate;

/**
 *  智能回收箱入口
 *  @author zqh
 */
@SpringBootApplication(scanBasePackages = { "com.auts.lajitong.**" })
@PropertySources({ @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true) })
@MapperScan("com.auts.lajitong.mapper.**")
@EnableWebSocket
//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@EnableEurekaClient
public class LajitongServerMain {
    private static Logger logger = LogManager.getLogger(LajitongServerMain.class);

    static {
        ParserConfig.getGlobalInstance().addAccept("com.auts.lajitong.model.");
    }

//    /**
//     * rest接口.
//     * @return rest模板
//     */
//    @Bean
//    @LoadBalanced
//    RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    /**
     * 项目启动方法入口.
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(LajitongServerMain.class, args);

        logger.info("Lajitong server startup success!");
    }

    /**
     * 会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     * 要注意，如果使用独立的servlet容器，
     * 而不是直接使用springboot的内置容器，
     * 就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理。
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
