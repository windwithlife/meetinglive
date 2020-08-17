package com.project.meetinglive.vo;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 直播截图通知VO
 * @author hejinguo
 * @version $Id: SnapshotLiveVO.java, v 0.1 2020年8月8日 下午2:20:15
 */
public class SnapshotLiveVO implements Serializable {
    /**  */
    private static final long serialVersionUID = 1784076314007342663L;
    private String stream_id;
    
    private String            channel_id;

    private BigInteger        create_time;

    private Integer           file_size;

    private Integer           width;

    private Integer           height;

    private String            pic_url;

    private String            pic_full_url;

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

    public BigInteger getCreate_time() {
        return create_time;
    }

    public void setCreate_time(BigInteger create_time) {
        this.create_time = create_time;
    }

    public Integer getFile_size() {
        return file_size;
    }

    public void setFile_size(Integer file_size) {
        this.file_size = file_size;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPic_full_url() {
        return pic_full_url;
    }

    public void setPic_full_url(String pic_full_url) {
        this.pic_full_url = pic_full_url;
    }

    @Override
    public String toString() {
        return "SnapshotLiveVO [stream_id=" + stream_id + ", channel_id=" + channel_id
               + ", create_time=" + create_time + ", file_size=" + file_size + ", width=" + width
               + ", height=" + height + ", pic_url=" + pic_url + ", pic_full_url=" + pic_full_url
               + "]";
    }
}
