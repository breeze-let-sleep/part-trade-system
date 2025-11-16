<script setup>
import { ref, reactive } from 'vue'
import { ElNotification } from 'element-plus'


//-----------------分页相关-------------------
// 分页数据
const page = reactive({
  //todo：在钩子函数中进行获取数据
  total: 100,
  pageSize: 7,
  currentPage: 1
})

// 页码改变
const handleCurrentChange = (val) => {
  console.log(`当前页: ${val}`)
  // TODO: 调用后端API获取对应页的数据
}

//-----------------头部---------------------
// 订单状态按钮
const status = ref('processing')
//-----------------表格相关-------------------
// 数据源
const processingTableData = ref([
  {
    updateDate: '2016-05-03 10:00:00',
    id: '001',  //订单流程id主键
    orderId: '12009',
    isDeliver: 0, //用于物流展示（0：未发货；1：已发货-运输中）
  },
  {
    updateDate: '2016-05-03 10:00:00',
    id: '001',  //订单流程id主键
    orderId: '12009',
    isDeliver: 1, //用于物流展示
  },
])
const endTableData = ref([
  {
    id: '001',
    orderId: '12009',
    updateTime: '2022-01-01 12:00:00',
    star: 5,
    evaluate: '这个零件很棒', 
  },
  {
    id: '001',
    orderId: '12009',
    updateTime: '2022-01-01 12:00:00',
    star: 1,
    evaluate: '这个零件很low', 
  },
])
//当前行索引
const currentIndex = ref(null)
//点击确认收货按钮
const takeGoods=(index) => { 
  currentIndex.value = index
  dialogVisible.value = true
}

//-------------------对话框相关-------------------
// 对话框可见
const dialogVisible = ref(false)
//评价对象
const dialogData = ref({
  id: '',
  star: 0,
  evaluate: '',
})
//确认收货
const confirmTakeGoods=() => { 
  //在对话框内评价星级和内容
  dialogData.value.id = processingTableData.value[currentIndex.value].id
  //todo：发送支付请求到后端确认收货
  dialogVisible.value = false
  ElNotification({
    title: '操作成功',
    message: '感谢您的评价，期待您的下次合作',
    type: 'success',
  })
}
//暂时取消
const cancel=() => { 
  dialogVisible.value = false
}
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header">
        <el-row>
          <el-col :span="24">
            <h1 class="page-title">订单跟踪</h1>
          </el-col>
        </el-row>
        <el-row gutter="10">
          <el-col :span="24">
            <el-radio-group
              v-model="status"
              text-color="#626aef"
              fill="rgb(239, 240, 253)"
            >
              <el-radio-button label="未完成" value="processing" />
              <el-radio-button label="已完成" value="end" />
            </el-radio-group>
            <el-divider content-position="left">零件交易系统</el-divider>
          </el-col>
        </el-row>
      </el-header>
      <el-main class="main">
        <!-- 未完成状态的表格 -->
        <el-table
          :data="processingTableData"
          style="padding: 20px;width: 100%;height: 90%;"
          v-if="status === 'processing'"
        >
          <el-table-column prop="updateDate" label="日期" width="180" />
          <el-table-column prop="id" label="ID" width="120" />
          <el-table-column prop="orderId" label="订单号" width="140" />
          <el-table-column prop="isDeliver" label="物流状态" width="360" >
            <template #default="scope">
              <el-steps :active="scope.row.isDeliver" finish-status="success" process-status="finish" align-center>
                <el-step title="未发货"/>
                <el-step title="已发货-运输中" />
                <!-- 应该为已送达（由其他人员来实现--------后续再完善） -->
                <el-step title="已收货-完成"/>  
              </el-steps>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="120">
            <template #default="scope">
              <!-- 将当前行索引作为参数传入 -->
              <el-button type="primary" :round="true" @click="takeGoods(scope.$index)">确认收货</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 完成状态的表格 -->
        <el-table
          :data="endTableData"
          style="padding: 20px;width: 100%;height: 90%;"
          v-if="status === 'end'"
        > 
          <el-table-column prop="id" label="ID" width="120" align="center"/>
          <el-table-column prop="orderId" label="订单ID" width="120" align="center"/>
          <el-table-column prop="updateTime" label="签收时间" width="160" align="center"/>
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
      <!-- 对话框 -->
      <el-dialog
        v-model="dialogVisible"
        title="订单评价详情"
        width="500"
        align-center
      >
        <div>
          <el-row gutter="10">
            <el-col :span="24">
              <label style="font-size: 16px;font-weight: bold;">星级评价：</label>
              <el-rate
                v-model="dialogData.star"
                :texts="['😱', '😩', '😊', '😃', '😘']"
                size="large"
                show-text
              />
            </el-col>
          </el-row>
          <el-row gutter="10">
            <el-col :span="24">
              <label style="font-size: 16px;font-weight: bold;">评价内容：</label>
              <el-input 
                type="textarea" 
                v-model="dialogData.evaluate" 
                rows="3" 
                placeholder="请输入评价内容" 
              />
            </el-col>
          </el-row>
        </div>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="cancel()">取消</el-button>
            <el-button type="primary" @click="confirmTakeGoods()">
              零件已确认无误，提交
            </el-button>
          </div>
        </template>
      </el-dialog>
    </el-container>
  </div>
</template>

<style scoped>
.header {
  flex: 0 0 auto;
  padding: 10px 0;
  height: 20vh;
}
.main {
  padding: 20px;
  margin-top: 20px;
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
.col-span {
  font-size: 16px;
}
</style>