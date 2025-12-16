import axios from "axios";

const request=axios.create(
  {
    baseURL: '/api',
    timeout: 600000
  }
)

//设置请求拦截器：每次请求都从localStorage中获取jwt，并设置在请求头中
request.interceptors.request.use(
  config=>{
    let jwt=localStorage.getItem('jwt')
    // console.log(jwt)
    if(jwt){
      config.headers.token=jwt
    }
    return config
  },
  error=>{
    return Promise.reject(error)
  }
)


//设置响应拦截器
request.interceptors.response.use(
  response=>{
    return response.data
  },
  error=>{
    if(error.response.status===401){
      localStorage.removeItem('jwt')
      useUserStore().logout()
    }
    return Promise.reject(error)
  }
)


export default request