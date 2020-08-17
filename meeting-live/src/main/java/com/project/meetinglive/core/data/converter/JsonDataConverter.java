package com.project.meetinglive.core.data.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.meetinglive.common.constant.DataEncode;
import com.project.meetinglive.common.util.JsonUtil;
import com.project.meetinglive.core.data.request.JsonMessage;

/**
 * json消息转换器
 * @author hejinguo
 * @version $Id: JsonDataConverter.java, v 0.1 2019年11月17日 下午4:57:47
 */
public class JsonDataConverter extends AbstractHttpMessageConverter<Object> {
    private static final Logger logger = LoggerFactory.getLogger(JsonDataConverter.class);

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage httpInputMessage)
                                                                                                   throws IOException,
                                                                                                   HttpMessageNotReadableException {
        InputStream in = null;
        try {
            // 读取http请求体数据
            String requestData = StreamUtils.copyToString(httpInputMessage.getBody(),
                Charset.forName(DataEncode.UTF8));
            // 解析JSON，封装数据
            logger.info("请求参数信息:" + requestData);
            JsonMessage jsonMessage = JsonUtil.jsonToObject(requestData, JsonMessage.class);
            jsonMessage.setSourceMessage(requestData);
            return jsonMessage;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException,
                                                                             HttpMessageNotWritableException {
        OutputStream out = null;
        try {
            if (String.class == obj.getClass()) {
                String json = (String) obj;
                out = outputMessage.getBody();
                out.write(json.getBytes(DataEncode.UTF8));
                out.flush();
                return;
            }
            /**
             * QuoteFieldNames———-输出key时是否使用双引号,默认为true
             * WriteMapNullValue——–是否输出值为null的字段,默认为false
             * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
             * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
             * WriteNullStringAsEmpty—字符类型字段如果为null,输出为"",而非null
             * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
             */
            String json = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
            logger.info("返回消息信息:" + json);
            out = outputMessage.getBody();
            out.write(json.getBytes(DataEncode.UTF8));
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        // TODO Auto-generated method stub
        String jsonMediaType = MediaType.APPLICATION_JSON_VALUE;
        String getMediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        String multipartType = MediaType.MULTIPART_FORM_DATA_VALUE;
        String textType = MediaType.TEXT_PLAIN_VALUE;
        String mediaTypeNow = mediaType.toString();
        if (mediaTypeNow.indexOf(jsonMediaType) != -1 || mediaTypeNow.indexOf(getMediaType) != -1
            || mediaTypeNow.indexOf(multipartType) != -1 || mediaTypeNow.indexOf(textType) != -1) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean canWrite(MediaType mediaType) {
        // TODO Auto-generated method stub
        String jsonMediaType = MediaType.APPLICATION_JSON_VALUE;
        String mediaTypeNow = String.valueOf(mediaType);
        if (mediaTypeNow.indexOf(jsonMediaType) != -1) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return true;
    }

}
