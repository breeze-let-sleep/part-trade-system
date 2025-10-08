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
    // 登录：解析JWT→拿到role
    async login(jwtToken) {
      //----接收后端发来的jwt后解析出token中的数据存到pinia中-----
      this.token = jwtToken
      const { id, name, role } = parseJwt(this.token)
      this.id = id
      this.name = name
      this.role = role

      await this._installRoutes()
      router.replace('/')
    },
    /* 刷新后恢复：同步阻塞，保证路由加完再放行 */
    async restore() {
      const jwt = localStorage.getItem('jwt')
      if (!jwt) return false
      try {
        const { id, name, role } = parseJwt(jwt)
        this.token = jwt
        this.id = id; this.name = name; this.role = role
        await this._installRoutes()
        return true
      } catch {
        // jwt 解析失败 → 清掉重来
        localStorage.removeItem('jwt')
        this.$reset()
        return false
      }
    },

    /* 真正安装路由 & 菜单 */
    async _installRoutes() {
      // 根据角色取对应路由数组
      const targetRoutes = roleToRoutes[this.role]
      if (!targetRoutes) throw new Error('非法角色')
      // 动态添加到路由表（layout 会作为根节点）
      targetRoutes.forEach(r => router.addRoute(r))
      // 同时保存一份给侧边栏递归渲染
      this.menus = targetRoutes[0].children // layout 下第一层

      // 再加一条“兜底 404”——一定要最后加！
      router.addRoute({
        path: '/:pathMatch(.*)*',
        name: '404',
        component: () => import('@/views/404.vue')
      })
    },

    logout() {
      this.$reset()
      localStorage.removeItem('jwt')
      // 清空动态路由
      location.reload()
    }
  },

  persist: { paths: ['token', 'id', 'name', 'role', 'menus'] }
})
