package com.project.meetinglive.core.Live;

import com.project.meetinglive.common.util.MD5Encode;
import com.project.meetinglive.core.config.ApplicationConfig;

/**
 * 腾讯直播工具类
 * @author hejinguo
 * @version $Id: TecentCloudUtils.java, v 0.1 2020年8月7日 上午12:56:19
 */
public class TecentCloudUtils {
    // 用于 生成推流防盗链的key
    public static final String key         = "83a08d18116f625fb77e930094f952e5";

    public static final String APPID       = "1256583477";

    public static final String bizid       = "koudaibook";

    // 用于主动查询和被动通知的key:API鉴定key
    public static final String API_KEY     = "83a08d18116f625fb77e930094f952e5";

    // API回调地址
    public static final String API_ADDRESS = "http://vod.koudaibook.com";
    /**推流URL*/
    public static final String push_url    = "livepusher.koudaibook.com";
    /**拉流URL*/
    public static final String pull_url    = "liveplayer.koudaibook.com";

    /**
     * 推流地址
     */
    public static final String PUSH_URL    = "rtmp://" + push_url + "/live/" + push_url + "_";

    /**
     * pc拉流地址
     */
    public static final String PULL_URL    = "http://" + pull_url + "/live/" + bizid + "_";

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
                             + roomCode;
        return pullRtmpUrl;
    }

    /**
     * 获取Flv拉流地址
     * @param roomCode
     * @return
     */
    public static String getPullFlvUrl(String roomCode) {
        String pullFlvUrl = "http://" + ApplicationConfig.tecentCloud_live_pullServerurl + roomCode
                            + ".flv";
        return pullFlvUrl;
    }

    /**
     * 获取HLS拉流地址
     * @param roomCode
     * @return
     */
    public static String getPullHlsUrl(String roomCode) {
        String pullHlsUrl = "http://" + ApplicationConfig.tecentCloud_live_pullServerurl + roomCode
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
                            + roomCode;
        return pullUdpUrl;
    }

    /**
     * 获取关闭直播的url关闭直播 需要发送请求给腾讯服务器,然后返回结果
     * @param id 需要关闭的房间ID
     * @return 关闭直播的url
     */
    public static String getCloseLiveUrl(String roomCode) {
        // 此请求的有效时间
        Long current = System.currentTimeMillis() / 1000 + 10;
        // 生成sign签名
        String sign = MD5Encode.stringToMD5(new StringBuffer().append(API_KEY).append(current)
            .toString());
        // 生成关闭的参数列表
        String params = new StringBuffer().append("&interface=Live_Channel_SetStatus")
            .append("&Param.s.channel_id=").append(roomCode).append("&Param.n.status=0")
            .append("&t=")
            .append(current).append("&sign=").append(sign).toString();

        // 拼接关闭URL
        String url = API_ADDRESS + "?appid=" + APPID + params;
        return url;
    }

    /**
     * 获取录播查询请求地址
     * @param id
     * @return
     */
    public static String getRecordUrl(String id) {
        Long current = (System.currentTimeMillis() + 60 * 60 * 24 * 1000) / 1000;
        String sign = MD5Encode.stringToMD5(new StringBuffer().append(API_KEY).append(current)
            .toString());
        String code = bizid + "_" + id;
        String params = new StringBuffer().append("&interface=Live_Tape_GetFilelist")
            .append("&Param.s.channel_id=").append(code).append("&t=").append(current)
            .append("&sign=").append(sign).toString();

        // 拼接URL
        String url = API_ADDRESS + "?appid=" + APPID + params;
        return url;
    }

}
