<script setup>
import { ref, reactive,onMounted } from 'vue'
import { ElNotification } from 'element-plus'
//-----------------表格相关-------------------
// 数据源
const msgList = ref([
  {
    createTime: '2016-05-03 10:00:00',
    id: '001',
    partName: 'Tom',
    amount: 10,
    totalPrice: 100,
  },
])
//当前行索引
const currentIndex = ref(null)
// 打开支付对话框进行支付
const goToPay=(index) => { 
  paidWay.value = 1
  currentIndex.value = index
  dialogVisible.value = true
}

//-------------------对话框相关-------------------
// 支付方式（目前仅做展示）
const paidWay = ref(1)
// 对话框可见
const dialogVisible = ref(false)
// 支付
const pay=() => { 
  //todo：发送支付请求到后端（通过currentIndex获取订单号作为参数）
  dialogVisible.value = false
  ElNotification({
    title: '支付成功',
    message: '订单成功支付，可前往订单详情页面查看具体订单流程',
    type: 'success',
  })
}
//取消支付
const cancel=() => { 
  dialogVisible.value = false
}

//-------------------生命周期函数-------------------
onMounted(() => { 
  // todo：获取待支付订单消息（is_paid=0的订单）
})
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="24">
            <h1 class="page-title">待支付订单消息</h1>
            <el-divider content-position="left">零件交易系统</el-divider>
          </el-col>
        </el-row>
      </el-header>
      <el-main class="main">
        <el-table
          :data="msgList"
          border
          style="padding: 20px;width: 100%;height: 90%;"
        >
          <el-table-column prop="createTime" label="购买日期" width="180" />
          <el-table-column prop="id" label="订单号" width="160" />
          <el-table-column prop="partName" label="购买零件" width="140" />
          <el-table-column prop="amount" label="购买数量" width="140" />
          <el-table-column prop="totalPrice" label="待支付金额(元)" width="180" >
            <template #default="scope">
              <el-tag type="danger" size="large">{{ scope.row.totalPrice }}元</el-tag>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="120">
            <template #default="scope">
              <!-- 将当前行索引作为参数传入 -->
              <el-button type="primary" :round="true" @click="goToPay(scope.$index)">去支付</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-empty v-if="msgList.length === 0" description="暂无消息" />
      </el-main>
      <!-- 对话框 -->
      <el-dialog
        v-model="dialogVisible"
        title="订单支付详情"
        width="500"
        align-center
      >
        <div>
          <el-row gutter="10">
            <el-col :span="24">
              <span class="col-span">订单号：[{{ msgList[currentIndex].id }}]，共需您支付的金额为：</span>
            </el-col>
          </el-row>
          <el-row gutter="10" justify="center">
            <el-col :span="10"></el-col>
            <el-col :span="14">
              <span style="font-size: 28px;font-weight: bold;color: red;">{{ msgList[currentIndex].totalPrice }}</span>元
            </el-col>
          </el-row>
          <el-divider style="margin: 0;"/>
          <el-row>
            <el-col :span="24"><span>推荐支付方式：</span></el-col>
          </el-row>
          <el-row gutter="10" :justify="center">
            <el-col :span="24">
              <el-radio-group v-model="paidWay">
                <el-radio :value="1" border><el-icon color="green"><ChatRound /></el-icon>微信支付</el-radio>
                <el-radio :value="2" border><el-icon color="blue"><Connection /></el-icon>支付宝支付</el-radio>
                <el-radio :value="3" border><el-icon color="orange"><CreditCard /></el-icon>银行卡支付</el-radio>
              </el-radio-group>
            </el-col>
          </el-row>
        </div>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="cancel()">取消</el-button>
            <el-button type="primary" @click="pay()">
              确认支付
            </el-button>
          </div>
        </template>
      </el-dialog>
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
.col-span {
  font-size: 16px;
}
</style>