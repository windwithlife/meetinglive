/**
 * 网络请求处理文件 
 * 工具类方法不要放在该文件中，放在tools下
 */
import axios from "axios";
import { Modal } from "antd-mobile";
import {Loading} from "./tools";

const isServer = typeof window == 'undefined';

/** 登录流程 */
export const doLogin = async response => {
    // if (response.status >= 200 && response.status < 300) {
    //     return response.data
    // } else {
    //     var error = new Error((response && response.statusText) || 'text')
    //     error.response = response
    //     throw error
    // }
}

export const checkStatus = async response => {
    if (response.status >= 200 && response.status < 300) {
        return response.data
    } else {
        var error = new Error((response && response.statusText) || 'text')
        error.response = response
        throw error
    }
}

function dealToken(result) {
    // console.log('result: ', result);
    let { token = '', status, data } = result;//status 响应状态(0:失败 1:成功  2:未登录)
    if (!!data.token) token = data.token;
    switch (status) {
        case 0: {
            Modal.alert('提示', result.message, [{text: '确定',onPress: ()=>{}}])
            throw new Error(result.message);
        }
        case 1: {
            localStorage.setItem('token', token);
            if (location.pathname == '/') location = `${location.origin}/lecture_setting` //首页登录成功处理
            return result;
        }
        case 2: {
            
            return result;
        }
    }
    
}

export async function invoke_post(url, params = {}) {
    try {
        Loading.show();
        axios.defaults.withCredentials = true;
        axios.defaults.crossDomain = true;
        let token = localStorage.getItem('token');
        let result = await axios({
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'post',
            url,
            data: { 
                platType: 4, category: 1, version: 1, platType: 3, platForm:"wechat_official_account", token, openId:"",
                data: params 
            }
        }).then(checkStatus).then(dealToken)
        Loading.hide();
        return result;
    } catch (error) {
        Loading.hide();
        console.error('---invoke_post_error---: ', error);
        throw error;
    }
}

export async function uploadFile(file) {
    try {
        Loading.show();
        let formData = new FormData();
        let token = localStorage.getItem('token');
        let json = { token, platType: 4, category: 1, version: 1, platForm: "web" };
        formData.append('json', JSON.stringify(json))
        formData.append('file', file);

        let result = await axios.post('https://service.koudaibook.com/meeting-server/uploadService/uploadImage', formData, {
            headers: { 'Content-Type': 'multipart/form-data' }
        }).then(checkStatus).then(dealToken)
        Loading.hide();
        return result;
    } catch (error) {
        Loading.hide();
        console.error('---invoke_uploadFile_error---: ', error);
        throw error;
    }
}

