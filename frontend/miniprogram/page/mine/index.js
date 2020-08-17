import {isLogin,exitLogin,invoke_post,getOpenId,getRegionList } from "../common/tools";
Page({
  data:{
    result:[{
      left:"昵称",
      right:"",
    },{
      left:"姓名",
      right:"",
    },{
      left:"头像",
      right:""
    },{
      left:"性别",
      right:""
    },{
      left:"城市",
      right:""
    },{
      left:"医院",
      right:""
    },{
      left:"科室",
      right:""
    }]
  },
  onShow(queryObj){
    const query = wx.createSelectorQuery()
    const userGender = query.select('#userGender')
    invoke_post('https://service.koudaibook.com/meeting-server/api/userService/getUserInfo',{},(result)=>{
      const {id,userNickName,headPic,userGrenderWx,provinceName,cityName,hospitalName,positionName,userTrueName} = result;   
      let newResult = this.data.result.map((item)=>{
        if(item.left == '昵称') item.right = userNickName;
        if(item.left == '姓名') item.right = userTrueName;
        if(item.left == '头像') item.right = headPic;
        if(item.left == '性别') userGrenderWx==1 ? item.right = '男' : item.right = '女'
        if(item.left == '城市') item.right = provinceName;
        if(item.left == '医院') item.right = hospitalName;
        if(item.left == '科室') item.right = positionName;
        return item;
      })
      this.setData({result:newResult})
    })
  },


})