// page/component/new-pages/user/user.js
Page({
  data:{
    titleArray:['美国', '中国', '巴西', '日本'],
    titleIsShow:false,
    titleDesc:"默认值",

    hospitalArr:['中山医院','华山医院','中山医院','华山医院'],
    hospitalIsShow:false,
    hospitalDesc:"默认值",

    region: ['广东省', '广州市', '海珠区'],
  },
  onReady(queryObj){
    const query = wx.createSelectorQuery()
    const userGender = query.select('#userGender')
    console.log('userGender: ', userGender);

  },

  bindRegionChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      region: e.detail.value
    })
  },

  changeHospitaIsShow(){
    this.setData({ hospitalIsShow:!this.data.hospitalIsShow })
  },
  bindHospitalPickerChange(event){
    const { picker, value, index } = event.detail;
    this.setData({hospitalDesc:value})
    this.changeHospitaIsShow();
  },

  changeTitleIsShow(){
    this.setData({ titleIsShow:!this.data.titleIsShow })
  },
  bindTitlePickerChange: function(event) {
    const { picker, value, index } = event.detail;
    this.setData({titleDesc:value})
    this.changeTitleIsShow();
  },
})