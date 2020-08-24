
import React, { useState,useEffect} from 'react';

let [count,setCount] = useState(0);
// function doCick(){
//     setCount(++count)
// }

export default function Index(){
    function A(){
 
        return (
            <h1 onClick={()=>doClick()}>A</h1>
        )
    }
    function B(){
     
        return (
            <h1 onClick={()=>doClick()}>B</h1>
        )
    }
    return (
        <>
            <A></A>
            <B></B>
        </>
    )
}

