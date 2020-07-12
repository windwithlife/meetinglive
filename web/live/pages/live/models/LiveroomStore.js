import { observable, action, computed,toJS,runInAction } from "mobx";
import BaseStore from '../../../models/BaseStore';
let  DataItem = {
    
        id:'',
        
    
        image:'',
        
    
        liveUrl:'',
        
    
        description:'',
        
    
        name:'',
        
    
    children:[],
}
let Data = {
    currentItem :DataItem,
    list:[]
}
export default class ProjectStore extends BaseStore {
    //@observable dataObject = Data;
    constructor() {
        super('/live/liveroom/');
        this.dataObject = Data;
    }
   
    @action.bound
    queryAll(callback){
        let that = this;
        this.model.queryRaw("/live/liveroom/queryAll",{},function (response) {
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
        this.model.queryRaw("/live/liveroom/query/"+id,{},function (response) {
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
        this.model.postRaw("/live/liveroom/save",values,function (response) {
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
        this.model.postRaw("/live/liveroom/update/" + values.id,values,function (response) {
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
        this.model.postRaw("/live/liveroom/romve" + id,{},function (response) {
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



