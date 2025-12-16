<script setup>
import { ref,onMounted } from 'vue'
import { useTransition } from '@vueuse/core'
import { ElNotification } from 'element-plus'
// 引入图表库chart.js
import { Chart, registerables } from 'chart.js'
Chart.register(...registerables)
//引入请求函数
import {getReportData} from '@/api/report'


// -----------------数据统计-----------------
//第一部分
//系统总用户数（管理员，商家，顾客）
const allUsers = ref()
// 已完成订单数
const hasDoneOrders = ref()
// 总订单数
const allOrders = ref()
// 累计成交总额
const totalMoney = ref(0)
// 输出动画
const outputValue = useTransition(totalMoney, {
  duration: 1500,
})
// 好评数量
const goodComments = ref()


// -----------------图表数据-----------------
//柱状图数据
const barLabels = ref()
const barData = ref()
//折线图数据
const lineLabels = ref()
const lineData = ref()
// 饼状图数据
const pieLabels = ref()
const pieData = ref()
// -----------------图表生成-----------------
//柱状图：零件销量前4名
const barRef = ref(null)
//饼状图：已完成订单数占总数比
const pieRef = ref(null)
//折线图：近4月销量（新产生的订单数）
const lineRef = ref(null)


const getData = async () => { 
  const res=await getReportData()
  if(res.code==1){
    // 统计数据
    allUsers.value=res.data.allUsers
    hasDoneOrders.value=res.data.hasDoneOrders
    allOrders.value=res.data.allOrders
    totalMoney.value=res.data.totalMoney
    goodComments.value=res.data.goodComments
    // 图表数据
    barLabels.value=res.data.barLabels
    barData.value=res.data.barData
    lineLabels.value=res.data.lineLabels
    lineData.value=res.data.lineData
    pieLabels.value=res.data.pieLabels
    pieData.value=res.data.pieData
  }else{
    ElNotification({
    title: '请求失败',
    message: '请刷新页面重新尝试',
    type: 'error',
  })
  }
  
}
//生命周期钩子函数
onMounted(async() => {
  await getData()
  new Chart(barRef.value, {
    type: 'bar',
    data: {
      labels: barLabels.value,
      datasets: [
        {
          label: '销量（件）',
          data: barData.value,
          backgroundColor: 'rgba(54, 162, 235)'
        }
      ]
    },
    options: {
      responsive: true,
      plugins: {
        title: { display: true, text: '零件销售前四分布图' }
      }
    }
  }),
  new Chart(pieRef.value, {
    type: 'pie',
    data: {
      labels: pieLabels.value,
      datasets: [{
        label: '数据详情',
        data: pieData.value,
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(54, 162, 235)',
        ],
        hoverOffset: 4
      }]
    },
    options: {
      responsive: true,
    }
  }),
  new Chart(lineRef.value, {
    type: 'line',
    data: {
      labels: lineLabels.value,
      datasets: [{
        label: '近四月月销量统计图',
        data: lineData.value,
        fill: false,
        borderColor: 'rgb(75, 192, 192)',
        tension: 0.1
      }]
    },
    options: {
      responsive: true,
    }
  })

})
</script>

<template>
  <!-- 报表统计：平台交易次数与总订单数占比；交易零件分布；交易满意程度分布 -->
  <!-- 数据展示表格 -->
  <div>
    <el-row :gutter="16">
      <span class="page-title">数据统计</span>
    </el-row>
    <el-divider content-position="left">零件交易系统</el-divider>
    <el-row :gutter="16">
      <el-col :xs="24" :sm="12" :md="6" class="text-center mb-4">
        <el-statistic title="总活跃人数" :value="allUsers" />
      </el-col>
      <el-col :xs="24" :sm="12" :md="6" class="text-center mb-4">
        <el-statistic :value="hasDoneOrders">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              已完成订单占总订单数比
              <el-icon style="margin-left: 4px" :size="12">
                <Coin />
              </el-icon>
            </div>
          </template>
          <template #suffix>/{{allOrders}}</template>
        </el-statistic>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6" class="text-center mb-4">
        <el-statistic title="累计成交总额（元）" :value="outputValue" />
      </el-col>
      <el-col :xs="24" :sm="12" :md="6" class="text-center mb-4">
        <el-statistic title="好评数" :value="goodComments">
          <template #suffix>
            <el-icon style="vertical-align: -0.125em">
              <ChatLineRound />
            </el-icon>
          </template>
        </el-statistic>
      </el-col>
    </el-row>
  </div>
  <el-divider />
  <div class="echart-container">
    <!-- 第一行：柱状图独占 -->
    <div class="chart-row full-width">
      <canvas ref="barRef"></canvas>
    </div>
    
    <!-- 第二行：折线图和饼图并排 -->
    <div class="chart-row half-width">
      <canvas ref="lineRef"></canvas>
      <canvas ref="pieRef"></canvas>
    </div>
  </div>
</template>

<style scoped>
.page-title {
  font-size: 28px;
  color: rgb(147, 71, 255);
  font-weight: bold;
}
.echart-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.chart-row {
  display: flex;
  justify-content: space-around;
  align-items: center;
  gap: 15px;
}

.chart-row.full-width canvas {
  width: 90% !important;
  height: 600px !important;
}

.chart-row.half-width canvas {
  width: 48% !important;
  height: 400px !important;
}
</style>