import { Layout, Menu} from 'antd';
import { AppstoreOutlined, MailOutlined, SettingOutlined} from '@ant-design/icons';
const { SubMenu } = Menu;
const { Header, Content, Sider, Footer } = Layout;

const headerMenus = [
  {
    id: 'info',
    name: "信息管理",
    url: "/info/add",
    childrenList: [
      {
        id: 'info_add',
        name: "信息添加",
        url: "/info/add",
      },
   
    ]
  },
  {
    id: 'live',
    name: "视频管理",
    url: "/live/add",
    childrenList: [{
      id: 'live_add',
      name: "视频添加",
      url: "/live/add",
    }]
  },
]

export default class Wrap extends React.Component {
  componentDidMount(){
    const {path} = this.props; if(path == '/_error') return {};  
    //根据url来匹配选中哪一个head 以及head下面的子项  
    let moduleName = path.match(/(?<=\/)\w+/g).shift();
    let selectedItemIdx = -1;
    for(let i=0;i<headerMenus.length;i++){ if(headerMenus[i].id == moduleName) { selectedItemIdx = i; break;} }
    let childrenList = headerMenus[selectedItemIdx]?.childrenList || [];
    this.setState({
      childrenList,
      selectedItemId:headerMenus[selectedItemIdx].id
    })
  }

  constructor(props){
    super(props);
    this.state = {
      childrenList:[],
      selectedItemId:'',
    }
  }
  
  handleClick = ({ item, key, keyPath, domEvent }) => {
    console.log('key: ', key);
    // key 就是 item.id
    // console.log('item, key, keyPath, domEvent: ', item, key, keyPath, domEvent);
    // event.stopPropagation();
    // //console.log('click ', e);
    // let menuId = e.key;
    // let menuObj = this.Store().findPagePathById(menuId);
    // router.push(menuObj.url);
  }

  render() {
    const {selectedItemId,childrenList} = this.state;
    function getSiderMenusDom(children){
      let arr = [];
      for(let i=0;i<children.length;i++){
        let nowItem = children[i];
        let child =  nowItem?.childrenList || [];
        if(child.length){
          arr.push(
            (
              <SubMenu icon={<AppstoreOutlined />} key={nowItem.id} title={nowItem.name}>
                {fun(child)}
              </SubMenu>
            )
          )
        }else{
          arr.push(
            (<Menu.Item key={nowItem.id}>{nowItem.name}</Menu.Item>)
          )
        }
      }
      return arr;
    }
    let SiderMenus =  getSiderMenusDom(childrenList)

    return (
      <>
        <Layout style={{height:'100%'}}>
          <Header className="header">
            <Menu theme="dark" mode="horizontal" defaultSelectedKeys={[`${selectedItemId}`]} onClick={this.handleClick.bind(this)}>
              {headerMenus.map((menu) => <Menu.Item key={menu.id}>{menu.name}</Menu.Item>)}
            </Menu>
          </Header>
          <Content style={{height:'calc(100% - 34px)',overflow:'auto'}}>
            <Layout style={{height:'100%'}}>
              {
                childrenList.length && (
                  <Sider style={{ background: "#fff" }}>
                    <Menu onClick={this.handleClick.bind(this)} defaultSelectedKeys={['info_add']} mode="inline">
                      { SiderMenus }
                      {/* <SubMenu key="sub2" icon={<AppstoreOutlined />} title="Navigation Two">
                        <Menu.Item key="5">Option 5</Menu.Item>
                        <Menu.Item key="6">Option 6</Menu.Item>
                        <SubMenu key="sub3" title="Submenu">
                          <Menu.Item key="7">Option 7</Menu.Item>
                          <Menu.Item key="8">Option 8</Menu.Item>
                        </SubMenu>
                      </SubMenu> */}
                    </Menu>
                  </Sider>
                )
              }
              <Content>
                {this.props.children}
              </Content>
            </Layout>
          </Content >
          <Footer theme="dark" style={{ textAlign: 'center', background: '#eee' }}>XCODER ©2020 Created by X Team</Footer>
        </Layout>
      </>
    )
  }
}





function _Wrap(App){
  return class _App extends React.Component {
    static async getInitialProps({ Component, router, ctx }) {
      
    }
    render(){
      <App></App>
    }
  }
}

