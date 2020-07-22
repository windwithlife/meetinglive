import React from 'react';
import { Form, Input, Button, Checkbox } from 'antd';
import "./styles/index.less";

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
};
const tailLayout = {
  wrapperCol: { offset: 8, span: 16,},
};

export default class Login extends React.Component{
    constructor(props){
        super(props);
    }
    onFinish = values => {
        location.href = `${location.origin}/lecture_setting`
    };
    
    onFinishFailed = errorInfo => {
        console.log('Failed:', errorInfo);
    };
    render(){
        return (
            <div className="con">  
                <h1>
                    XX医疗运营后台
                </h1>
                <Form  {...layout} name="basic" initialValues={{ remember: true, }} 
                    onFinish={this.onFinish.bind(this)}
                    onFinishFailed={this.onFinishFailed.bind(this)}
                >
                    <Form.Item label="Username" name="username"
                        rules={[{ required: true, message: 'Please input your username!' }]}>
                        <Input />
                    </Form.Item>
                    
                    <Form.Item label="Password" name="password"
                        rules={[{ required: true, message: 'Please input your password!'}]}>
                        <Input.Password />
                    </Form.Item>
                    <Form.Item {...tailLayout}>
                        <Button  type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Form>

            </div>
          );
    }
}

