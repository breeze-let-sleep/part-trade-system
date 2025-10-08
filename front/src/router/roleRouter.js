// src/router/roleRoutes.js
// 元素结构完全同官方要求，component 用懒加载
export const adminRoutes = [
  {
    path: '/',
    redirect: '/home',
    component: () => import('@/layouts/index.vue'),
    children: [
      {
        path: 'home',
        name: 'Home',
        meta: { title: '首页', icon: 'Odometer' },
        component: () => import('@/views/home.vue')
      },
      {
        path: 'info',
        name: 'Info',
        meta: { title: '个人信息', icon: 'Odometer' },
        component: () => import('@/views/admin/info.vue')
      }
    ]
  }
]

export const merchantRoutes = [
  {
    path: '/',
    redirect: '/home',
    component: () => import('@/layouts/index.vue'),
    children: [
      {
        path: 'home',
        name: 'Home',
        meta: { title: '首页', icon: 'Odometer' },
        component: () => import('@/views/home.vue')
      },
      {
        path: 'info',
        name: 'Info',
        meta: { title: '个人信息', icon: 'Odometer' },
        component: () => import('@/views/merchant/info.vue')
      }
    ]
  }
]

export const customerRoutes = [
  {
    path: '/',
    redirect: '/home',
    component: () => import('@/layouts/index.vue'),
    children: [
      {
        path: 'home',
        name: 'Home',
        meta: { title: '首页', icon: 'Odometer' },
        component: () => import('@/views/home.vue')
      },
      {
        path: 'info',
        name: 'Info',
        meta: { title: '个人信息', icon: 'Odometer' },
        component: () => import('@/views/customer/info.vue')
      }
    ]
  }
]