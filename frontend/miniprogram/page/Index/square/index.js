import {isLogin,exitLogin,invoke_post,getOpenId } from "../../common/tools";
import Dialog from "@vant/weapp/dialog/dialog.js"
import {WX_OPEN_ID,WX_TOKEN} from "../../common/const";
let currentPage = 1;
Page({
  data: {
    pageBannerList: [],
    liveList:[],
    liveHistoryList:[],
    isShowLogin:false,
    loginToastFlag:false,
    userInfo:{}, //用户授权信息数据
  },
  notLogin(){
    this.setData({loginToastFlag:false})
  },
  bindgetuserinfo(res){
    const userInfo =  res?.detail?.userInfo || '';
    console.log('userInfo: ', !!userInfo);
    if(!!userInfo) this.setData({ userInfo,loginToastFlag:true })
    else  Dialog.alert({ title: 'bindgetuserinfo', message:'用户信息获取失败，请重试'})
  },
  getPhoneNumber(e){
    const {errMsg,iv,encryptedData} = e.detail;
    if(!iv || !encryptedData) return;
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
        if(!!openId && !!token){
          wx.setStorageSync(WX_OPEN_ID, openId);
          wx.setStorageSync(WX_TOKEN, token);
          this.setData({isShowLogin:false,loginToastFlag:false})
        }
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
  init(){
    invoke_post('https://service.koudaibook.com/meeting-server/api/advertService/getLiveList',{
      currentPage,pageSize:10,
    },(result)=>{
      let {liveList,totalPage} = result;
      liveList = this.data.liveList.concat(liveList);
      currentPage++;
      this.setData({ liveList:liveList });
      if(currentPage <= totalPage) this.init();
    },(errorMsg)=>{
      console.error('errorMsg: ', errorMsg);
    })
  },
  onShow(objectQuery){
    isLogin((flag)=>{
      //"isLogin":Integer 是否登录(0:未登录 1:已登录)
      if(flag == 1) this.setData({isShowLogin:false});
      else this.setData({isShowLogin:true});
    })
    this.init();
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