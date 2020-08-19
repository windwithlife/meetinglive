import React, { useState,useEffect} from 'react';
import "./index.less";


export default class Live extends React.Component{
  constructor(props){
    super(props);
  }
  loadPlayer(){ 
    //xgplayer not support ssr
    import("xgplayer").then((xgplayer)=>{
      const Player = xgplayer.default;
      new Player({
        el:document.querySelector('#mse'),
        url: '//s1.pstatp.com/cdn/expire-1-M/byted-player-videos/1.0.0/xgplayer-demo.mp4',
        videoInit: true  
      });      
    })
  }
  componentDidMount(){
    this.loadPlayer(); 
  }
  render(){
    return (
      <div className="live_container">
        <div id="mse"></div>
      </div>
    )
  }
}
// export default function Live() {
//     // 声明一个新的叫做 “count” 的 state 变量
//     const [count, setCount] = useState(0);
//     // let player =  null;
//     // useEffect(() => {
//     //   player = new Player({
//     //     id: 'mse',
//     //     url: '//s1.pstatp.com/cdn/expire-1-M/byted-player-videos/1.0.0/xgplayer-demo.mp4'
//     // });
//     // },[]);


//     return (
//       <div className="live_container">
//         <div id="mse"></div>
//       </div>
//     );
//   }