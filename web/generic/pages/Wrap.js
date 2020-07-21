import { Layout, Menu } from 'antd';
const { Content, Sider, Footer } = Layout;
import "./styles/wrap.less"



const menuList = [{
    desc:"讲座设置",
    key:"lecture_setting", //匹配以 /lecture_setting开头的所有路由
  },{
    desc:"讲师设置",
    key:"teacher_setting",
  },{
    desc:"广告设置",
    key:"advertise_setting",
  },{
    desc:"咨询编辑",
    key:"consult_edit",
  }]

function Head(props){
  return (
    <div className="head_con">
      <div className="head_con_left">
        XX医疗运营后台
      </div>
      <div className="head_con_right">
        <div>18621085656</div>
        <div></div>
      </div>
    </div>
  )
}

export default class Wrap extends React.Component {
  static async getInitialProps(router){
    const {pathname,query,asPath,basePath,}  = router;
    const pathArr = asPath.replace(/\//,"").split('/');
    return { pathArr,asPath }
  }

  handleClick = ({ item, key, keyPath, domEvent }) => {
    location.href = `${location.origin}/${key}`
  }

  render() {
    const {pathArr} = this.props;
    const defaultSelectedKeys = pathArr[0] || ''
    return (
      <>
        <Layout style={{ height: '100%' }}>
          <Head></Head>
          <Content style={{ height: 'calc(100% - 34px)', overflow: 'auto' }}>
            <Layout style={{ height: '100%' }}>
              <Sider style={{ background: "#fff" }}>
                <Menu onClick={this.handleClick.bind(this)} defaultSelectedKeys={[defaultSelectedKeys]} mode="inline">
                  {
                    menuList.map((item)=> <Menu.Item key={item.key}>{item.desc}</Menu.Item>)
                  }
                </Menu>
              </Sider>
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

