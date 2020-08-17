import {WX_CODE_KEY,WX_OPEN_ID,WX_TOKEN} from "./page/common/const";
import {doLogin} from "./page/common/tools";
App({
  onShow: function () {
    /**生命周期回调——监听小程序启动或切前台。*/
    console.log('App Show')
    wx.checkSession({
      success(){
        console.info('session_key 未过期，并且在本生命周期一直有效');
        var wx_code = wx.getStorageSync(WX_CODE_KEY);
        if(!wx_code) {
          console.info('session_key 未过期;但是wx_code缓存不存在_进行重新登录');
          doLogin(); //如果本地缓存中不存在 授权码 则需要再次进行登录; 
        }
      },
      fail(){
        console.info('session_key 已经失效，需要重新执行登录流程');
        doLogin();
      }
    })
  },
  globalData: {
  }
})
