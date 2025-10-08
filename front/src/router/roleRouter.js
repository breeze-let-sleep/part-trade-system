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
        meta: { title: '顾客列表', icon: 'GoodsFilled' },
        component: () => import('@/views/admin/customerList.vue')
      },
      {
        path: 'merchantList',
        name: 'MerchantList',
        meta: { title: '商家列表', icon: 'Shop' },
        component: () => import('@/views/admin/merchantList.vue')
      },
      {
        path: 'contractList',
        name: 'ContractList',
        meta: { title: '合同列表', icon: 'List' },
        component: () => import('@/views/admin/contractList.vue')
      },
      {
        path: 'tradeLog',
        name: 'TradeLog',
        meta: { title: '交易日志表', icon: 'InfoFilled' },
        component: () => import('@/views/admin/tradeLog.vue')
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
        meta: { title: '产品列表', icon: 'Menu' },
        component: () => import('@/views/merchant/productList.vue')
      },
      {
        path: 'shop',
        name: 'Shop',
        meta: { title: '顾客求购列表', icon: 'Shop' },
        component: () => import('@/views/merchant/shop.vue')
      },
      {
        path: 'message',
        name: 'Message',
        meta: { title: '消息列表', icon: 'BellFilled' },
        component: () => import('@/views/merchant/message.vue')
      },
      {
        path: 'contractList',
        name: 'ContractList',
        meta: { title: '合同列表', icon: 'List' },
        component: () => import('@/views/merchant/contractList.vue')
      },
      {
        path: 'tradeLog',
        name: 'TradeLog',
        meta: { title: '交易日志', icon: 'InfoFilled' },
        component: () => import('@/views/merchant/tradeLog.vue')
      },
      {
        path: 'robot',
        name: 'Robot',
        meta: { title: '智能客服', icon: 'HelpFilled' },
        component: () => import('@/views/merchant/robot.vue')
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
        path: 'publish',
        name: 'Publish',
        meta: { title: '发布求购列表', icon: 'Promotion' },
        component: () => import('@/views/customer/publish.vue')
      },
      {
        path: 'favoriteMerchant',
        name: 'FavoriteMerchant',
        meta: { title: '收藏商家', icon: 'StarFilled' },
        component: () => import('@/views/customer/favoriteMerchant.vue')
      },
      {
        path: 'message',
        name: 'Message',
        meta: { title: '消息列表', icon: 'BellFilled' },
        component: () => import('@/views/customer/message.vue')
      },
      {
        path: 'contractList',
        name: 'ContractList',
        meta: { title: '合同列表', icon: 'List' },
        component: () => import('@/views/customer/contractList.vue')
      },
      {
        path: 'tradeLog',
        name: 'TradeLog',
        meta: { title: '交易日志', icon: 'InfoFilled' },
        component: () => import('@/views/customer/tradeLog.vue')
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