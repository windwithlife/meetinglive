import {isLogin,exitLogin,invoke_post,getOpenId } from "../../common/tools";
const app = getApp();
Page({
  data: {
    pageBannerList: [],
    liveStartList:[],
    liveHistoryList:[],
    isShowLogin:true,
    userInfo:{}, //用户授权信息数据
  },
  bindgetuserinfo(res){
    const {userInfo} = res.detail;
    console.log('bindgetuserinfo---this.data.userInfo: ', this.data.userInfo);
    this.setData({ userInfo })
  },
  getPhoneNumber(e){
    const {errMsg,iv,encryptedData} = e.detail;
    const {avatarUrl,country,province,city,gender,nickName} = this.data.userInfo;
    getOpenId((openId)=>{
      let params = {
        userNickName:nickName,
        headPic:avatarUrl,
        userGrenderWx:gender,
        userCountryWx:country,
        userProvinceWx:province,
        userCityWx:city,
        encryptedData:encryptedData,
        iv:iv,
        openId:openId
      }
      invoke_post('https://service.koudaibook.com/meeting-server/api/wechatService/registerUser',params,(result)=>{
        let {openId,token} = result;
        const {WX_OPEN_ID,WX_TOKEN} = app.globalData;
        wx.setStorageSync(WX_OPEN_ID, openId);
        wx.setStorageSync(WX_TOKEN, token);
      },(errorMsg)=>{
        console.log('errorMsg: ', errorMsg);
      })
    })
  },
  doClick(event){
    const {currentTarget} = event;
    const {id} = currentTarget?.dataset;
    wx.navigateTo({
      url:`/page/Index/video/video?id=${id}`,
    })
  },
  onLoad(objectQuery){
    isLogin((flag)=>{
      //"isLogin":Integer 是否登录(0:未登录 1:已登录)
      if(flag == 1) this.setData({isShowLogin:false});
      else this.setData({isShowLogin:true});
    })
    invoke_post('https://service.koudaibook.com/meeting-server/api/advertService/getHomePage',{},(result)=>{
      const {pageBannerList,liveStartList,liveHistoryList} = result;
      this.setData({ liveStartList })
    },(errorMsg)=>{
      console.error('errorMsg: ', errorMsg);
    })
  },
  tapBanner: function (e) {
    var path = this.data.imgUrls[e.target.dataset.id].path;
    wx.navigateTo({ url: path});
  },
  liveClick(event){
    // wx.navigateTo({url:"/page/Index/video/video"})
    // https://service.koudaibook.com/meeting-server/api/wechatService/getWechatOpenId
    function successCallback(result){
      console.log('result: ', result);
    }
    function failCallback(errorMsg){
        console.log('errorMsg: ', errorMsg);
    }
    isLogin(successCallback,failCallback)
    // exitLogin(successCallback,failCallback);
  }
})