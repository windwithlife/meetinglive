import App, {Container} from 'next/app'
import React from 'react'
import { initializeStore } from '../models/index';
import { Provider } from 'mobx-react'
import Wrap from './Wrap'


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

class MyApp extends App {
    static async getInitialProps(appContext) {
        // Get or Create the store with `undefined` as initialState
        // This allows you to set a custom default initialState
        const mobxStore = initializeStore()
        appContext.ctx.mobxStore = mobxStore
        let appProps = await App.getInitialProps(appContext)

        return {
            ...appProps,
        };
    }

    constructor(props) {
        super(props)
    }

    render() {
        const { Component, pageProps } = this.props;
        let pathName = this.props.router.pathname;

        return (
            <Provider {...this.mobxStore}>
                <Container>
                    <Wrap path={pathName}>
                        <Component {...pageProps} />
                    </Wrap>
                </Container>
            </Provider>
        )
    }
}
export default MyApp


