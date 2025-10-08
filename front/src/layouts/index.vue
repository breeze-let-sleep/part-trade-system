<script setup>
import { computed } from 'vue'
import { useUserStore } from '@/store/user.js'
import SideMenu from './sideMenu.vue'

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
          <span style="margin-right: 5px; color: #ffffff;">当前角色：{{ roleTag }}</span>
          <el-button link @click="userStore.logout(); $router.push('/login')">
            <el-icon color="#ffffff"><Remove /></el-icon><span style="color: #ffffff">退出</span>
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
  /* background-color: #a57eff; */
  /* 设置紫色渐变色背景 */
  background: linear-gradient(135deg, #510ff7 0%, #673ab7 100%);  
}
.logo {
  margin-right: 8px;
  margin-top: 1px; 
  color: #ffffff
}
.title {
  font-size: 28px; 
  font-weight: bold; 
  /* 设置渐变色字体 */
  color: #ffffff;
}
.flex-row {
  display: flex;
}
</style>
