import axios from 'axios';
import { getToken } from '../components/auth';
export const BASE_URL = 'http://localhost:8080/api/v1';

export const myAxios = axios.create({
    baseURL: BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
    }
})

export const privateAxios = axios.create({
    baseURL: BASE_URL,  
})
privateAxios.interceptors.request.use(config => {
    if (window.location.href === "/login") {
        return config; // If the URL is "/login", allow the request to proceed without adding headers
    }

    const token = getToken();

    
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
        return config;
    } else {
        return config;
    }
}, error => {
    return Promise.reject(error);
});
