<script setup>
import {ref,reactive,onMounted,watch} from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getProcessingList,getEndList,deliverOrder } from '@/api/orderDetail'

//-----------------分页相关-------------------
// 分页数据
const page = reactive({
  total: '',
  pageSize: 7,
  currentPage: 1
})

// 页码改变
const handleCurrentChange =async (val) => {
  console.log(`当前页: ${val}`)
  if(status.value==='processing'){
    const res = await getProcessingList(page.currentPage, page.pageSize)
    page.total = res.data.total
    processTableData.value = res.data.rows
  }else{
    const res = await getEndList(page.currentPage, page.pageSize)
    page.total = res.data.total
    endTableData.value = res.data.rows
  }
}

//-----------------头部相关-------------------
//选择按钮
const status = ref('processing') 
// 对status的状态进行监听
watch(status, async (newStatus) => {
  if (newStatus === 'processing') {
    const res = await getProcessingList(page.currentPage, page.pageSize)
    page.total = res.data.total
    processTableData.value = res.data.rows
  } else if (newStatus === 'end') {
    const res = await getEndList(page.currentPage, page.pageSize)
    page.total = res.data.total
    endTableData.value = res.data.rows
  }
})

//------------------表格相关--------------------
//表格数据
const processTableData = ref()
const endTableData = ref()

//发货
const deliver=(index) => { 
  ElMessageBox.confirm('确定发货吗', {
    title: '发货确认',
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'success',
  })
    .then(async() => {
      await deliverOrder(processTableData.value[index].orderId)
      const res = await getProcessingList(page.currentPage,page.pageSize)
      page.total = res.data.total
      processTableData.value = res.data.rows
      ElMessage({
        type: 'success',
        message: `已经成功发货`,
      })
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '已取消操作',
      })
    })
}

//-----------------生命周期钩子函数----------------------
onMounted(async() => { 
  const res = await getProcessingList(page.currentPage,page.pageSize)
  page.total = res.data.total
  processTableData.value = res.data.rows
})
</script>

<template>
  <div class="common-layout">
    <el-container class="container">
      <!-- 头部 -->
      <el-header class="header">
        <el-row>
          <el-col :span="24"><span class="page-title">订单流程管理</span></el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col>
            <div style="margin-top: 20px">
              <el-radio-group
                v-model="status"
                text-color="#626aef"
                fill="rgb(239, 240, 253)"
              >
                <el-radio-button label="进行中" value="processing" />
                <el-radio-button label="已结束" value="end" />
              </el-radio-group>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-divider content-position="left">零件交易系统</el-divider>
        </el-row>
      </el-header>
      <!-- 主体：表格+分页+按钮 -->
      <el-main class="main-content">
        <!-- 进行中表格 -->
        <el-table :data="processTableData" style="height: 90%" v-if="status === 'processing'">
          <el-table-column prop="isDeliver" label="是否发货" width="150" align="center">
            <template #default="scope">
              <el-tag v-if="scope.row.isDeliver === 1" type="success">已发货-运输中</el-tag>
              <el-tag v-else type="info">未发货</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="orderId" label="订单编号" width="120" align="center"/>
          <el-table-column prop="customerName" label="顾客姓名" width="180" align="center"/>
          <el-table-column prop="customerAddress" label="顾客地址" width="200" align="center"/>
          <el-table-column prop="totalPrice" label="订单实付金额（元）" width="160" align="center"/>
          <el-table-column prop="updateTime" label="修改时间" width="200" align="center"/>
          <el-table-column fixed="right" label="操作" min-width="120" align="center">
            <template #default="scope">
              <!-- 
                scope.$index 通常来自 Element UI 的表格组件（el-table），
                表示当前行的索引。-----可通过当前行索引获取数组里的对象，再获
                得该对象的属性值。
              -->
              <el-button
                type="primary"
                size="default"
                :disabled="scope.row.isDeliver === 1"
                @click="deliver(scope.$index)"
                round
              >
                发货
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 已结束表格 -->
        <el-table :data="endTableData" style="height: 90%" v-if="status === 'end'">
          <el-table-column prop="id" label="序号" width="120" align="center"/>
          <el-table-column prop="orderId" label="订单编号" width="120" align="center"/>
          <el-table-column prop="updateTime" label="收货时间" width="160" align="center"/>
          <el-table-column prop="star" label="星级评价" width="200" align="center">
            <template #default="scope">
              <el-icon v-for="i in scope.row.star" :key="i" color="rgb(255, 153, 0)"><StarFilled /></el-icon>
            </template>
          </el-table-column>
          <el-table-column prop="evaluate" label="评价详情" width="360" align="center"/>
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
.page-title {
  font-size: 28px;
  color: rgb(147, 71, 255);
  font-weight: bold;
}
.container {
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  flex: 0 0 auto;
  padding: 10px 0;
  height: 20vh;
}

.main-content {
  flex: 1;
  padding: 10px;
}

/* 表格 */
.table {
  width: 100%;
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