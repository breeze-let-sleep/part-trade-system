<script setup>
// import SideMenu from './SideMenu.vue'
// import { useUserStore } from '@/store/user'
// const userStore = useUserStore()

import { computed } from 'vue'
import { useUserStore } from '@/store/user.js'
import SideMenu from './sideMenu.vue'

// const userStore= {role:1, username:'admin'}
//数字 → 中文 展示用
// const roleLabel = { 0: '管理员', 1: '商家', 2: '顾客' }


const userStore = useUserStore()
const roleTag = computed(() => {
  const map = { 0: '管理员', 1: '商家', 2: '顾客' }
  return map[userStore.role] || '未知'
})
</script>

<template>
  <div>
    <el-container style="height: 100vh">
      <!-- 头部 -->
      <el-header class="toolbar">
        <div class="flex-row">
          <!-- logo -->
          <el-icon :size="40" class="logo">
              <setting />
          </el-icon>
          <!-- 标题 -->
          <span class="title">零件交易系统</span>
        </div>
        <!-- 位于header的最右侧 -->
        <div style="flex: 1; text-align: right;"> 
          <!-- 距离右边按钮2px，文字颜色为#0077ed -->
          <!-- <span style="margin-right: 5px; color: #0077ed;">当前角色：{{ roleLabel[userStore.role] }}</span> -->
          <span style="margin-right: 5px; color: #0077ed;">当前角色：{{ roleTag }}</span>
          <el-button link @click="userStore.logout(); $router.push('/login')">
            <el-icon><Remove /></el-icon>退出
          </el-button>
        </div>
      </el-header>
      <el-container>
        <!-- 侧边栏 -->
        <el-aside width="200px">
          <!-- 递归菜单 -->
          <SideMenu :menuList="userStore.menus" />
        </el-aside>
        <!-- 内容栏 -->
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 80px;
  right: 20px;
  background-color: #a57eff;
}
.logo {
  margin-right: 8px;
  margin-top: 1px; 
  color: #0077ed
}
.title {
  font-size: 28px; 
  font-weight: bold; 
  /* 设置渐变色字体 */
  color: #303133;
  background: linear-gradient(to right, #ed8200, #a5ff7e);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}
.flex-row {
  display: flex;
}
</style>
