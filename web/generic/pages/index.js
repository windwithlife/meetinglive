// import MyComponent from "/Users/zhangfan/CtripProjects/gs_online_ui/dist/index"
import MyComponent from "gs_online_ui/dist/index"
// import "./test.less"
export default function(props){
    return (
        <>
            {/* <style jsx>{`
                h1{
                    font-size:100px;
                }
            `}
            </style> */}
            <h1 className="cr">this is index page</h1>
            <MyComponent></MyComponent>
        </>
    )
}