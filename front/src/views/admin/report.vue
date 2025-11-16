<script setup>
import { ref,onMounted } from 'vue'
import { useTransition } from '@vueuse/core'
// 引入图表库chart.js
import { Chart, registerables } from 'chart.js'
Chart.register(...registerables)

// -----------------数据统计-----------------
//系统总用户数（管理员，商家，顾客）
const allUsers = ref(2671)
// 已完成订单数
const hasDoneOrders = ref(1627)
// 总订单数
const allOrders = ref(2529)
// 累计成交总额
const totalMoney = ref(0)
// 输出动画
const outputValue = useTransition(totalMoney, {
  duration: 1500,
})
totalMoney.value = 172000
// 平均好评率
const goodComments = ref(1518)
const getStatistics = async () => { 
  //todo：获取数据统计相关参数
}

// -----------------图表显示-----------------
//柱状图：零件销量前3名
const barRef = ref(null)
//饼状图：已完成订单数占总数比
const pieRef = ref(null)
//折线图：近4月销量（新产生的订单数）
const lineRef = ref(null)

const getCharts = async () => { 
  //todo：获取图表数据
}
//生命周期钩子函数
onMounted(() => {
  getStatistics()
  getCharts()
  new Chart(barRef.value, {
    type: 'bar',
    data: {
      labels: ['零件a', '零件c', '零件b', '其他'],
      datasets: [
        {
          label: '销量（件）',
          data: [120, 190, 80, 150],
          backgroundColor: 'rgba(54, 162, 235)'
        }
      ]
    },
    options: {
      responsive: true,
      plugins: {
        title: { display: true, text: '销售零件前三分布图' }
      }
    }
  }),
  new Chart(pieRef.value, {
    type: 'pie',
    data: {
      labels: [
        '已完成订单数',
        '总订单数',
      ],
      datasets: [{
        label: '已完成订单数',
        data: [1627, 2529],
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
      labels: ["8月","9月", "10月", "11月"],
      datasets: [{
        label: '近四月月销量统计图',
        data: [24, 56, 55, 40],
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
        <el-statistic title="累计成交总额（万）" :value="outputValue" />
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