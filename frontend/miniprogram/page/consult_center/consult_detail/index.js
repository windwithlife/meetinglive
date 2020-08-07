import {invoke_post} from "../../common/tools"
Page({
    data: {
    },
    onLoad(objectQuery){
        const {id} = objectQuery;
        
        invoke_post('https://service.koudaibook.com/meeting-server/api/advertService/getInformationDetail',{
            id,
        },(result)=>{
            console.log('result: ', result);
        },(errorMsg)=>{
          console.log('errorMsg: ', errorMsg);
        })
      },
  })