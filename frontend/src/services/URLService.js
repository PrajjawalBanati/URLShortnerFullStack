import axios from "axios";

const URL_API_BASE_URL = "http://localhost:8080/url"

class URLService{

    saveURL(url)
    {
        const data = {
            "longUrl" : url
        };
        return axios.post(URL_API_BASE_URL, data,{
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    getUrl(key)
    {
        return axios.get(URL_API_BASE_URL+"/"+key,{
            headers:{
                'Content-Type': 'application/json'
            }
        });
    }

}
const urlService = new URLService();

export default urlService;
