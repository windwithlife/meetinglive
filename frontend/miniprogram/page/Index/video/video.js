const app = getApp()
function getRandomColor() {
  const rgb = []
  for (let i = 0; i < 3; ++i) {
    let color = Math.floor(Math.random() * 256).toString(16)
    color = color.length === 1 ? '0' + color : color
    rgb.push(color)
  }
  return '#' + rgb.join('')
}

Page({
  data: {
    countTime:30 * 60 * 60 * 1000,
    isShowMantle:false
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
