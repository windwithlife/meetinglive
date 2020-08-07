import {isLogin,exitLogin,invoke_post,getOpenId,getRegionList } from "../common/tools";
Page({
  data:{
    result:[{
      left:"昵称",
      right:"",
    },{
      left:"头像",
      right:""
    },{
      left:"性别",
      right:""
    },{
      left:"所在城市",
      right:""
    },{
      left:"所在医院",
      right:""
    },{
      left:"职称",
      right:""
    }]
  },
  onReady(queryObj){
    const query = wx.createSelectorQuery()
    const userGender = query.select('#userGender')
    invoke_post('https://service.koudaibook.com/meeting-server/api/userService/getUserInfo',{},(result)=>{
      const {id,userNickName,headPic,userGrenderWx,provinceName,cityName,hospitalName,positionName} = result;   
      let newResult = this.data.result.map((item)=>{
        if(item.left == '昵称') item.right = userNickName;
        if(item.left == '头像') item.right = headPic;
        if(item.left == '性别') item.right = userGrenderWx;
        if(item.left == '所在城市') item.right = provinceName;
        if(item.left == '所在医院') item.right = hospitalName;
        if(item.left == '职称') item.right = positionName;
        return item;
      })
      this.setData({result:newResult})
    })
  },


})