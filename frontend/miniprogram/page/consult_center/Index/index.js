import {invoke_post} from "../../common/tools";
Page({
    data: {
        informationList:[],
    },
    onLoad(objectQuery){
        const data = {
            informationList:[{
                id:1,
                advTitle:"资讯标题",
                advPicPath:'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3464775501,3653819025&fm=26&gp=0.jpg',
                advSource:"资讯详情链接地址",
                clickNumber:1234
            }]
        }
        const {informationList} = data;
        this.setData({
            informationList,
        })
        // invoke_post('https://service.koudaibook.com/meeting-server/api/advertService/getInformationList',{},(result)=>{
        //   const {data} = result;
        //   const {informationList} = data;
        //   this.setData({
        //     informationList,
        // })
        // },(errorMsg)=>{
        //   console.log('errorMsg: ', errorMsg);
        // })
      },
    tapBanner: function (e) {
        wx.navigateTo({ url: "page/consult_center/consult_detail/index",});
    },
  })