<script setup>
import {ref,reactive,onMounted} from 'vue'
import { Search,Pointer } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox,ElNotification } from 'element-plus'

//-----------------搜索框相关---------------------
//供应商搜索
const inputMerchant = ref('')
//零件搜索
const inputPart = ref('')

//搜索函数
const search = () => {
  //todo：发送查询请求到后端获取数据
}
//重置搜索框
const resetSearch = () => {
  inputMerchant.value = ''
  inputPart.value = ''
}


//----------------------展示区相关--------------------
//颜色
const colors = ref(['红色', '黄色', '绿色', '蓝色', '白色', '黑色'])
//购买零件的对象（传递到后端）
const buyPart = ref({
  partId: 0,
  merchantId: 0,
  customerId: 0,  //通过pinia获得
  amount: 0,
})
//购买的函数
const buy = (item) => { 
  //item获得的是list中的一个对象（与零件相关）
  //对buyPart对象进行赋值
  ElMessageBox.confirm(
    '确定购买该零件商品吗？',
    '购买零件',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'primary',
    }
  )
  // then: 确定按钮点击事件
    .then(() => {
      //todo：发送请求到后端，将数据传递给后端
      ElMessage({
        type: 'success',
        message: '购买成功，您有新的订单合同需要核对签字',
      })
    })
  // catch: 取消按钮点击事件
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '已取消',
      })
    }
  )
}
//假设为后端获取到的数据
const newDatas = ref([
  {
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },{
    partId: 1,
    name: '零件1',
    price: 10,
    merchantId: 1,
    merchant: '供应商1',
    color: 1,
    weight: 1,
    description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
    amount: 0, //将要购买数量
  },
]);

//商品数据部分
const partData = ref({
  list: [
    //返回的数据格式
    {
      partId: 1,
      name: '零件1',
      singlePrice: 10,
      merchantId: 1,
      merchant: '供应商1',
      color: 1,
      weight: 1,
      description: '这是零件1的描述哇哈哈哈哈哈哈啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈',
      amount: 0, //将要购买数量
    },
  ],
  loading: false, //用于显示加载中
  hasMore: true,  //是否还有更多数据可加载与没有更多数据的显示
  pageSize: 20    //每次响应回来的数量
})
//滚动函数
const handleScroll = (event) => {
  const container = event.target;
  const { scrollTop, scrollHeight, clientHeight } = container;
  
  // 距离底部 10px 时触发加载
  if (scrollTop + clientHeight >= scrollHeight - 10) {
    loadMore();
  }
}
//加载更多函数
const loadMore = async () => {
  if (partData.value.loading) return;
  
  partData.value.loading = true;
  try {
    // 获取游标：最后一条数据的 id
    const cursor = partData.value.list.length > 0 ? partData.value.list[partData.value.list.length - 1].id : 0;
    
    //todo：请求后端获取size大小的数据给到newData
    const newData = newDatas.value;
    
    if (newData.length < partData.value.pageSize) {
      partData.value.hasMore = false; // 数据不足，说明已加载完
    }
    
    partData.value.list = [...partData.value.list, ...newData];
  } catch (error) {
    console.error('加载失败:', error);
  } finally {
    partData.value.loading = false;
  }
}

/* 当出现两个按钮（取消和确认时），可在父组件内设置事件@confirm，只有点击确定时才会执行该操作 */
//当点击确认按钮时会执行该操作
const collectMerchant = (id) => {
  //todo：发送请求到后端收藏该供应商（从pinia获取用户id+当前参数供应商id）
  ElNotification({
    title: '操作成功',
    message: '收藏供应商成功，可前往收藏列表查看',
    type: 'success',
  })
}

//-----------------------生命周期钩子函数----------------------
onMounted(() => {
  // 初始化时加载数据
  loadMore();
})
</script>

<template>
  <!-- 商品列表：展示现有的所有零件供用户下单（支持同供应商名字和零件名字进行查找） -->
  <div class="common-layout">
    <el-container class="container">
      <!-- 头部：搜索相关组件 -->
      <el-header class="header">
        <el-row>
          <el-col :span="24"><span class="page-title">订单流程管理</span></el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="24">
            <div class="search-container">
              <label class="search-label">供应商名称：</label>
              <el-input
                v-model="inputMerchant"
                style="width: 50px;"
                placeholder="输入供应商名称进行查找"
                :prefix-icon="Search"
                class="search-input"
              />
              <label class="search-label">零件名称：</label>
              <el-input
                v-model="inputPart"
                style="width: 50px;"
                placeholder="输入零件名称进行查找"
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
      <!-- 主体：表格+分页+按钮 -->
      <el-main class="main-content" @scroll="handleScroll">
        <el-space :size="30" wrap style="overflow-y: auto;">
          <el-card v-for="item in partData.list" :key="item.id" class="box-card" style="width: 300px;height: 250px; display: flex; flex-direction: column;">
            <!-- partData.list 中的每个 item 是一个对象，即一行数据 -->
            <div style="flex: 1; display: flex; flex-direction: column;">
              <el-row>
                <el-col :span="12">
                  <label style="font-size: 28px;font-weight: bold;">{{ item.name }}</label>
                </el-col>
                <el-col :span="12">
                  <span style="color: blue;">总价：{{ item.singlePrice*item.amount }}元</span>
                </el-col>
              </el-row>
              <el-row gutter="10">
                <el-col :span="12">
                  <label style="font-weight: bold;">供应商: </label>
                  <el-popconfirm title="你想要收藏该供应商吗？" @confirm="collectMerchant(item.merchantId)">
                    <template #reference>
                      <span style="color: rgb(147, 71, 255);">{{ item.merchant }}</span>
                    </template>
                    <template #actions="{ confirm, cancel }">
                      <el-button size="small" @click="cancel">取消</el-button>
                      <el-button
                        type="primary"
                        size="small"
                        @click="confirm"
                      >
                        确定
                      </el-button>
                    </template>
                  </el-popconfirm>
                </el-col>
                <el-col :span="12">
                  <label style="font-weight: bold;">单价: </label><span style="color: red;"> {{ item.singlePrice }}元</span>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="5">
                  <label style="font-weight: bold;">外观：</label>
                </el-col>
                <el-col :span="9">
                  <span>颜色: {{ colors[item.color-1] }}</span>
                </el-col>
                <el-col :span="10">
                  <span>重量: {{ item.weight }}KG</span>
                </el-col>
              </el-row>
              <el-row style="flex: 1;">
                <el-col :span="5">
                  <label style="font-weight: bold;">描述：</label>
                </el-col>
                <el-col :span="19">
                  <p style="height: 70px; overflow: hidden; text-overflow: ellipsis; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical;">{{ item.description }}</p>
                </el-col>
              </el-row>
            </div>
            <el-row gutter="10">
              <el-col :span="5"></el-col>
              <el-col :span="12">
                <el-input-number v-model="item.amount" :min="0" size="small" @change="handleChange" />
              </el-col>
              <el-col :span="7">
                <el-button type="primary" :round="true" :icon="Pointer" @click="buy(item)">购买</el-button>
              </el-col>
            </el-row>
          </el-card>
          <!-- 提示信息 -->
          <div v-if="partData.loading" class="loading">加载中...</div>
          <div v-if="!partData.hasMore" class="no-more">没有更多了</div>
        </el-space>
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
</style>
