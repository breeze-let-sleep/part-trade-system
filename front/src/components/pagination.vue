<template>
  <div class="pagination-container">
    <el-pagination
      v-model:current-page="innerCurrent"
      v-model:page-size="innerSize"
      size="large"
      :background="true"
      layout="prev, pager, next, jumper"
      :total="total"
      :pager-count="5"
      @current-change="handleChange"
      @size-change="handleChange"
    />
  </div>
</template>

<script setup>
/*
使用方法：
1、在要使用分页的vue文件(主文件)的script中引入
import Pagination from '@/components/Pagination.vue'
2、在template中调用
  <Pagination
    v-model:currentPage="主文件的page对象当前页属性"
    v-model:pageSize="主文件的page对象页大小属性"
    :total="主文件的page对象总页数属性"
    @change="getList"
  />
3、在使用分页的vue文件（主文件）的script中定义页参数和获取数据方法
const page = reactive({
  total: 100,
  pageSize: 7,
  currentPage: 1
})
-------------------以下包括js代码没搞懂------------------
function getList({ current, size }) {
  console.log('拉取数据 -> 页码:', current, '每页条数:', size)
  // 这里调后端接口即可
}
*/
import { computed } from 'vue'

/* ===== 父组件传参 ===== */
const props = defineProps({
  total:       { type: Number, required: true },   // 总条数
  pageSize:    { type: Number, default: 10 },      // 每页条数
  currentPage: { type: Number, default: 1 }       // 当前页
})

/* ===== 向父组件回传事件 ===== */
const emit = defineEmits(['update:currentPage', 'update:pageSize', 'change'])

/* 用计算属性实现“双向绑定” */
const innerCurrent = computed({
  get: () => props.currentPage,
  set: val => emit('update:currentPage', val)
})
const innerSize = computed({
  get: () => props.pageSize,
  set: val => emit('update:pageSize', val)
})

/* 页码或页大小变化时统一抛事件 */
function handleChange() {
  emit('change', { current: innerCurrent.value, size: innerSize.value })
}
</script>

<style scoped>
.pagination-container {
  display: flex;
  justify-content: center;
}

:deep(.el-pagination) {
  padding: 15px 20px;
  border-radius: 8px;
}

:deep(.el-pagination .el-pager li) {
  min-width: 35px;
  font-size: 16px;
}

:deep(.el-pagination .el-pagination__jump) {
  font-size: 16px;
}

:deep(.el-pagination .el-pagination__editor) {
  font-size: 16px;
}
</style>