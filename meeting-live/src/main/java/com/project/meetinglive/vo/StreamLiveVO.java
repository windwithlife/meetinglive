package com.project.meetinglive.vo;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 直播推流、断流通知VO
 * @author hejinguo
 * @version $Id: StreamLiveVO.java, v 0.1 2020年8月8日 下午1:58:46
 */
public class StreamLiveVO implements Serializable {
    /**  */
    private static final long serialVersionUID = 3234269512965272655L;

    private Integer           event_type;

    private String            sign;

    private Integer           appid;

    private String            app;

    private String            appname;

    private String            stream_id;

    private String            channel_id;

    private BigInteger        event_time;

    private String            sequence;

    private String            node;

    private String            user_ip;

    private String            stream_param;

    private String            push_duration;

    private Integer           errcode;

    private String            errmsg;

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getStream_id() {
        return stream_id;
    }

    public void setStream_id(String stream_id) {
        this.stream_id = stream_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public BigInteger getEvent_time() {
        return event_time;
    }

    public void setEvent_time(BigInteger event_time) {
        this.event_time = event_time;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public String getStream_param() {
        return stream_param;
    }

    public void setStream_param(String stream_param) {
        this.stream_param = stream_param;
    }

    public String getPush_duration() {
        return push_duration;
    }

    public void setPush_duration(String push_duration) {
        this.push_duration = push_duration;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getEvent_type() {
        return event_type;
    }

    public void setEvent_type(Integer event_type) {
        this.event_type = event_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StreamLiveVO [event_type=");
        builder.append(event_type);
        builder.append(", sign=");
        builder.append(sign);
        builder.append(", appid=");
        builder.append(appid);
        builder.append(", app=");
        builder.append(app);
        builder.append(", appname=");
        builder.append(appname);
        builder.append(", stream_id=");
        builder.append(stream_id);
        builder.append(", channel_id=");
        builder.append(channel_id);
        builder.append(", event_time=");
        builder.append(event_time);
        builder.append(", sequence=");
        builder.append(sequence);
        builder.append(", node=");
        builder.append(node);
        builder.append(", user_ip=");
        builder.append(user_ip);
        builder.append(", stream_param=");
        builder.append(stream_param);
        builder.append(", push_duration=");
        builder.append(push_duration);
        builder.append(", errcode=");
        builder.append(errcode);
        builder.append(", errmsg=");
        builder.append(errmsg);
        builder.append("]");
        return builder.toString();
    }
}
