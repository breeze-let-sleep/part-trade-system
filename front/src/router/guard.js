import { useUserStore } from '@/store/user.js'
import router from './index.js'

let restored = false   // 只恢复一次

router.beforeEach(async (to, from, next) => {
  const userStore = useUserStore()

  // 第一次/刷新：同步恢复角色 & 路由
  if (!restored) {
    restored = true
    const ok = await userStore.restore()   // true=解析成功  false=失败或无 jwt
    if (!ok) {
      // 任何失败 => 都去登录页
      return next('/login')
    }
    // 恢复成功：把“本来想进的那条路径”重新走一遍（此时动态路由已装好）
    return next({ path: to.fullPath, replace: true })
  }

  // 无 token → 去登录
  if (!userStore.token) {
    if (to.path === '/login') return next()
    return next('/login')
  }

  // 有 token 但进了 404 → 推回首页
  if (to.name === '404') {
    return next('/')
  }

  next()
})