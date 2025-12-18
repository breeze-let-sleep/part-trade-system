<script setup>
import { ref, reactive,onMounted } from 'vue'
import { Search } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import {likeCollects,removeCollect,getCollects} from '@/api/collects'
import { useUserStore } from '@/store/user'


const userStore = useUserStore()
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
  const res=await getCollects(page.currentPage,page.pageSize)
  page.total = res.data.total
  tableData.value=res.data.rows
}

//-----------------搜索框相关-------------------
//输入框数据
const inputMerchant = ref('')
// 搜索
const search = async() => {
  const res = await likeCollects(inputMerchant.value,userStore.id)
  page.total = res.data.total
  tableData.value=res.data.rows
}
//重置搜索
const resetSearch = () => {
  inputMerchant.value = ''
}
//-----------------表格相关-------------------
// 数据源
const tableData = ref()

const deleteRow = (index) => {
  ElMessageBox.confirm(
    '此操作将会取消该供应商的收藏，是否继续',
    '注意',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(async() => {
      const res = await removeCollect(userStore.id,tableData.value[index].merchantId)
      if (res.code === 1) {
        ElMessage({
          type: 'success',
          message: '操作成功',
        })
        const res=await getCollects(page.currentPage,page.pageSize)
        page.total = res.data.total
        tableData.value=res.data.rows
        ws
      }else{
        ElMessage({
          type: 'error',
          message: '操作失败，请重试',
        })
      }
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '已取消',
      })
    })
}

onMounted(async() => {
  const res=await getCollects(page.currentPage,page.pageSize)
  page.total = res.data.total
  tableData.value=res.data.rows
})
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header">
        <el-row>
          <el-col :span="24"><span class="page-title">收藏的供应商</span></el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12"></el-col>
          <el-col :span="12">
            <div class="search-container">
              <label class="search-label">搜索：</label>
              <el-input
                v-model="inputMerchant"
                style="width: 50px;"
                placeholder="输入供应商名称进行查找"
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
        <el-table :data="tableData" style="height: 90%">
          <el-table-column prop="avatar" label="头像" width="150" align="center">
            <template #default="scope">
              <el-image 
                style="width: 80px; height: 80px; border-radius: 50%;" 
                :src="scope.row.avatar" 
                fit="cover"
                :preview-src-list="[scope.row.avatar]"
                preview-teleported
              />
            </template>
          </el-table-column>
          <el-table-column prop="merchantId" label="供应商编号" width="120" align="center"/>
          <el-table-column prop="name" label="供应商姓名" width="120" align="center"/>
          <el-table-column prop="phone" label="供应商电话" width="120" align="center"/>
          <el-table-column prop="address" label="供应商地址" width="150" align="center"/>
          <el-table-column prop="description" label="供应商简介" width="400" align="center"/>
          <el-table-column prop="createTime" label="创建时间" width="180" align="center"/>
          <el-table-column fixed="right" label="操作" min-width="100" align="center">
            <template #default="scope">
              <!-- 
                scope.$index 通常来自 Element UI 的表格组件（el-table），
                表示当前行的索引。-----可通过当前行索引获取数组里的对象，再获
                得该对象的属性值。
              -->
              <el-button
                type="danger"
                size="default"
                @click="deleteRow(scope.$index)"
                round
              >
                取消收藏
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
</template>

<style scoped>
.main {
  padding: 20px;
  margin-top: 18px;
  height: 70vh;
}
.page-title {
  font-size: 28px;
  font-weight: bold;
  color: rgb(147, 71, 255);
}
.highlight-row {
  color: red;
  font-weight: bold;
}
.header {
  flex: 0 0 auto;
  padding: 10px 0;
  height: 20vh;
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