
import React from 'react';

export default class Index extends React.Component{
    static async getInitialProps(ctx){
        return {}
    }
    constructor(props){
        super(props);
    }
    render(){
        return (
            <>
                {/* <style jsx>{`
                    h1{
                        font-size:100px;
                    }
                `}
                </style> */}
                <h1 className="cr">this is index page</h1>
            </>
        )
    }
}



