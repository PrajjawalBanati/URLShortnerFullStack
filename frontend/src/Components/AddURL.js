import React, { useState } from 'react'
import URLService from '../services/URLService';

export const AddURL = () => {

    const [url, setUrl] = useState("");
    const [visible, setVisible] = useState(false);
    const [error, isError] = useState(false);
    const [shortUrl, setShortUrl] = useState("");
    const [key, setKey] = useState("");

    const handleChange = (e) =>{
        const url = e.target.value;
        setUrl(url);
    }

    const saveUrl = (e) => {
        e.preventDefault();
        if(url==="")
        {
            alert("Please enter a valid URL");
        }
        else
        {
            URLService.saveURL(url)
            .then((response)=>{
                const hostname = window.location.hostname;
                const port = window.location.port;
                setShortUrl(hostname+":"+port+"/"+response.data.key);
                setKey(response.data.key);
                setVisible(true);
            })
            .catch((error) => {
                console.log(error);
                isError(true);
            });
            
        }
        
    }

    const getURL = (e) => {
        e.preventDefault();
        URLService.getUrl(key)
        .then((response)=>{
            window.location.replace(response.data.url);
        });

    }
    
    const clearTextBox = (e) =>{
        setUrl("");
        if(error){ isError(false); }
        if(visible){ setVisible(false); }
    }

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b bg-blue-500">
        <div className="items-center px-8 py-8 w-full">
            <div className="justify-center font-bold font-mono text-2xl tracking-wider">
                <h1>Your Own URL Shortner</h1>
            </div>
            <div className="items-center justify-center h-14 w-full my-4">
                <label className="block text-black-600 text-xs font-normal">
                    Paste your Long URL
                </label>
                <input 
                type="text" 
                className="h-10 mt-2 px-2 py-2 border border-gray-950 rounded-full w-full"
                name="url"
                value={url}
                onChange={(e) => handleChange(e)}
                required={true}></input>
            </div>
            <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-2">
                <button onClick={saveUrl} className="rounded-full text-white bg-green-400 hover:bg-green-700 px-6 py-2 ">
                    Get Short URL
                </button>
                <button onClick = {clearTextBox} className="rounded-full right-full text-white bg-red-400 hover:bg-red-700 px-6 py-2">
                    Clear
                </button>
            </div>
            <div className="items-center justify-center h-14 w-full my-4">
                {visible && <>
                    <label className="block text-black-700 text-sm font-bold"> Here's your short URL</label>
                    <div className="items-center justify-center h-14 w-full my-4">
                    <div className = "w-auto rounded-full bg-gray-600 text-white hover:cursor-pointer" onClick={getURL}>
                        <span className="justify-center px-48">{shortUrl}</span>
                    </div>
                    </div>
                </>
                    }
                {error && <label className="block text-red-700 text-sm font-bold"> Internal Server Error Occured.</label>}
            </div>
        </div>
    </div>
  )
}
