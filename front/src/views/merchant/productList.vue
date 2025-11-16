<script setup>
import {ref,reactive,onMounted,nextTick} from 'vue'
import { Search } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'

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

//-----------------搜索框相关-------------------
// 下拉框数据
const color = ref('')
const colors = [
  {value: '1',label: '红色'},{value: '2',label: '黄色'},{value: '3',label: '绿色'},
  {value: '4',label: '蓝色'},{value: '5',label: '白色'},{value: '6',label: '黑色'},
]
const isPublish = ref('')
const status = [
  {value: '1',label: '上线'},{value: '0',label: '下线'},
]
//输入框数据
const partId = ref('')
const partName = ref('')
const weight = ref('')
// 时间选择器数据
const overTime = ref('')
//格式化时间数据
const startTime = ref('')
const endTime = ref('')
/*-------注意：时间选择器获得的数据是类Date数据即
（0: Sat Nov 01 2025 00:00:00 GMT+0800 (中国标准时间)），因此要转为标准格式：yyyy-MM-dd HH:mm:ss
方法：还原为Date数据再进行格式化即可
*/
// 格式化时间
const toFormatTime = (str) => { 
  const d = new Date(str)                      // 先转 Date
  const pad = n => n.toString().padStart(2, '0')
  return `${d.getFullYear()}-${pad(d.getMonth() + 1)}-${pad(d.getDate())}
   ${pad(d.getHours())}:${pad(d.getMinutes())}:${pad(d.getSeconds())}`
}
// 搜索
const search = () => { 
  startTime.value = toFormatTime(overTime.value[0])
  endTime.value = toFormatTime(overTime.value[1])
  //todo：发送参数到后端获取数据
}
//重置搜索框
const resetSearch = () => { 
  partId.value = ''
  partName.value = ''
  color.value = ''
  weight.value = ''
  isPublish.value = ''
  overTime.value = ''
}


//------------------表格相关--------------------
//表格数据
const tableData = [
  //todo：在钩子函数中进行获取数据
  {
    isPublish: 0,
    id: '001',
    name: 'Tom',
    color: '12312312321',
    weight: 'California',
    description:'描述信息',
    singlePrice:'111',
    inventory: '12312312321',
    createTime: '2025-10-10 09:00:00',
    updateTime: '2025-10-10 09:00:00',
  },
  {
    isPublish: 1,
    id: '001',
    name: 'Tom',
    color: '12312312321',
    weight: 'California',
    description:'描述信息',
    singlePrice:'111',
    inventory: '12312312321',
    createTime: '2025-10-10 09:00:00',
    updateTime: '2025-10-10 09:00:00',
  }
]

//编辑行数据
const editRow=(index) => { 
  resetDialog()
  //index为当前行索引
  dialogTitle.value = '编辑信息'
  dialogButtonText.value = '保存'
  dialogData.id = tableData[index].id
  dialogData.name = tableData[index].name
  dialogData.color = tableData[index].color
  dialogData.weight = tableData[index].weight
  dialogData.description = tableData[index].description
  dialogData.singlePrice = tableData[index].singlePrice
  dialogData.inventory = tableData[index].inventory
  dialogVisible.value = true
}

// 删除行数据
const deleteRow = (index) => { 
  //index为当前行索引
  ElMessageBox.confirm(
    '确定删除该信息吗？此操作不可恢复！',
    '删除',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
  // then: 确定按钮点击事件
    .then(() => {
      //todo：发送请求到后端删除该用户
      ElMessage({
        type: 'success',
        message: '删除成功',
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
//上线
const goOn = (index) => { 
  ElMessageBox.confirm(
    '确定上线该零件吗？',
    '操作',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'primary',
    }
  )
  // then: 确定按钮点击事件
    .then(() => {
      //todo：发送请求到后端删除该用户
      ElMessage({
        type: 'success',
        message: '上线成功',
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
//下线
const goOff = (index) => { 
  ElMessageBox.confirm(
    '确定下架该零件吗？',
    '操作',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'info',
    }
  )
  // then: 确定按钮点击事件
    .then(() => {
      //todo：发送请求到后端删除该用户
      ElMessage({
        type: 'success',
        message: '下架成功',
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

//-----------------对话框相关-------------------
//对话框显示标志
const dialogVisible = ref(false)
//对话框标题
const dialogTitle = ref('') //根据点击按钮进行设置
//对话框按钮显示文字
const dialogButtonText = ref('')  //根据点击按钮进行设置
//对话框校验规则（表单引用，用于后面清掉校验提示信息）
const dialogRules = ref(null)
//对话框对象
const dialogData = reactive({
  id: '',
  name: '',
  color: '',
  weight: '',
  description: '',
  singlePrice: '',
  inventory: '',
})

//对话框校验规则
const rules = {
  name: [
    { required: true, message: '请输入正确的名称', trigger: 'blur' },
    { min:1 ,max: 15, message: '最大长度只能为15', trigger: 'blur' },
  ],
  weight: [
    { required: true, message: '请输入零件重量（KG）', trigger: 'blur' },
    { min: 1,max: 5, message: '非法输入', trigger: 'blur' },
  ],
  singlePrice: [
    { required: true, message: '请输入零件单价（元）', trigger: 'blur' },
    { min: 1,max: 10, message: '非法输入', trigger: 'blur' },
  ],
  inventory: [
    { required: true, message: '请输入零件库存', trigger: 'blur' },
    { min: 1, max: 30, message: '非法输入', trigger: 'blur' },
  ],
}
//打开对话框（添加按钮事件）
const openAddDialog = () => {
  //重置对话框，防止旧数据影响新数据
  resetDialog()
  dialogTitle.value = '添加信息'
  dialogButtonText.value = '添加'
  dialogVisible.value = true
}
//对话框按钮点击事件：根据按钮文本内容执行保存或修改
const dialogButton = () => { 
  if (dialogButtonText.value === '添加') {
    //todo：发送请求到后端添加
  }
  if (dialogButtonText.value === '保存') {
    //todo：发送请求到后端修改信息
  }
}
// 重置对话框
const resetDialog = () => {
  dialogData.id = ''
  dialogData.name = ''
  dialogData.color = ''
  dialogData.weight = ''
  dialogData.description = ''
  dialogData.singlePrice = ''
  dialogData.inventory = ''
  // 清除表单校验状态
  nextTick(() => {
    if (dialogRules.value) {
      dialogRules.value.clearValidate()
    }
  })
}

//-----------------生命周期钩子函数----------------------
onMounted(() => { 
  //todo: ......
})
</script>

<template>
  <div class="common-layout">
    <el-container class="container">
      <!-- 头部：搜索相关组件 -->
      <el-header class="header">
        <el-row>
          <el-col :span="24"><span class="page-title">零件管理列表</span></el-col>
        </el-row>
        <el-row :gutter="10" justify="center">
          <el-col :span="24">
            <div class="search-container">
              <label class="search-label">ID：</label>
              <el-input
                v-model="partId"
                style="width: 50px;"
                placeholder="输入ID进行查找"
                :prefix-icon="Search"
                class="search-input"
              />
              <label class="search-label">名称：</label>
              <el-input
                v-model="partName"
                style="width: 50px;"
                placeholder="输入名称进行模糊查找"
                :prefix-icon="Search"
                class="search-input"
              />
              <label class="search-label">颜色：</label>
              <el-select
                v-model="color"
                clearable
                placeholder="请选择颜色"
                class="search-input"
              >
                <el-option
                  v-for="item in colors"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
              <label class="search-label">重量KG：</label>
              <el-input
                v-model="weight"
                style="width: 50px;"
                placeholder="输入重量进行查找"
                :prefix-icon="Search"
                class="search-input"
              />
            </div>
          </el-col>
        </el-row>
        <el-row gutter="10" justify="center">
          <el-col :span="24">
            <label class="search-label">创建时间：</label>
            <el-date-picker
              v-model="overTime"
              style="width: 520px;"
              type="datetimerange"
              start-placeholder="起始时间"
              end-placeholder="结束时间"
              format="YYYY-MM-DD HH:mm:ss"
              date-format="YYYY/MM/DD ddd"
              time-format="A hh:mm:ss"
            />
            <label class="search-label">状态：</label>
              <el-select
                v-model="isPublish"
                clearable
                placeholder="请选择状态"
                style="width:120px"
                class="search-input"
              >
                <el-option
                  v-for="item in status"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            <el-button class="search-button" type="primary" plain @click="search">搜索</el-button>
            <el-button class="search-button" type="info" plain @click="resetSearch">重置</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-divider content-position="left">零件交易系统</el-divider>
        </el-row>
      </el-header>
      <!-- 主体：表格+分页+按钮 -->
      <el-main class="main-content">
        <el-table :data="tableData" style="height: 90%">
          <el-table-column fixed="left" prop="isPublish" label="状态" width="120" align="center">
            <template #default="scope">
              <span :class="{ 'highlight-row': scope.row.isPublish === 0, 'highlight-rows': scope.row.isPublish === 1 }">
                {{ scope.row.isPublish === 0 ? '下线' : '上线' }}
              </span>
            </template>
          </el-table-column>
          <el-table-column prop="id" label="ID" width="120" align="center"/>
          <el-table-column prop="name" label="名称" width="120" align="center"/>
          <el-table-column prop="color" label="颜色" width="150" align="center"/>
          <el-table-column prop="weight" label="重量(KG)" width="150" align="center"/>
          <el-table-column prop="description" label="描述" width="200" align="center"/>
          <el-table-column prop="singlePrice" label="单价(元)" width="150" align="center"/>
          <el-table-column prop="inventory" label="库存(个)" width="150" align="center"/>
          <el-table-column prop="createTime" label="创建时间" width="180" align="center"/>
          <el-table-column prop="updateTime" label="修改时间" width="180" align="center"/>
          <el-table-column fixed="right" label="零件管理" min-width="100" align="center">
            <template #default="scope">
              <!-- 
                scope.$index 通常来自 Element UI 的表格组件（el-table），
                表示当前行的索引。-----可通过当前行索引获取数组里的对象，再获
                得该对象的属性值。
              -->
              <el-button
                type="success"
                size="default"
                @click="editRow(scope.$index)"
                round
              >
                编辑
              </el-button>
              <el-button
                type="danger"
                size="default"
                @click="deleteRow(scope.$index)"
                round
              >
                删除
              </el-button>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" min-width="100" align="center">
            <template #default="scope">
              <!-- 
                scope.$index 通常来自 Element UI 的表格组件（el-table），
                表示当前行的索引。-----可通过当前行索引获取数组里的对象，再获
                得该对象的属性值。
              -->
              <el-button
                type="primary"
                size="default"
                @click="goOn(scope.$index)"
                round
              >
                上线
              </el-button>
              <el-button
                type="info"
                size="default"
                @click="goOff(scope.$index)"
                round
              >
                下线
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
          <el-button class="add-btn" @click="openAddDialog">新增一条数据</el-button>
        </div>
      </el-main>
    </el-container>
  </div>
  <!-- 对话框 -->
  <el-dialog
    v-model="dialogVisible"
    :title="dialogTitle"
    width="500"
    align-center
  >
  <!-- 表单 -->
    <el-form
      ref="dialogRules"
      style="max-width: 600px"
      :model="dialogData"
      :rules="rules"
      label-width="auto"
    >
      <!-- ID -->
      <el-form-item label="ID" prop="id" >
        <el-input v-model="dialogData.id" disabled/>
      </el-form-item>
      <!-- 名称 -->
      <el-form-item label="名称" prop="name">
        <el-input v-model="dialogData.name" placeholder="请输入零件名称"/>
      </el-form-item>
      <!-- 颜色 -->
      <el-form-item label="颜色" prop="color">
        <el-select
                v-model="dialogData.color"
                clearable
                placeholder="请选择颜色"
                class="search-input"
              >
                <el-option
                  v-for="item in colors"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
      </el-form-item>
      <!-- 重量 -->
      <el-form-item label="重量" prop="weight">
        <el-input v-model="dialogData.weight" placeholder="请输入零件重量（单位KG）"/>
      </el-form-item>
      <!-- 单价 -->
      <el-form-item label="单价" prop="singlePrice">
        <el-input v-model="dialogData.singlePrice" placeholder="请输入零件单价（单位元）"/>
      </el-form-item>
      <!-- 库存 -->
      <el-form-item label="库存" prop="inventory">
        <el-input v-model="dialogData.inventory" placeholder="请输入零件数量（单位件）"/>
      </el-form-item>
      <!-- 描述 -->
      <el-form-item label="描述" prop="description">
        <el-input type="textarea" :rows="2" v-model="dialogData.description" placeholder="请输入零件描述信息"/>
      </el-form-item>
    </el-form>
    <!-- 表单按钮 -->
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="resetDialog">重置</el-button>
        <el-button type="primary" @click="dialogButton">
          {{dialogButtonText}}
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>
.highlight-row {
  color: red;
  font-weight: bold;
}
.highlight-rows {
  color: rgb(0, 162, 255);
  font-weight: bold;
}
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
  height: 30vh;
}

.main-content {
  flex: 1;
  padding: 10px;
  margin-top: 10px;
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