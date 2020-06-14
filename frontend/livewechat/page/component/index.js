Page({
  data: {
    imgUrls: [
      {img:'/image/b3.jpg',path:'/page/component/details/details'},
      {img:'/image/b2.jpg',path:'/page/component/details/details'},
      {img:'/image/b1.jpg',path:'/page/component/details/details'}
    ],
    icons:[
      {
        id: 1,
        img: '../../imgs/index/icon_1.jpg',
        name: '现场直播',
        url: ''
      },
      {
        id: 2,
        img: '../../imgs/index/icon_12.jpg',
        name: '会议',
        url: ''
      },
      {
        id: 3,
        img: '../../imgs/index/icon_5.jpg',
        name: '专家频道',
        url: ''
      },
      {
        id: 4,
        img: '../../imgs/index/icon_6.jpg',
        name: '案例',
        url: ''
      }],
    indicatorDots: false,
    autoplay: false,
    interval: 3000,
    duration: 800,
  },
  tapBanner: function (e) {
    var path = this.data.imgUrls[e.target.dataset.id].path;
    wx.navigateTo({ url: path});
	},
})