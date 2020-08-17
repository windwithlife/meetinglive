package com.project.meetinglive.core.spring.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author WJL
 * 系统启动判断加载redis
 */
@Component
public class RedisInitConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RedisInitConfiguration.class);

    /**
     * 初始化redis数据
     */
    @PostConstruct
    public void initRedisConfig() {

    }
}
