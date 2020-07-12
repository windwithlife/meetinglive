

import React from 'react';
import {
    Icon,
    Button,
    Collapse,
    Modal,
    Form,
    Input,
    Card,
    Select,
} from 'antd';
const { Panel } = Collapse;
import { SettingOutlined } from '@ant-design/icons';
const { TextArea } = Input;
import router from 'next/router';
import { inject, observer } from 'mobx-react';
import EditTable from '../common/components/EditableTable';
//import NetworkHelper from '../common/components/models/network';

@inject('recommendPositionStore')
@observer
export default class EditPage extends React.Component {
    formRef = React.createRef();
    state = {
        editMode: false,
    }
    constructor() {
        super();
        this.projectName = "tempName";
    }
    Store = () => {
        return this.props.recommendPositionStore;
    }
    StoreData = () => {
        return this.props.recommendPositionStore.dataObject;
    }
  
    componentDidMount() {
        let that = this;
        let id = this.props.query.id;
        if (!id){return;}
        this.Store().queryById(id, function (values) {
            console.log(values);
            that.formRef.current.setFieldsValue(values);
        });
    }
    onFinish = values => {
        var that = this;
        //let id = this.props.query.id;
        //values.project = projectId;
        this.Store().update(values, () => { console.log('finished update row'); router.back(); });
    }

    render() {
        let that = this;
        let itemData = that.StoreData().currentItem;
        console.log('render module edit page');
        return (
            < div >
                <Card size="small" title="基本信息" style={{ width: 500 }}  >
                        <Form ref={this.formRef} onFinish={that.onFinish.bind(that)}>
                        <Form.Item
                                name="id"
                                noStyle='true'
                            ></Form.Item>

   
   
                          < Form.Item name="name" label="推荐位名称">
                           <Input />
                          </Form.Item>

   
                          < Form.Item name="description" label="位置描述">
                           <Input />
                          </Form.Item>

   
                          < Form.Item name="pattern" label="URL模式">
                           <Input />
                          </Form.Item>

                        
                    <Card type="inner">
                        <Form.Item>
                            <Button type="primary" htmlType="submit" size="large">Save</Button>
                        </Form.Item>
                    </Card>
                        </Form>
                </Card>

               
            </div>
        );
    }
}

EditPage.getInitialProps = async function (context) {
    return { query: context.query };
}












