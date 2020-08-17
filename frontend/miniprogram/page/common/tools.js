const app = getApp();
import {WX_OPEN_ID,WX_TOKEN,WX_CODE_KEY}  from "./const"
import Dialog from "@vant/weapp/dialog/dialog.js"

/**微信登录 */
export function doLogin(){
  wx.login({
    success (res) {
      const {code = ''} =  res;
      wx.setStorageSync(WX_CODE_KEY, code);
    }
  })
}

/**省市区查询 */
export function getRegionList(successCallback,pid=100000,level=1){
  invoke_post('https://service.koudaibook.com/meeting-server/api/userService/getRegionList',{pid,level},successCallback);
}

/** 判断登录接口*/
export function isLogin(successCallback){
    //"isLogin":Integer 是否登录(0:未登录 1:已登录)
    invoke_post('https://service.koudaibook.com/meeting-server/api/userService/validateUserLogin',{},function(result){
      const {isLogin} = result;
      if(isLogin == 0) console.log('-------isLogin-------','用户未登录');
      else if(isLogin == 1) console.log('-------isLogin-------','用户已登录');
      successCallback(isLogin);
    });
}
/**退出登录接口*/
export function exitLogin(successCallback){
    invoke_post('https://service.koudaibook.com/meeting-server/api/userService/loginOut',{},successCallback);
}
/**获取openid接口*/
export function getOpenId(successCallback){
    var wx_code = wx.getStorageSync(WX_CODE_KEY);
    invoke_post(
      "https://service.koudaibook.com/meeting-server/api/wechatService/getWechatOpenId",
      {code:wx_code},
      (result)=>{
        let {openId} = result;
        successCallback(openId)
      }
    );
}


const checkStatus = response => {
  if (response.statusCode >= 200 && response.statusCode < 300) {
    return response.data
  } else {
    Dialog.alert({ title: 'checkStatus_error', message:  response.errMsg || 'checkStatus_error'})
    return {};
  }
}
const jsLogin = (result) => {
  let {token='',status,data,message} = result;//status 响应状态(0:失败 1:成功  2:未登录)
  switch(status){
      case 0 :  {
        if(message.includes('请重新打开')){ //重新获取wx_code
          doLogin();
        }
        Dialog.alert({ title: 'jsLogin_status_0', message:message})
      }
      break;
      case 1 : {
        
      }
      break;
      case 2 :{
        doLogin();
        wx.switchTab({url:'/page/Index/square/index'})
      }
      break;
  }
  return data;
}

export function invoke_post(url='',params={},successCallback){
      var openId = wx.getStorageSync(WX_OPEN_ID);
      var token = wx.getStorageSync(WX_TOKEN);
      wx.request({
        url,
        method:"POST",
        success:function(response){
          let result = checkStatus(response);
          let data = jsLogin(result)
          const {token,openId} = data;
          console.log('invoke_post_response:>>>>>> ', data);
          if(!!token) wx.setStorageSync(WX_TOKEN, token);
          if(!!openId) wx.setStorageSync(WX_OPEN_ID, openId);
          
          successCallback(data);
        },
        fail:function(error){
          Dialog.alert({ title: 'invoke_post_error', message: JSON.stringify(error)})
        },
        data:{
            openId,token,
            version:1,
            category:1,
            platType:3,
            platForm:"jyonline", 
            data:params,
        }
    })
}

