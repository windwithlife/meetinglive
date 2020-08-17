const app = getApp()
import {isLogin,exitLogin,invoke_post,getOpenId,getRegionList } from "../../common/tools";

Page({
  data: {
    countTime:0,
    isShowMantle:false,
    resultData:{},
    regionList:[],
    pageQuery:{},

    isShowInfo_get:false, //是否显示信息录取弹窗
    titleArray:[], //省份数据
    titleIsShow:false, //省份selector展示
    info_get_list:[{
      type:"text",
      identify:"name",
      leftDesc:"姓名",
      rightVal:"",
    },{
      type:"select",
      identify:"provice",
      leftDesc:"省份",
      rightVal:"",
    },{
      type:"text",
      identify:"hospital",
      leftDesc:"医院",
      rightVal:"",
    },{
      type:"text",
      identify:"department",
      leftDesc:"科室",
      rightVal:"",
    }]
  },
  inputDeal(event){
    const {target} = event;
    const {value} = event.detail;
    let identify = target?.dataset?.identify;

    let info_get_list = this.data.info_get_list.map((item)=>{
      if(item.identify == identify) item.rightVal = value;
      return item;
    })
    this.setData({info_get_list:info_get_list});
  },
  changeProviceShow(){
    this.setData({ titleIsShow:!this.data.titleIsShow });
  },
  doSubmit(){
    console.log('this.data.info_get_list: ', this.data.info_get_list);
    if(!this.data.info_get_list.every((item)=>!!item.rightVal && item.rightVal != '请选择')) return;
    let params = {}
    this.data.info_get_list.forEach((item,idx) => {
      if(item.identify == 'name')  params.userTrueName = item.rightVal;
      if(item.identify == 'provice') params.provinceName = item.rightVal;
      if(item.identify == 'hospital') params.hospitalName = item.rightVal;
      if(item.identify == 'department') params.departmentName = item.rightVal;
    });
    invoke_post('https://service.koudaibook.com/meeting-server/api/userService/getUserInfo',{},(result)=>{
      const {id,userNickName,headPic,userGrenderWx,provinceName,cityName,hospitalName,positionName} = result;   
      params.id = id;
      invoke_post('https://service.koudaibook.com/meeting-server/api/userService/updateUserInfo',params,(result)=>{
        this.setData({isShowInfo_get:false})
      },(errorMsg)=>{
        console.log('errorMsg: ', errorMsg);
      })
    })
  },
  bindTitlePickerChange(event) { //省选择器 确认按钮点击
    const { picker, value, index } = event.detail;
    let info_get_list = this.data.info_get_list.map((item)=>{
      if(item.identify == 'provice') item.rightVal = value;
      return item;
    })
    this.setData({info_get_list:info_get_list})
    this.changeProviceShow();
  },
  dealCountDown(resultData){ //直播未开始 倒计时展示处理
    const {roomStatus,liveStartDate} = resultData;
    if(roomStatus == 0){
      let countTime = new Date(liveStartDate).getTime() - new Date().getTime();
      this.setData({
        isShowMantle:true,
        countTime:countTime
      })
    }
  },
  validateWriteUserInfo(){
    invoke_post('https://service.koudaibook.com/meeting-server/api/userService/validateWriteUserInfo',{},(result)=>{
      const {isWrite} = result;
      if(isWrite == 0) this.setData({isShowInfo_get:true})
    },(errorMsg)=>{
      console.log('errorMsg: ', errorMsg);
    })
  },
  getRegionList(){
    getRegionList((result)=>{
      let {regionList} = result;
      let arr = [];
      regionList.forEach((item)=>{arr.push(item.name)})
      this.setData({regionList,titleArray:arr})
    })
  },
  onLoad(objectQuery){
    this.setData({pageQuery:objectQuery})
  },
  onShow(){
    const {id=""} = this.data.pageQuery;
    this.validateWriteUserInfo();
    this.getRegionList();
    invoke_post('https://service.koudaibook.com/meeting-server/api/advertService/getLiveDetail',{
      id
    },(resultData)=>{
      this.setData({ resultData})
      this.dealCountDown(resultData);
    },(errorMsg)=>{
      console.log('errorMsg: ', errorMsg);
    })
  },
  onReady() {
    this.videoContext = wx.createVideoContext('myVideo')
  },
  countDownFinished(){
    this.setData({ isShowMantle:false})
  },
  videoErrorCallback(e) {
    console.error(e.detail.errMsg);
  },

})
