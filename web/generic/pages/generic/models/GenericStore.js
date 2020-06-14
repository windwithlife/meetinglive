import { observable, action, computed,toJS,runInAction } from "mobx";
import BaseStore from '../../../models/BaseStore';
let  DataItem = {
    
    children:[],
}
let Data = {
    currentItem :DataItem,
    list:[]
}
export default class ProjectStore extends BaseStore {
    //@observable dataObject = Data;
    constructor() {
        super('/generic/generic/');
        this.dataObject = Data;
    }
   

    
}



