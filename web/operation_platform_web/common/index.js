import axios from "axios";
const host_server_base_path = 'https://service.koudaibook.com/meeting-server'


export async function invoke_post(url,params={}){
    axios.defaults.withCredentials=true;
    axios.defaults.crossDomain=true;
const payload = {
    platType:4,
    category:1,
    version:1,
    platForm:"web",
    token:"token",
    data:params,
}

console.log('JSON.stringify(payload): ', JSON.stringify(payload));
    try{
        let result = await axios({
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'post',
            url:`${host_server_base_path}/${url}`,
            data: {
                platType:4,
                category:1,
                version:1,
                platForm:"web",
                token:"token",
                data:params,
            }
        })
        return result;
    }catch(error){
        console.error('---invoke_post_error---: ', error);
        return {}
    }
}

