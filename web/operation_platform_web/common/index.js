import axios from "axios";
const host_server_base_path = 'https://service.koudaibook.com/meeting-server'

export async function invoke_post(url,params={}){
    try{
        let result = await axios({
            // console.log('result: ', result);
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
        console.error('error: ', error);
        return {}
    }
}

