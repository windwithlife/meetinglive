import { Layout, Menu} from 'antd';
import { AppstoreOutlined, MailOutlined, SettingOutlined} from '@ant-design/icons';
const { SubMenu } = Menu;
const { Header, Content, Sider, Footer } = Layout;
import router from 'next/router';


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
      {
        id: 'info_delete',
        name: "信息删除",
        url: "/info/delete",
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
  static async getInitialProps(router) {
    const path = router.pathname;
    if(path == '/_error') return {};
    //根据url来匹配选中哪一个head 以及head下面的子项  
    let pathArr = [],
        selectedHead = {};
    pathArr = path.match(/(?<=\/)\w+/g);
    if(!!pathArr) selectedHead = headerMenus.find(item=>item.id == pathArr[0]);
    else pathArr = [];
    return {
      childrenList:selectedHead?.childrenList || [],
      pathArr,
    }
  }
  
  handleClick = ({ item, key, keyPath, domEvent }) => {
    let menuItem = null;
    function getMenuItem(list){
      for(let i=0;i<list.length;i++){
        if(list[i].id == key) { menuItem = list[i];  return;}
        if(list[i].childrenList) getMenuItem(list[i].childrenList);
      }
    }
    getMenuItem(headerMenus);
    if(!!menuItem){
      location.href = `${location.origin}${menuItem.url}`
    }
  }

  render() {
    const {childrenList,pathArr} = this.props;
    function getSiderMenusDom(children){
      let arr = [];
      for(let i=0;i<children.length;i++){
        let nowItem = children[i];
        let child =  nowItem?.childrenList || [];
        if(child.length){
          arr.push(
            (
              <SubMenu icon={<AppstoreOutlined />} key={nowItem.id} title={nowItem.name}>
                {getSiderMenusDom(child)}
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
            <Menu theme="dark" mode="horizontal" defaultSelectedKeys={[`${pathArr[0]}`]} onClick={this.handleClick.bind(this)}>
              {headerMenus.map((menu) => <Menu.Item key={menu.id}>{menu.name}</Menu.Item>)}
            </Menu>
          </Header>
          <Content style={{height:'calc(100% - 34px)',overflow:'auto'}}>
            <Layout style={{height:'100%'}}>
              {
                childrenList.length && (
                  <Sider style={{ background: "#fff" }}>
                    <Menu onClick={this.handleClick.bind(this)} defaultSelectedKeys={[pathArr.join('_')]} mode="inline">
                      { SiderMenus }
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





 {/* <SubMenu key="sub2" icon={<AppstoreOutlined />} title="Navigation Two">
                        <Menu.Item key="5">Option 5</Menu.Item>
                        <Menu.Item key="6">Option 6</Menu.Item>
                        <SubMenu key="sub3" title="Submenu">
                          <Menu.Item key="7">Option 7</Menu.Item>
                          <Menu.Item key="8">Option 8</Menu.Item>
                        </SubMenu>
                      </SubMenu> */}

