package com.project.meetinglive.core.spring.configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 *  FastJson消息转换器
 * @author hejinguo
 * @version $Id: FastJsonWebmvcConfiguration.java, v 0.1 2019年11月17日 下午5:35:44
 */
@Configuration
public class FastJsonWebmvcConfiguration implements WebMvcConfigurer {
    //    @Bean
    //    public JsonDataConverter converter() {
    //        return new JsonDataConverter();
    //    }
    /**
     * QuoteFieldNames———-输出key时是否使用双引号,默认为true
     * WriteMapNullValue——–是否输出值为null的字段,默认为false
     * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
     * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
     * WriteNullStringAsEmpty—字符类型字段如果为null,输出为"",而非null
     * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter<?> converter = iterator.next();
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                iterator.remove();
            }
        }
        //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
            SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteDateUseDateFormat);
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }
}
