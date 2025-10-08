<!-- src/layout/SideMenu.vue -->
<!--递归菜单组件-->
<template>
  <el-menu
    :default-active="$route.path"
    router
    unique-opened
  >
    <template v-for="item in menuList" :key="item.path">
      <el-sub-menu
        v-if="item.children && item.children.length"
        :index="item.path"
      >
        <template #title>
          <el-icon v-if="item.meta.icon">
            <component :is="item.meta.icon" />
          </el-icon>
          <span>{{ item.meta.title }}</span>
        </template>
        <SideMenu :menuList="item.children" />
      </el-sub-menu>

      <el-menu-item v-else :index="item.path">
        <el-icon v-if="item.meta.icon">
          <component :is="item.meta.icon" />
        </el-icon>
        <span>{{ item.meta.title }}</span>
      </el-menu-item>
    </template>
  </el-menu>
</template>

<script setup>
defineProps({ menuList: Array })
</script>