import React, { useState,useEffect} from 'react';
import { List, InputItem, WhiteSpace,Picker } from 'antd-mobile';
import "./infoAdd.less";
import {invoke_post} from "../../common/index"

const CustomChildren = props => (
    <div onClick={props.onClick} style={{ backgroundColor: '#fff', paddingLeft: 15 }}>
        <div className="test" style={{ display: 'flex', height: '45px', lineHeight: '45px',position:'relative',borderBottom:0 }}>
            <div style={{ flex: 1, overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap',fontSize:"17px" }}>{props.children}</div>
            <div style={{ textAlign: 'right', color: '#888', marginRight: 15 }}>{props.extra}</div>
        </div>
    </div>
  );

export default class InfoAdd extends React.Component{
  constructor(props){
    super(props);
    this.state = {
      isShowinfoAddModule:false
    }
  }

  onChange(val){
    console.log('val: ', val);
  }
  onOk(value){
    console.log('value: ', value);
    this.setState({ pickerValue: value })
  }

  async componentDidMount(){
    try{
      let result = await invoke_post('https://service.koudaibook.com/meeting-server/api/userService/validateWriteUserInfo')
      console.log('result: ', result);
    }catch(error){
      console.error('error: ', error);
    }
  }

  render(){
    const {isShowinfoAddModule} = this.state;
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

    const info_add_module = (
      <div className="info_add_layer_con">
        <div className="info_add_layer_content_con">
          <div className="info_add_layer_content_title">个人信息填写</div>
          <div className="info_con_bottom_content">
            <InputItem onChange={this.onChange.bind(this)} clear >姓名</InputItem>
            <InputItem onChange={this.onChange.bind(this)} clear >医院</InputItem>
            <InputItem onChange={this.onChange.bind(this)} clear >科室</InputItem>
            <Picker title="选择地区" extra="请选择(可选)" data={antdDistrict} 
                    value={this.state.pickerValue} 
                    onChange={v => this.setState({ pickerValue: v })}
                    onOk={this.onOk.bind(this)}
                >
                    <CustomChildren>省份</CustomChildren>
                </Picker>
            <div className="submit">提交</div>
          </div>
        </div>
      </div>
    )
    return (
      <>
        {isShowinfoAddModule && info_add_module}
      </>
    )
  }
}
