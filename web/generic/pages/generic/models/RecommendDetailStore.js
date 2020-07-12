import { observable, action, computed,toJS,runInAction } from "mobx";
import BaseStore from '../../../models/BaseStore';
let  DataItem = {
    
        id:'',
        
    
        name:'',
        
    
        image:'',
        
    
        description:'',
        
    
        path:'',
        
    
        positionId:'',
        
    
    children:[],
}
let Data = {
    currentItem :DataItem,
    list:[]
}
export default class ProjectStore extends BaseStore {
    //@observable dataObject = Data;
    constructor() {
        super('/generic/recommendDetail/');
        this.dataObject = Data;
    }
   
    @action.bound
    queryAll(callback){
        let that = this;
        this.model.queryRaw("/generic/recommendDetail/queryAll",{},function (response) {
            if (response && response.data) {
                console.log(response.data);
                that.dataObject.list= response.data.items;
                if (callback){
                    callback(response.data);
                }
            }
        });

    }

    @action.bound
    queryById(id, callback){
        let that = this;
        this.model.queryRaw("/generic/recommendDetail/query/"+id,{},function (response) {
            if (response && response.data) {
                console.log(response.data);
                that.dataObject.currentItem= response.data;
                if (callback){
                    callback(response.data);
                }
            }
        });

    }

    @action.bound
    add(values, callback){
        let that = this;
        this.model.postRaw("/generic/recommendDetail/save",values,function (response) {
            if (response && response.data) {
                console.log(response.data);
                that.dataObject.currentItem= response.data;
                if (callback){
                    callback(response.data);
                }
            }
        });

    }

    @action.bound
    update(values, callback){
        let that = this;
        this.model.postRaw("/generic/recommendDetail/update/" + values.id,values,function (response) {
            if (response && response.data) {
                console.log(response.data);
                that.dataObject.currentItem= response.data;
                if (callback){
                    callback(response.data);
                }
            }
        });

    }
    @action.bound
    removeById(id, callback){
        let that = this;
        this.model.postRaw("/generic/recommendDetail/romve" + id,{},function (response) {
            if (response && response.data) {
                console.log(response.data);
                that.dataObject.currentItem= response.data;
                if (callback){
                    callback(response.data);
                }
            }
        });

    }
    

    
}



