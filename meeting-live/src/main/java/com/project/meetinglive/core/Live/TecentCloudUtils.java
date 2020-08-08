package com.project.meetinglive.core.Live;

import java.io.File;

import com.project.meetinglive.common.util.MD5Encode;
import com.project.meetinglive.core.config.ApplicationConfig;

/**
 * 腾讯直播工具类
 * @author hejinguo
 * @version $Id: TecentCloudUtils.java, v 0.1 2020年8月7日 上午12:56:19
 */
public class TecentCloudUtils {
    /**
     * 这是推流防盗链的生成 KEY+ streamId + txTime
     * @param key 防盗链使用的key
     * @param streamId  通常为直播码.示例:bizid+房间id
     * @param txTime 到期时间
     * @return
     */
    public static String getSafeUrl(String key, String streamId, long txTime) {
        String input = new StringBuilder().append(key).append(streamId)
            .append(Long.toHexString(txTime).toUpperCase()).toString();
        String txSecret = null;
        txSecret = MD5Encode.stringToMD5(input);
        return txSecret == null ? "" : new StringBuilder().append("txSecret=").append(txSecret)
            .append("&").append("txTime=").append(Long.toHexString(txTime).toUpperCase())
            .toString();
    }

    /**
     * 获取推流秘钥
     * @return
     */
    public static String getPushSecretKey(String roomCode, Long txTime) {
        String safeUrl = getSafeUrl(ApplicationConfig.tecentCloud_live_safeChain, roomCode, txTime);
        String pushSecretKey = roomCode + "?" + safeUrl;
        return pushSecretKey;
    }

    /**
     * 获取Rtmp拉流地址
     * @param roomCode
     * @return
     */
    public static String getPullRtmpUrl(String roomCode) {
        String pullRtmpUrl = "rtmp://" + ApplicationConfig.tecentCloud_live_pullServerurl
                             + File.separator + ApplicationConfig.tecentCloud_live_appName
                             + File.separator
                             + roomCode;
        return pullRtmpUrl;
    }

    /**
     * 获取Flv拉流地址
     * @param roomCode
     * @return
     */
    public static String getPullFlvUrl(String roomCode) {
        String pullFlvUrl = "http://" + ApplicationConfig.tecentCloud_live_pullServerurl
                            + File.separator + ApplicationConfig.tecentCloud_live_appName
                            + File.separator + roomCode
                            + ".flv";
        return pullFlvUrl;
    }

    /**
     * 获取HLS拉流地址
     * @param roomCode
     * @return
     */
    public static String getPullHlsUrl(String roomCode) {
        String pullHlsUrl = "http://" + ApplicationConfig.tecentCloud_live_pullServerurl
                            + File.separator + ApplicationConfig.tecentCloud_live_appName
                            + File.separator + roomCode
                            + ".m3u8";
        return pullHlsUrl;
    }

    /**
     * 获取UDP拉流地址
     * @param roomCode
     * @return
     */
    public static String getPullUdpUrl(String roomCode) {
        String pullUdpUrl = "webrtc://" + ApplicationConfig.tecentCloud_live_pullServerurl
                            + File.separator + ApplicationConfig.tecentCloud_live_appName
                            + File.separator
                            + roomCode;
        return pullUdpUrl;
    }
}
