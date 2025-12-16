<script setup>
import { ref, reactive,onMounted } from 'vue'
import { Search } from '@element-plus/icons-vue'
import { getEndLikeOrders } from '@/api/order'
//-----------------搜索相关-------------------
// 零件名
const partName = ref('')
// 时间选择器数据
const overTime = ref('')
//格式化时间数据
/* ------------注意：JavaScript 不允许对 const 声明的变量进行重新赋值：
1、声明为let或var
2、声明为响应式数据
*/
const startTime = ref('')
const endTime = ref('')
// 订单号
const orderId = ref('')
// 评价星级
const star = ref(0)
/*-------注意：时间选择器获得的数据是类Date数据即
（0: Sat Nov 01 2025 00:00:00 GMT+0800 (中国标准时间)），因此要转为标准格式：yyyy-MM-dd HH:mm:ss
方法：还原为Date数据再进行格式化即可
*/
// 格式化时间
const toFormatTime = (str) => { 
  if (!str) return null; // 处理空值
  
  const d = new Date(str);
  if (isNaN(d.getTime())) return null; // 处理无效日期
  
  const pad = n => n.toString().padStart(2, '0');
  return `${d.getFullYear()}-${pad(d.getMonth() + 1)}-${pad(d.getDate())} ${pad(d.getHours())}:${pad(d.getMinutes())}:${pad(d.getSeconds())}`;
}
// 搜索
const search =async () => { 
if (overTime.value === null || overTime.value === undefined) {
    startTime.value = null
    endTime.value = null
  }else{
    startTime.value = toFormatTime(overTime.value[0])
    endTime.value = toFormatTime(overTime.value[1])
  }
  //-----------------有值传值，无值不传（Axios 的params参数会自动忽略undefined）
  if(star.value===0){
    star.value = null
  }
  const res=await getEndLikeOrders(partName.value,orderId.value,star.value,startTime.value,endTime.value,page.currentPage,page.pageSize)
  page.total = res.data.total
  tableData.value=res.data.rows
}
//重置搜索框
const resetSearch = () => { 
  partName.value = ''
  overTime.value = ''
  orderId.value = ''
  star.value = 0
}

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
  const res = await getEndLikeOrders(null,null,null,null,null,page.currentPage,page.pageSize)
  page.total = res.data.total
  tableData.value = res.data.rows
}
//-----------------表格相关-------------------
// 数据源
const tableData = ref()

onMounted(async() => { 
  const res = await getEndLikeOrders(null,null,null,null,null,page.currentPage,page.pageSize)
  page.total = res.data.total
  tableData.value = res.data.rows
})
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header">
        <el-row>
          <el-col :span="24">
            <span class="page-title">订单列表</span>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="24">
            <div class="search-container">
              <label class="search-label">零件名：</label>
              <el-input
                v-model="partName"
                style="width: 50px;"
                placeholder="输入零件名进行模糊查找"
                :prefix-icon="Search"
                class="search-input"
              />
              <label class="search-label">交易时间：</label>
                <el-date-picker
                  v-model="overTime"
                  style="width: 320px;"
                  type="datetimerange"
                  start-placeholder="起始时间"
                  end-placeholder="结束时间"
                  format="YYYY-MM-DD HH:mm:ss"
                  date-format="YYYY/MM/DD ddd"
                  time-format="A hh:mm:ss"
                />
              
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="24">
            <div class="search-container">
              <label class="search-label">订单号：</label>
              <el-input
                v-model="orderId"
                style="width: 60px;"
                placeholder="输入订单号进行模糊查找"
                :prefix-icon="Search"
                class="search-input"
              />
              <label class="search-label">订单星级：</label>
              <el-input-number 
                v-model="star" 
                :min="0" 
                :max="5" 
                @change="handleChange"
                style="width: 40px;"
                placeholder="输入评价星级查找"
                :prefix-icon="Search"
                class="search-input"
              />
              <el-button class="search-button" type="primary" plain @click="search">搜索</el-button>
              <el-button class="search-button" type="info" plain @click="resetSearch">重置</el-button>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-divider content-position="left">零件交易系统</el-divider>
        </el-row>
      </el-header>
      <el-main class="main">
        <el-table
          :data="tableData"
          border
          style="padding: 20px;width: 100%;height: 90%;"
        >
          <el-table-column prop="updateTime" label="最后更新时间" width="160" />
          <el-table-column prop="orderId" label="订单编号" width="120" />
          <el-table-column prop="merchantName" label="供应商名字" width="160" />
          <el-table-column prop="customerName" label="顾客名字" width="160" />
          <el-table-column prop="partName" label="零件名字" width="160" />
          <el-table-column prop="amount" label="交易数量" width="160" />
          <el-table-column prop="totalPrice" label="交易金额" width="120" />
          <el-table-column prop="star" label="星级评价" width="120" >
            <template #default="scope">
              <!-- 如果星级评价低于2星，则高亮显示 -->
              <span :class="{ 'highlight-row':  scope.row.star < 2 }">
                {{ scope.row.star }}
              </span>
            </template>
          </el-table-column>
          <el-table-column prop="evaluate" label="评价详情" width="300"/>
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
  margin-top: 80px;
  height: 70vh;
}
.page-title {
  font-size: 28px;
  color: rgb(147, 71, 255);
  font-weight: bold;
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

.header {
  flex: 0 0 auto;
  padding: 10px 0;
  height: 20vh;
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

.el-col{
  margin-bottom: 10px;
}
</style>