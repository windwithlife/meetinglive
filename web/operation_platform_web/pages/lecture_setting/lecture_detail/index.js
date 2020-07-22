import React from "react";
import "../../styles/lecture_detail/lectureDetail.less"

import {Breadcrumb, Input, Select,DatePicker,Button } from 'antd';
const { Option } = Select;
const { RangePicker } = DatePicker;
const { TextArea } = Input;



export default class Index extends React.Component{
    static async getInitialProps({router ,req ,res, initializeStoreObj}) {
        return {}
    }
    constructor(props){
        super(props);
        this.playBackAddress = '';
        this.lectureName = '';
        this.teacherName = '';
        this.liveTimeOnOkVal = null;
        this.lectureIntr = '';


        this.playBackAddressInputOnChange = this.playBackAddressInputOnChange.bind(this);
        this.lectureNameInputOnChange = this.lectureNameInputOnChange.bind(this);
        this.selectOnchange = this.selectOnchange.bind(this);
        this.liveTimeOnOk = this.liveTimeOnOk.bind(this);
        this.textAreaOnChange = this.textAreaOnChange.bind(this);
        this.btnSaveClick = this.btnSaveClick.bind(this);
        
        this.state = {
            previewImgUrl : 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3264589794,202278324&fm=26&gp=0.jpg',
            previewImgFile: null,
        }

        this.modules = [{
            leftDesc:"推流地址",
            rightType:"text",
            rightDesc:"https://www.baidu.com/?tn=02003390_43_hao_pg",
        },{
            leftDesc:"串流秘钥",
            rightType:"text",
            rightDesc:"2sibdbsa23hbbfand", 
        },{
            leftDesc:"回放地址",
            rightType:"input",
            bindEvent:this.playBackAddressInputOnChange
        },{
            leftDesc:"讲座名称",
            rightType:"input",
            bindEvent:this.lectureNameInputOnChange
        },{
            leftDesc:"讲师",
            rightType:"select",
            bindEvent:this.selectOnchange,
            options:['Jack','lucy','tom'],
        },{
            leftDesc:"直播时间",
            rightType:"datePick",
            bindEvent:this.liveTimeOnOk
        },{
            leftDesc:"讲座介绍",
            rightType:"textarea",
            bindEvent:this.textAreaOnChange
        },{
            leftDesc:"",
            rightType:"button",
            bindEvent:this.btnSaveClick
        }]
    }
    playBackAddressInputOnChange(event){ //回放地址
        this.playBackAddress = event.currentTarget.value;
    }
    lectureNameInputOnChange(event){ //讲座名称
        this.lectureName = event.currentTarget.value;
    }
    selectOnchange(value, option){ //讲师
        this.teacherName = value;
    }
    btnSaveClick(){
        console.log(this.playBackAddress,this.lectureName,this.teacherName,this.liveTimeOnOkVal,this.lectureIntr);
    }
    liveTimeOnOk(value) { //直播时间
        this.liveTimeOnOkVal = value._d.getTime();
    }
    textAreaOnChange(event){  //讲座介绍
        this.lectureIntr = event.currentTarget.value;
    }
    selectedLocalPic(event){
        let file = event.target.files[0];
        this.setState({
            previewImgFile : file,
            previewImgUrl : URL.createObjectURL(file)
        })
    }
    uploadLocalPic(){ //上传本地图片
        const {previewImgFile} = this.state;
        let formData = new FormData();
        // let request = new XMLHttpRequest();
        formData.append("imgData", previewImgFile);
        // request.open("POST", url, true);
        // request.send(formData);
    }
    render(){
        const {previewImgUrl} = this.state;
        return(
            <div className="lecture_detail_con">
                <Breadcrumb separator=">">
                    <Breadcrumb.Item href="/lecture_setting">讲座设置</Breadcrumb.Item>
                    <Breadcrumb.Item>讲座详情</Breadcrumb.Item>
                </Breadcrumb>
                <div className="picture_con">
                    <div className="picture_con_left">
                        <img className="img_base" src={previewImgUrl}></img>
                    </div>
                    <div className="picture_con_right">
                        {/* <Button>预览</Button> */}
                        <div className="previewBtn">预览</div>
                        <div onClick={this.uploadLocalPic.bind(this)} className="previewBtn uploadBtn">上传</div>
                        <input type="file" onChange={this.selectedLocalPic.bind(this)}></input>
                    </div>
                </div>
                <div className="base_info_con">
                    <div className="base_info_con_left">
                        <img className="img_base" src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3264589794,202278324&fm=26&gp=0.jpg"></img>
                    </div>
                    <div className="base_info_con_right">
                        {
                             this.modules.map((module)=>{
                                switch(module.rightType){
                                    case "text" :
                                        return (
                                            <div key={module.leftDesc} className="base_info_con_right_small_con">
                                                <div className="base_info_con_right_first">{module.leftDesc}</div>
                                                <div className="base_info_con_right_second">{module.rightDesc}</div>
                                            </div>
                                        )
                                    break;
                                    case 'input' :
                                            return (
                                                <div key={module.leftDesc} className="base_info_con_right_small_con">
                                                    <div className="base_info_con_right_first">{module.leftDesc}</div>
                                                    <div className="base_info_con_right_second">
                                                        <Input onChange={module.bindEvent}  />
                                                    </div>
                                                </div>
                                            ) 
                                    break;
                                    case 'select' :
                                        return (
                                            <div key={module.leftDesc} className="base_info_con_right_small_con">
                                                <div className="base_info_con_right_first">{module.leftDesc}</div>
                                                <div className="base_info_con_right_second">
                                                    <Select showSearch style={{ width: 300 }} placeholder="Select a person" 
                                                        optionFilterProp="children" onChange={module.bindEvent}
                                                        filterOption={(input, option) => option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0} >
                                                            {
                                                                module.options.map(option=>{
                                                                    return (
                                                                        <Option key={option}>{option}</Option>
                                                                    )
                                                                })
                                                            }
                                                    </Select>
                                                </div>
                                            </div>
                                        )
                                    break;
                                    case 'datePick' :
                                        return (
                                            <div key={module.leftDesc} className="base_info_con_right_small_con">
                                                <div className="base_info_con_right_first">{module.leftDesc}</div>
                                                <div className="base_info_con_right_second">
                                                    <DatePicker showTime style={{width:300}}
                                                        onOk={module.bindEvent} />
                                                </div>
                                            </div>
                                        )
                                    break;
                                    case 'textarea' :
                                        return (
                                            <div key={module.leftDesc} className="base_info_con_right_small_con">
                                                <div className="base_info_con_right_first">{module.leftDesc}</div>
                                                <div className="base_info_con_right_second">
                                                    <TextArea style={{ width: 300,height:120 }} onChange={module.bindEvent}></TextArea>
                                                </div>
                                            </div>
                                        )
                                    break;
                                    case 'button' : 
                                        return (
                                            <div key={module.leftDesc} className="base_info_con_right_small_con">
                                                <div className="base_info_con_right_first">{module.leftDesc}</div>
                                                <div className="base_info_con_right_second">
                                                    <Button onClick={module.bindEvent}>保存</Button>
                                                </div>
                                            </div>
                                        )
                                    break;
                                }
                             })
                        }
                    </div>
                </div>
            </div>
        )
    }
}


