import axios from "axios";

<<<<<<< HEAD
const URL_API_BASE_URL = "http://34.125.38.52:80/url"
=======
const URL_API_BASE_URL = "https://34.125.38.52/url"
>>>>>>> origin/main

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
