import {isLogin,getOpenId,exitLogin,invoke_post } from "../../common/tools";
Page({
  data: {
    pageBannerList: [],
    liveStartList:[],
    liveHistoryList:[]
  },
  onLoad(objectQuery){
    const data = {
      pageBannerList:[{
        id:123,
        advTitle:"标题",
        advPicPath:"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg",
        path:'/page/component/details/details'
      }],
      liveStartList:[{
        id:123,
        roomTitle:"roomTitle",
        roomPicPath:"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg",
        roomDesc:"roomDesc",
        userTrueName:"userTrueName",
        positionName:"positionName",
        roomStatus:0, //0:未开始 1:直播中 2:已结束
        liveStartDate:"liveStartDate",
      },{
        id:123,
        roomTitle:"roomTitle",
        roomPicPath:"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg",
        roomDesc:"roomDesc",
        userTrueName:"userTrueName",
        positionName:"positionName",
        roomStatus:1, //0:未开始 1:直播中 2:已结束
        liveStartDate:"liveStartDate",
      },{
        id:123,
        roomTitle:"roomTitle",
        roomPicPath:"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg",
        roomDesc:"roomDesc",
        userTrueName:"userTrueName",
        positionName:"positionName",
        roomStatus:2, //0:未开始 1:直播中 2:已结束
        liveStartDate:"liveStartDate",
      }],
      liveHistoryList:[{
        id:1234,
        roomTitle:"标题",
        roomPicPath:'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg',
        roomDesc:"直播内容",
        userTrueName:"讲师姓名",
        positionName:"职位名称",
        playNumber:1234,
      }]
    }
    const {pageBannerList,liveStartList,liveHistoryList} = data;
    this.setData({
      pageBannerList,liveStartList,liveHistoryList
    })

    invoke_post('https://service.koudaibook.com/meeting-server/api/advertService/getHomePage',{},(result)=>{
      console.log('result: ', result);
    },(errorMsg)=>{
      console.log('errorMsg: ', errorMsg);
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