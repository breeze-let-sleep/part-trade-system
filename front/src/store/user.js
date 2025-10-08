// src/store/user.js
import { defineStore } from 'pinia'
import { parseJwt } from '@/utils/jwt.js'
import router from '@/router'
import {
  adminRoutes,
  merchantRoutes,
  customerRoutes
} from '@/router/roleRouter.js'

// 角色→路由表 映射
const roleToRoutes = {
  0: adminRoutes,
  1: merchantRoutes,
  2: customerRoutes
}

export const useUserStore = defineStore('user', {
  state: () => ({
    token: '',
    id: 0,
    name: '',
    role: -1,
    menus: [] // 给侧边栏用的同一套数据
  }),

  actions: {
    // ① 登录：解析JWT→拿到role
    // async login(jwtToken) {
    //   this.token = jwtToken
    //   const { id, name, role } = parseJwt(jwtToken)
    //   this.id = id
    //   this.name = name
    //   this.role = role

    //   // ② 根据角色取对应路由数组
    //   const targetRoutes = roleToRoutes[role]
    //   if (!targetRoutes) throw new Error('非法角色')

    //   // ③ 动态添加到路由表（layout 会作为根节点）
    //   targetRoutes.forEach(r => router.addRoute(r))

    //   // ④ 同时保存一份给侧边栏递归渲染
    //   this.menus = targetRoutes[0].children // layout 下第一层
    //   // ⑤ 跳首页
    //   router.replace('/')
    // },
    login() {
      //----接收后端发来的jwt后解析出token中的数据存到pinia中-----
      this.token = "jwtToken"
      // const { id, name, role } = parseJwt(jwtToken)
      this.id = "001"
      this.name = 'root'
      this.role = 1

      // 根据角色取对应路由数组
      const targetRoutes = roleToRoutes[this.role]
      if (!targetRoutes) throw new Error('非法角色')

      // 动态添加到路由表（layout 会作为根节点）
      targetRoutes.forEach(r => router.addRoute(r))

      // 同时保存一份给侧边栏递归渲染
      this.menus = targetRoutes[0].children // layout 下第一层
      // 跳首页
      router.replace('/')
    },

    logout() {
      this.$reset()
      // 清空动态路由
      location.reload()
    }
  },

  persist: { paths: ['token', 'id', 'name', 'role', 'menus'] }
})
