import React, { useState,useEffect} from 'react';
import "./index.less";
import LinesEllipsis from 'react-lines-ellipsis'



export default class Live extends React.Component{
  constructor(props){
    super(props);
  }
  render(){
    return (
      <div className="live_list_container">
        <div className="live_ele_container">
          <div className="live_ele_left">
            <img src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1957329624,2042146448&fm=26&gp=0.jpg"></img>
            <div className="live_ele_left_desc">
              直播中
            </div>
          </div>
          <div className="live_ele_right">
            <div className="live_ele_right_ele1">
              <LinesEllipsis text={'论新冠病毒对人体的影响论新冠病毒对人体的影响论新冠病毒对人体的影响'} maxLine="2" />
            </div>
            <div className="icon-shipin iconfont">
              &nbsp;123456
            </div>
          </div>
        </div>
      </div>
    )
  }
}
