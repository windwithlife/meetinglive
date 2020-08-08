package com.project.meetinglive.core.Live;

import com.project.meetinglive.core.config.ApplicationConfig;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.live.v20180801.LiveClient;

/**
 * 腾讯云API
 * @author hejinguo
 * @version $Id: TecentCloudFactory.java, v 0.1 2020年8月8日 下午11:29:34
 */
public class TecentCloudFactory {
    private static Credential credential;

    private TecentCloudFactory() {
        credential = new Credential(ApplicationConfig.tecentCloud_live_SecretId,
            ApplicationConfig.tecentCloud_live_SecretKey);
    }

    private static class TecentCloudHolder {
        private static TecentCloudFactory INSTACE = new TecentCloudFactory();
    }

    public static TecentCloudFactory create() {
        return TecentCloudFactory.TecentCloudHolder.INSTACE;
    }

    /**
     * 获取直播流客户端操作对象
     * @return
     */
    public LiveClient getLiveClient() {
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("live.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        LiveClient client = new LiveClient(credential, "ap-shanghai", clientProfile);
        return client;
    }

}
