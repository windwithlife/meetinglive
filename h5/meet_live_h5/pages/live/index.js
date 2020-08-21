import React, { useState,useEffect} from 'react';
import { List, InputItem, WhiteSpace,Picker } from 'antd-mobile';
import "./index.less";
const CustomChildren = props => (
  <div onClick={props.onClick} style={{ backgroundColor: '#fff', paddingLeft: 15 }}>
      <div className="test" style={{ display: 'flex', height: '45px', lineHeight: '45px',position:'relative',borderBottom:0 }}>
          <div style={{ flex: 1, overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>{props.children}</div>
          <div style={{ textAlign: 'right', color: '#888', marginRight: 15 }}>{props.extra}</div>
      </div>
  </div>
);

export default class Live extends React.Component{
  constructor(props){
    super(props);
    this.state = {
      pickerValue: [],
      whichTap:"LEFT" //LEFT | RIGHT
    }
  }
  onOk(value){
    console.log('value: ', value);
    this.setState({ pickerValue: value })
  }
  tapClick(whichTap){
    this.setState({whichTap})
  }
  onChange(val){
    console.log('val: ', val);
  }
  loadPlayer(){ 
    //xgplayer not support ssr
    import("xgplayer").then((xgplayer)=>{
      const Player = xgplayer.default;
      new Player({
        el:document.querySelector('#mse'),
        url: '//s1.pstatp.com/cdn/expire-1-M/byted-player-videos/1.0.0/xgplayer-demo.mp4',
        videoInit: true  
      });      
    })
  }
  componentDidMount(){
    this.loadPlayer(); 
  }

  render(){
    let antdDistrict =[];
    let districtData = require('./location');
    Object.keys(districtData).forEach((index)=>{
        let itemLevel1 ={};
        let itemLevel2 ={};
        itemLevel1.value = districtData[index].code;
        itemLevel1.label = districtData[index].name;
        itemLevel1.children = [];
        let data = districtData[index].cities;
        Object.keys(data).forEach((index)=>{
            itemLevel2.value = data[index].code;
            itemLevel2.label = data[index].name;
            itemLevel2.children = [];
            let data2 = data[index].districts;
            let itemLevel3 ={};
            itemLevel3.children = [];
            Object.keys(data2).forEach((index)=>{
                itemLevel3.value = index;
                itemLevel3.label = data2[index];
                itemLevel2.children.push(itemLevel3);
                itemLevel3 ={};
            });
            itemLevel1.children.push(itemLevel2);
            itemLevel2 ={};
        });
        antdDistrict.push(itemLevel1)
    });

    const {whichTap} = this.state;

    const info_con_top_module = (
      <div className="info_con_top">
        <div className="info_con_title">
          论新冠病毒对人体的影响
        </div>
        <div className="icon-shipin iconfont">
          &nbsp;1234
        </div>
      </div>
    )
    const info_con_bottom_module = (
      <div className="info_con_bottom">
        <>
          <div onClick={this.tapClick.bind(this,"LEFT")}
            style={whichTap=="LEFT"?{background:"#AB956D",color:"#fff"}:{}} 
            className="info_con_bottom_left_tap tap">
            会议日程
          </div>
          <div  onClick={this.tapClick.bind(this,"RIGHT")}
          style={whichTap=="RIGHT"?{background:"#AB956D",color:"#fff"}:{}}
          className="info_con_bottom_right_tap tap">
            会议介绍
          </div>
        </>
      </div>
    )

    const info_add_module = (
      <div className="info_add_layer_con">
        <div className="info_add_layer_content_con">
          <div className="info_add_layer_content_title">个人信息填写</div>
          <div className="info_con_bottom_content">
            <InputItem onChange={this.onChange.bind(this)} clear >姓名</InputItem>
            <InputItem onChange={this.onChange.bind(this)} clear >省份</InputItem>
            <InputItem onChange={this.onChange.bind(this)} clear >医院</InputItem>
            <InputItem onChange={this.onChange.bind(this)} clear >科室</InputItem>
            <Picker title="选择地区" extra="请选择(可选)" data={antdDistrict} 
                    value={this.state.pickerValue} 
                    onChange={v => this.setState({ pickerValue: v })}
                    onOk={this.onOk.bind(this)}
                >
                    <CustomChildren>Customized children</CustomChildren>
                </Picker>
            <div className="submit">提交</div>
          </div>
        </div>
      </div>
    )
    return (
      <div className="live_container">
        <div id="mse"></div>

        <div className="info_con">
          {info_con_top_module}
          {info_con_bottom_module}
        </div>
        {info_add_module}
      </div>
    )
  }
}
