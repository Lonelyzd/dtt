import Axios from 'axios'
import loadingGit from '../assets/loading.gif'
import router from '@/router'

const axios = Axios.create({
    baseURL: import.meta.env.VUE_APP_BASE_API,
    timeout: 200000, // 请求超时 200s
})

let loading = null

axios.interceptors.request.use(
    (response) => {
        setOpertime(new Date().getTime())
        if (response.showLoading) {
            loading = Loading.service({
                lock: true,
                text: 'Loading...',
                spinnerSrc: loadingGit,
                spinnerSize: '80px',
                textColor: '#6b6161',
                background: 'rgba(222,222,222,0.8)'
            })
        }
        // response.headers.TSS_TOKEN = getToken()
        return response
    },
    (error) => {
        return Promise.reject(error)
    }
)

axios.interceptors.response.use(
    (response) => {
        if (loading) {
            loading.close()
        }
        if (response.status === 200) {
            return Promise.reject(response.data)
        } else {
            return Promise.reject(response.statusText)
        }
    },
    (error) => {
        if (loading) {
            loading.close()
        }
        if (error.response.status === 404) {
            //路径不存在
            Message.error(`请求路径不存在:${error.response?.data?.path}`)
        } else {
            Message.error(error.response?.data?.message || '未知错误')
            return Promise.reject(error)
        }
    }
)

function isJSON(str) {
    if (typeof str == 'string') {
        try {
            var obj = JSON.parse(str);
            if (typeof obj == 'object' && obj) {
                return true;
            } else {
                return false;
            }

        } catch (e) {
            return false;
        }
    }
    console.log('It is not a string!')
}

export default axios
