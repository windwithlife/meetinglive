

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
//import { SettingOutlined } from '@ant-design/icons';
const { TextArea } = Input;
import router from 'next/router';
import { inject, observer } from 'mobx-react';
import EditTable from '../common/components/EditableTable';
//import NetworkHelper from '../common/models/network';

@inject('menuStore')
@observer
export default class DetailPage extends React.Component {
    formRef = React.createRef();
    state = {
        editMode: false,
    }
    constructor() {
        super();
        this.projectName = "tempName";
    }
    Store = () => {
        return this.props.menuStore;
    }
    StoreData = () => {
        return this.props.menuStore.dataObject;
    }
    changeEditMode = (event) => {
        event.stopPropagation();
        console.log('click on edit model');
        let nextMode = !this.state.editMode;
        this.setState({ editMode: nextMode });
    }
    tableHeader() {
        var that = this;

        var fieldColumns = [];

        fieldColumns.push({
            title: "表的名称",
            dataIndex: 'name',
            key: 'name'
        });

        fieldColumns.push({
            title: "说明",
            dataIndex: 'description',
            key: 'description'
        });

        return fieldColumns;
    }

   
    componentDidMount() {
        let that = this;
        let id = this.props.query.id;
        if (!id){return;}
        this.Store().queryById(id, function (values) {
            console.log(values);
            //that.formRef.current.setFieldsValue(values);
        });
    }

    handleLineUpdate(type,index, record) {
        //let path ="/generic//edit";
        let path ="/generic//" + type + "_edit";
        router.push({ pathname: path, query: { id: record.id } });

    }
    handleLineDetail(type,record) {
        let path ="/generic//" + type + "_detail";
        console.log(path);
        router.push({ pathname: path, query: { id: record.id } });
    }
    handleLineAdd(type) {
        //let path ="/generic//add";
        let path ="/generic//" + type + "_add";
        router.push({ pathname:path});
    }

    handleLineDelete(type,index, record) {
        let that = this;
        let id = record.id;
        this.Store().removeById(id,function(value){
            console.log('removed item ID is:' + id);
        });
    }

    render() {

        let that = this;
        let itemData = that.StoreData().currentItem;
        let items = that.StoreData().currentItem.children;
        console.log('render module edit page');
        return (
            < div >
                <Card size="small" title="基本信息" style={{ width: 800 }}  >
                        <Form >

                          < Form.Item label="Id">
                            {itemData.id}
                          </Form.Item>

                          < Form.Item label="Name">
                            {itemData.name}
                          </Form.Item>

                          < Form.Item label="页面路由路径">
                            {itemData.url}
                          </Form.Item>

                          < Form.Item label="父菜单ID">
                            {itemData.parentId}
                          </Form.Item>

                          < Form.Item label="位置类型 导航，侧边，右上等">
                            {itemData.type}
                          </Form.Item>

                          < Form.Item label="频道所属">
                            {itemData.channel}
                          </Form.Item>

                        </Form>
                </Card>

                <EditTable title="列表" columns={that.tableHeader()} data={items}
                    onAdd={that.handleLineAdd.bind(that,"table")}
                    onDelete={that.handleLineDelete.bind(that,"table")}
                    onUpdate={that.handleLineUpdate.bind(that,"table")}
                    onDetail={that.handleLineDetail.bind(that,"table")}
                ></EditTable>
               
            </div>
        );
    }
}

DetailPage.getInitialProps = async function (context) {
    return { query: context.query };
}










