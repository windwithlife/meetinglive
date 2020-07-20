Page({
  data: {
    swiperData: [
      {img:'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg',path:'/page/component/details/details'},
      {img:'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2751001779,3002194522&fm=26&gp=0.jpg',path:'/page/component/details/details'},
      {img:'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg',path:'/page/component/details/details'}
    ],
    liveDlivery:[
      {
        img:'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg',
      },
      {
        img:'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg',
      },
      {
        img:'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg',
      },
    ],
    playback:[{
      img:'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg',
    },{
      img:'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg',
    },{
      img:'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg',
    }]
  },
  onLoad(objectQuery){
   
  },
  tapBanner: function (e) {
    var path = this.data.imgUrls[e.target.dataset.id].path;
    wx.navigateTo({ url: path});
  },
  liveClick(event){
    wx.navigateTo({url:"/page/Index/video/video"})
    // wx.login({
    //   success (res) {
    //     if (res.code) {
    //       console.log('res.code: ', res.code);
    //       wx.request({
    //         url: 'https://test.com/onLogin',
    //         data: {
    //           code: res.code //用户登录凭证（有效期五分钟）
    //         }
    //       })
    //     } else {
    //       console.error('登录失败！' + res.errMsg)
    //     }
    //   }
    // })
  }
})