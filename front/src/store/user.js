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
    id: 0,
    name: '',   //对应管理员判定是否是超级管理员而使用
    role: -1,
    menus: [] // 给侧边栏用的同一套数据
  }),

  actions: {
    // 登录：解析JWT→拿到role
    async setUserInfo(jwtToken) {
      /* JWT（JSON Web Token）是Token的一种具体实现格式，就像"苹果"是"水果"的一种。
      Token是泛指用于身份验证的令牌，而JWT是其中基于JSON开放标准的一种结构化Token。 */
      //----接收后端发来的jwt后解析出用户信息数据存到pinia中-----
      const { id, name, role } = parseJwt(jwtToken)
      this.id = id
      this.name = name
      this.role = role
      // 安装路由
      await this._installRoutes()
      // 放行
      router.replace('/')
    },
    /* 刷新后恢复：同步阻塞，保证路由加完再放行 */
    async restore() {
      const jwt = localStorage.getItem('jwt')
      if (!jwt) return false
      try {
        const { id, name, role } = parseJwt(jwt)
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

      // 如果当前角色为管理员且登录名字不为root时，删除其内adminList路由元素
      if (this.role === 0 && this.name !== 'root') {
        // 遍历路由数组，找到包含adminList的路由配置并移除
        targetRoutes.forEach((route) => {
          if (route.children) {
            route.children = route.children.filter(child => child.name !== 'AdminList')
          }
        })
      }
      
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

  // 持久化pinia中的数据，确保刷新不会丢失
  persist: { paths: ['id', 'name', 'role', 'menus'] }
})
