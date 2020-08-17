package com.project.meetinglive.core.Live;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.meetinglive.common.util.JsonUtil;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.live.v20180801.models.DropLiveStreamRequest;
import com.tencentcloudapi.live.v20180801.models.DropLiveStreamResponse;
import com.tencentcloudapi.live.v20180801.models.ForbidLiveStreamRequest;
import com.tencentcloudapi.live.v20180801.models.ForbidLiveStreamResponse;

/**
 * 直播流管理帮助类
 * @author hejinguo
 * @version $Id: LiveClientHelp.java, v 0.1 2020年8月9日 上午12:00:12
 */
public class LiveClientHelp {
    private static final Logger logger = LoggerFactory.getLogger(LiveClientHelp.class);

    /**
     * 断开直播流
     * @param pushDoMainUrl  推流域名
     * @param appName   推流路径,默认为live
     * @param roomCode  房间code(唯一)
     * @return
     */
    public static DropLiveStreamResponse dropLiveStream(String pushDoMainUrl, String appName,
                                                        String roomCode) {
        DropLiveStreamResponse resp = null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("DomainName", pushDoMainUrl);
            paramMap.put("AppName", appName);
            paramMap.put("StreamName", roomCode);
            String params = JsonUtil.writeObjectJSON(paramMap);
        DropLiveStreamRequest req = DropLiveStreamRequest.fromJsonString(params,
            DropLiveStreamRequest.class);
            resp = TecentCloudFactory.create().getLiveClient()
            .DropLiveStream(req);
        } catch (TencentCloudSDKException e) {
            logger.error("断开直播流处理异常,异常信息:message--->{}", e.toString());
        }
        return resp;
    }

    /**
     * 禁推直播流
     * @param pushDoMainUrl
     * @param appName
     * @param roomCode
     * @return
     */
    public static ForbidLiveStreamResponse forbidLiveStream(String pushDoMainUrl, String appName,
                                                            String roomCode) {
        ForbidLiveStreamResponse resp = null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("DomainName", pushDoMainUrl);
            paramMap.put("AppName", appName);
            paramMap.put("StreamName", roomCode);
            String params = JsonUtil.writeObjectJSON(paramMap);
            ForbidLiveStreamRequest req = ForbidLiveStreamRequest.fromJsonString(params,
                ForbidLiveStreamRequest.class);
            resp = TecentCloudFactory.create().getLiveClient().ForbidLiveStream(req);
        } catch (TencentCloudSDKException e) {
            logger.error("禁推直播流处理异常,异常信息:message--->{}", e.toString());
        }
        return resp;
    }
}
