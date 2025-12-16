<script setup>
import { ref, reactive,onMounted } from 'vue'
import { getLogs } from '@/api/logs'


//-----------------分页相关-------------------
// 分页数据
const page = reactive({
  total: '',
  pageSize: 7,
  currentPage: 1
})

// 页码改变
const handleCurrentChange = async(val) => {
  console.log(`当前页: ${val}`)
  page.currentPage = val
  const res =await getLogs(page.currentPage,page.pageSize)
  page.total = res.data.total
  tableData.value = res.data.rows
}
//-----------------表格相关-------------------
// 数据源
const tableData = ref()

onMounted(async() => { 
  const res =await getLogs(page.currentPage,page.pageSize)
  page.total = res.data.total
  tableData.value = res.data.rows
})
</script>

<template>
<!-- 操作日志列表：查询后端相关数据表，将数据（根据协议表的创建时间进行排序）
  返回回来进行分页展示（只可查看，不可操作） -->
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="24">
            <h1 class="page-title">操作日志</h1>
            <el-divider content-position="left">零件交易系统</el-divider>
          </el-col>
        </el-row>
      </el-header>
      <el-main class="main">
        <el-table
          :data="tableData"
          border
          style="padding: 20px;width: 100%;height: 90%;"
        >
          <el-table-column prop="createTime" label="操作日期" width="180" />
          <el-table-column prop="id" label="序号" width="120" />
          <el-table-column prop="adminId" label="操作人ID" width="120" />
          <el-table-column prop="name" label="操作人姓名" width="120" />
          <el-table-column prop="method" label="操作方法详情" width="400">
            <template #default="scope">
              <span :class="{ 'highlight-row': scope.row.method.includes('delete') }">
                {{ scope.row.method }}
              </span>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <div class="pagination-container">
          <el-pagination
            v-model:current-page="page.currentPage"
            v-model:page-size="page.pageSize"
            size="large"
            :background="true"
            layout="prev, pager, next, jumper"
            :total="page.total"
            @current-change="handleCurrentChange"
            :pager-count="5"
          />
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
.main {
  padding: 20px;
  margin-top: 18px;
  height: 70vh;
}
.page-title {
  font-size: 28px;
  color: rgb(147, 71, 255);
}
.highlight-row {
  color: red;
  font-weight: bold;
}
/* 分页 */
.pagination-container {
  display: flex;
  justify-content: left;
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