import { createApp } from 'vue'
import App from './App.vue'
// 1、引入element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 3、引入element-plus的所有icon图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 引入路由
import router from './router/index.js'
//引入pinia
import { createPinia } from 'pinia'

const app=createApp(App)
//2、全局使用element-plus
app.use(ElementPlus);
//4、将所有icon图标批量注册成全局组件
Object.keys(ElementPlusIconsVue).forEach(key => {
  app.component(key, ElementPlusIconsVue[key])   
})
// 将路由挂载到app
app.use(router)
app.use(createPinia())
app.mount('#app')
