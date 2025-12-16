<script setup>
import {ref,reactive,onMounted,nextTick,watch} from 'vue'
import { Search } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/user.js'
import {getPageList, getOrderDetail,signatureContract} from '@/api/contract'
import {removeOrder} from '@/api/order'

//全局变量
const userStore = useUserStore()

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
  page.currentPage = val
  const res=await getPageList(page.currentPage,page.pageSize)
  page.total = res.data.total
  tableData.value=res.data.rows
}

//-----------------头部相关-------------------
//动态路由对象
const router = useRouter()
//选择按钮
const hasCompleted = ref(true)  //默认展示未完成的（如果选择已完成，则跳转到orderDetail页面）
//监听hasCompleted变化
watch(hasCompleted, (newValue, oldValue) => {
  if (newValue === false) { 
    router.push({path: '/orderDetail'})
  }
})

//------------------表格相关--------------------
const colors = ['红色', '黄色', '绿色', '蓝色', '白色', '黑色']
// 订单详情
const orderDetail = ref()

//表格数据
const tableData = ref()

//展示详情
const showDetail=async(index) => { 
  //index为当前行索引
  const res=await getOrderDetail(tableData.value[index].orderId)
  console.log(res.data)
  orderDetail.value = res.data
  dialogVisible.value = true
}
//签名
const signature=(index) => { 
  ElMessageBox.prompt('请确认无误后输入您的签名', '订单合同确认', {
    confirmButtonText: '已确认无误，提交',
    cancelButtonText: '取消',
    type: 'success',
  })
    .then(async({ value }) => {
      const res = await signatureContract(tableData.value[index].orderId,value)
      if(res.code === 1){
        ElMessage({
          type: 'success',
          message: `“${value}”您已成功签署该订单合同`,
        })
        const res=await getPageList(page.currentPage,page.pageSize)
        page.total=res.data.total
        tableData.value=res.data.rows
      }else{ 
        ElMessage.primary(res.data)
      }
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '已取消操作',
      })
    })
}
//取消
const cancel=(index) => { 
  ElMessageBox.prompt(
    '确定取消该订单吗？如属实，请输入取消原因：',
    '订单操作',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'error',
    }
  )
  // then: 确定按钮点击事件
    .then(async() => {
      const res=await removeOrder(tableData.value[index].orderId)
      if(res.code === 1){
        ElMessage({
          type: 'success',
          message: '取消成功',
        })
      }
    })
  // catch: 取消按钮点击事件
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消操作',
      })
    }
  )
}



//-----------------对话框相关-------------------
//对话框显示标志
const dialogVisible = ref(false)
//打开添加管理员对话框（添加管理员按钮事件）
const close = () => {
  dialogVisible.value = false
}


//-----------------生命周期钩子函数----------------------
onMounted(async() => { 
  const res=await getPageList(page.currentPage,page.pageSize)
  page.total=res.data.total
  tableData.value=res.data.rows
})
</script>

<template>
  <div class="common-layout">
    <el-container class="container">
      <!-- 头部：搜索相关组件 -->
      <el-header class="header">
        <el-row>
          <el-col :span="24"><span class="page-title">订单合同列表</span></el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col>
            <el-switch
              v-model="hasCompleted"
              class="mb-2"
              active-text="未完成签名"
              inactive-text="已完成签名"
            />
          </el-col>
        </el-row>
        <el-row>
          <el-divider content-position="left">零件交易系统</el-divider>
        </el-row>
      </el-header>
      <!-- 主体：表格+分页+按钮 -->
      <el-main class="main-content">
        <el-table :data="tableData" style="height: 90%">
          <el-table-column prop="orderId" label="订单号" width="120" align="center"/>
          <el-table-column prop="partName" label="购买零件名称" width="120" align="center"/>
          <el-table-column prop="amount" label="购买数量(件)" width="150" align="center"/>
          <el-table-column prop="totalPrice" label="金额(元)" width="150" align="center"/>
          <el-table-column prop="createTime" label="创建时间" width="180" align="center"/>
          <el-table-column fixed="right" label="操作" min-width="300" align="center">
            <template #default="scope">
              <!-- 
                scope.$index 通常来自 Element UI 的表格组件（el-table），
                表示当前行的索引。-----可通过当前行索引获取数组里的对象，再获
                得该对象的属性值。
              -->
              <el-button
                type="success"
                size="default"
                @click="showDetail(scope.$index)"
                round
              >
                查看详情
              </el-button>
              <el-button
                type="primary"
                size="default"
                @click="signature(scope.$index)"
                round
              >
                签名
              </el-button>
              <el-button
                type="info"
                size="default"
                @click="cancel(scope.$index)"
                round
              >
                取消订单
              </el-button>
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
  <!-- 对话框 -->
  <el-dialog
    v-model="dialogVisible"
    title="订单详情页"
    width="500"
    align-center
  >
    <p style="text-align: left;font-size: 16px;">
      订单号：[<span>{{orderDetail.orderId}}</span>]，由采购方【<span>{{orderDetail.customerName}}</span>】与
      供应方【<span>{{orderDetail.merchantName}}</span>】于<span>{{orderDetail.createTime}}</span>签订本订单合同，
      采购方同意向供应方采购<span>{{orderDetail.partName}}</span>（颜色：<span>{{colors[orderDetail.color-1]}}</span>，
      重量：<span>{{orderDetail.weight}}</span>KG），该零件单价为[<span>{{orderDetail.singlePrice}}</span>元]，
      购买数量为[<span>{{orderDetail.amount}}</span>件]，订单总价合计[<span>{{orderDetail.totalPrice}}</span>元]，
      双方确认上述交易信息无误，同意按此履行交货及付款等相关义务。
    </p>
    <!-- 表单按钮 -->
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="close">关闭</el-button>
      </div>
    </template>
  </el-dialog>
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

/* 搜索栏 */
.search-container {
  display: flex;
  align-items: center;
  height: 100%;
}

.search-label {
  white-space: nowrap;
  margin: 10px;
}

.search-input {
  flex: 1;
  width: 50px;
}

.search-button {
  margin-left: 10px;
}

/* 表格 */
.table {
  width: 100%;
}

/* 添加按钮 */
.add-btn {
  margin-top: 16px;
  padding: 15px;
  font-size: 18px;
  font-weight: bold;
  background-color: #409eff;
  border: none;
  border-radius: 8px;
  color: white;
  cursor: pointer;
  transition: all 0.3s;
  /* 位于父元素右侧 */
  position: absolute;
  right: 50px;
}

.add-btn:hover {
  background-color: #66b1ff;
  transform: translateY(-2px);
  box-shadow: 0 4px 15px 0 rgba(0, 0, 0, 0.1);
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
/* 对话框图像 */
.avatar-uploader .avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  display: block;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  text-align: center;
}

</style>