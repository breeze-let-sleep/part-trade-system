import { createApp } from 'vue'
import App from './App.vue'
// 1、引入element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
//引入element-plus全局中文
import zhCn from 'element-plus/es/locale/lang/zh-cn'
// 3、引入element-plus的所有icon图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 引入路由
import router from './router/index.js'
//引入pinia
import { createPinia } from 'pinia'
//引入守卫
import '@/router/guard.js'

const app=createApp(App)
//2、全局使用element-plus且全局使用中文
app.use(ElementPlus, {
  locale: zhCn,
})
//4、将所有icon图标批量注册成全局组件
Object.keys(ElementPlusIconsVue).forEach(key => {
  app.component(key, ElementPlusIconsVue[key])   
})
// 将路由挂载到app
app.use(router)
app.use(createPinia())
app.mount('#app')
