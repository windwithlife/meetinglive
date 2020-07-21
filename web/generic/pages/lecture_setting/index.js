import React from "react";
import "../styles/lectureSetting.less"
import { Button } from 'antd';


export default class LectureSetting extends React.Component{
    static async getInitialProps({router ,req ,res, initializeStoreObj}) {
        return {}
    }
    constructor(props){
        super(props)
    }
    lookDetail(){
        location.href = `${location.origin}/lecture_setting/lecture_detail`;
    }
    publish(){

    }
    newSetUp(){
        location.href = `${location.origin}/lecture_setting/newBuildLecture`;
    }
    render(){
        return(
            <div className="lecture_setting_con">
                <Button className="new_setup_btn" onClick={this.newSetUp.bind(this)}>新建讲座</Button>
                {
                    [1,2,3,4,5].map(()=>{
                        return (
                            <div className="content_con">
                                <div className="content_con_left">
                                    <div className="content_con_left_img_con">
                                        <img className="img_base" src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1235257593,3415073461&fm=26&gp=0.jpg"></img>
                                        <div className="content_con_left_img_tag">直播中</div>
                                    </div>
                                    <div className="content_con_left_total_con">
                                        <h1>蚂蚁集团启动上市计划 概念股开盘大涨</h1>
                                        <div className="content_con_left_doctor_con">
                                            <div>
                                            <span className="iconfont icon-yonghutouxiang">&nbsp;&nbsp;张三&nbsp;&nbsp;&nbsp;</span>
                                            <span className="iconfont icon-shipin">&nbsp;&nbsp;2000&nbsp;&nbsp;&nbsp;</span>
                                            <span className="iconfont icon-clock">&nbsp;&nbsp;2020/07/07 18:00:00&nbsp;&nbsp;&nbsp;</span>
                                            
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="content_con_right">
                                    <Button onClick={this.lookDetail.bind(this)}>查看详情</Button>
                                    <Button onClick={this.publish.bind(this)}>立即发布</Button>
                                </div>
                            </div> 
                        )
                    })
                }
            </div>
        )
    }
}


