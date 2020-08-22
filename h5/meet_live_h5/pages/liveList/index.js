import React, { useState,useEffect} from 'react';
import "./index.less";
import LinesEllipsis from 'react-lines-ellipsis'
import {invoke_post} from "../../common/index"



export default class Live extends React.Component{
  constructor(props){
    super(props);
    this.currentPage = 1;
    this.state = {
      liveList:[],
    }
  }
  async initList(){
    let data = await invoke_post('https://service.koudaibook.com/meeting-server/api/advertService/getLiveList',{
            currentPage:this.currentPage,pageSize:10
          }).then((result)=>result?.data);
          
    let {totalPage,liveList=[]} = data;
    let liveList_state = this.state.liveList;
    this.setState({
      liveList:liveList_state.concat(liveList)
    },()=>{
      if(this.currentPage < totalPage) {
        this.currentPage++;
        this.initList();
      }
    });
  }
  componentDidMount(){
    try{
      this.initList();
    }catch(error){
      console.error('error: ', error);
    }

  }
  doClick(item){
    let {id} = item;
    location.href = `${location.origin}/live?id=${id}`;
  }
  render(){
    const {liveList} = this.state;
    return (
      <div className="live_list_container">
        {liveList.map((item,idx)=>{
          return (
            <div className="live_ele_container" key={idx}>
              <div className="live_ele_left" onClick={this.doClick.bind(this,item)}>
                <img src={item.roomPicPath}></img>
                <div className="live_ele_left_desc">
                  {item.roomStatus == 0 && `未开始 ${item.liveStartDate}`}
                  {item.roomStatus == 1 && "直播中"}
                  {item.roomStatus == 2 && "已结束"}
                </div>
              </div>
              <div className="live_ele_right">
                <div className="live_ele_right_ele1">
                  <LinesEllipsis text={item.roomTitle} maxLine="2" />
                </div>
                <div className="icon-shipin iconfont">
                  &nbsp;{item.playNumber}
                </div>
              </div>
            </div>
          )
        })}
      </div>
    )
  }
}
