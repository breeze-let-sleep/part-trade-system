// src/router/roleRoutes.js
// 元素结构完全同官方要求，component 用懒加载
//维护3种角色的路由
export const adminRoutes = [
  {
    path: '/',
    redirect: '/home',
    component: () => import('@/layouts/index.vue'),
    children: [
      {
        path: 'home',
        name: 'Home',
        meta: { title: '首页', icon: 'HomeFilled' },
        component: () => import('@/views/home.vue')
      },
      {
        path: 'info',
        name: 'Info',
        meta: { title: '个人信息', icon: 'UserFilled' },
        component: () => import('@/views/admin/info.vue')
      },
      {
        path: 'customerList',
        name: 'CustomerList',
        meta: { title: '顾客列表', icon: 'PhoneFilled' },
        component: () => import('@/views/admin/customerList.vue')
      },
      {
        path: 'adminList',
        name: 'AdminList',
        meta: { title: '管理员列表', icon: 'Avatar' },
        component: () => import('@/views/admin/adminList.vue')
      },
      {
        path: 'merchantList',
        name: 'MerchantList',
        meta: { title: '商家列表', icon: 'Shop' },
        component: () => import('@/views/admin/merchantList.vue')
      },
      {
        path: 'orderList',
        name: 'OrdertList',
        meta: { title: '订单列表', icon: 'List' },
        component: () => import('@/views/admin/orderList.vue')
      },
      {
        path: 'operateLog',
        name: 'OperateLog',
        meta: { title: '操作日志表', icon: 'WarningFilled' },
        component: () => import('@/views/admin/operateLog.vue')
      },
      {
        path: 'report',
        name: 'Report',
        meta: { title: '报表统计', icon: 'TrendCharts' },
        component: () => import('@/views/admin/report.vue')
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
        meta: { title: '首页', icon: 'HomeFilled' },
        component: () => import('@/views/home.vue')
      },
      {
        path: 'info',
        name: 'Info',
        meta: { title: '个人信息', icon: 'UserFilled' },
        component: () => import('@/views/merchant/info.vue')
      },
      {
        path: 'productList',
        name: 'ProductList',
        meta: { title: '零件管理', icon: 'Menu' },
        component: () => import('@/views/merchant/productList.vue')
      },
      {
        path: 'contractList',
        name: 'ContractList',
        meta: { title: '订单合同', icon: 'List' },
        component: () => import('@/views/merchant/contractList.vue')
      },
      {
        path: 'orderDetail',
        name: 'OrderDetail',
        meta: { title: '订单流程', icon: 'BellFilled' },
        component: () => import('@/views/merchant/orderDetail.vue')
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
        meta: { title: '首页', icon: 'HomeFilled' },
        component: () => import('@/views/home.vue')
      },
      {
        path: 'info',
        name: 'Info',
        meta: { title: '个人信息', icon: 'UserFilled' },
        component: () => import('@/views/customer/info.vue')
      },
      {
        path: 'shop',
        name: 'Shop',
        meta: { title: '零件购买', icon: 'Promotion' },
        component: () => import('@/views/customer/shop.vue')
      },
      {
        path: 'contractList',
        name: 'ContractList',
        meta: { title: '订单合同', icon: 'List' },
        component: () => import('@/views/customer/contractList.vue')
      },
      {
        path: 'message',
        name: 'Message',
        meta: { title: '消息列表', icon: 'BellFilled' },
        component: () => import('@/views/customer/message.vue')
      },
      {
        path: 'orderDetail',
        name: 'OrderDetail',
        meta: { title: '订单详情', icon: 'InfoFilled' },
        component: () => import('@/views/customer/orderDetail.vue')
      },
      {
        path: 'favoriteMerchant',
        name: 'FavoriteMerchant',
        meta: { title: '收藏商家', icon: 'StarFilled' },
        component: () => import('@/views/customer/favoriteMerchant.vue')
      },
      {
        path: 'robot',
        name: 'Robot',
        meta: { title: '智能客服', icon: 'HelpFilled' },
        component: () => import('@/views/customer/robot.vue')
      }
    ]
  }
]