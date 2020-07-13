import App, {Container} from 'next/app'
import React from 'react'
import { initializeStore } from '../models/index';
import { Provider } from 'mobx-react'
import Wrap from './Wrap'

class MyApp extends App {
    static async getInitialProps(appContext) {
        const { Component, router, ctx } = appContext;
        // Get or Create the store with `undefined` as initialState
        // This allows you to set a custom default initialState
        appContext.ctx.mobxStore = initializeStore()
        let pageProps = {}
        if (Component.getInitialProps) pageProps = await Component.getInitialProps(ctx)
        
        let WrapInitialProps = await Wrap.getInitialProps(router);
        return {
            pageProps,
            WrapInitialProps,
        };
    }

    constructor(props) {
        super(props)
    }

    render() {
        const { Component, pageProps,WrapInitialProps } = this.props;
        return (
            <Provider {...this.mobxStore}>
                <Wrap {...WrapInitialProps}>
                    <Component {...pageProps} />
                </Wrap>
            </Provider>
        )
    }
}
export default MyApp


