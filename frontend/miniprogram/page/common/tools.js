/**
 * @param {*} successCallback 
 * @param {*} failCallback 
 * 判断登录接口
 */
export function isLogin(successCallback,failCallback){
    invoke_post('https://service.koudaibook.com/meeting-server/api/userService/validateUserLogin',{},successCallback,failCallback);
}
/**
 * 退出登录接口
 * @param {*} successCallback 
 * @param {*} failCallback 
 */
export function exitLogin(successCallback,failCallback){
    invoke_post('https://service.koudaibook.com/meeting-server/api/userService/loginOut',{},successCallback,failCallback);
    
}
/**
 * 获取openid接口
 * @param {*} successCallback 
 * @param {*} failCallback 
 */
export function getOpenId(successCallback,failCallback){
    wx.login({
        success (res) {
          if (res.code) {
            const code = res.code;
            invoke_post(
                "https://service.koudaibook.com/meeting-server/api/wechatService/getWechatOpenId",
                {code},
                successCallback,failCallback
            )
          } else {
            console.error('获取code失败' + res.errMsg)
          }
        }
      })
}

export function invoke_post(url='',params={},successCallback,failCallback){
    wx.request({
        url,
        method:"POST",
        success:successCallback,
        fail:failCallback,
        data:{
            version:1,
            category:1,
            platType:3,
            platForm:"jyonline", 
            data:params,
        }
    })
}
