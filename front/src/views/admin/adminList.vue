<script setup>
import {ref,reactive,onMounted,nextTick} from 'vue'
import { Search } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox,ElNotification } from 'element-plus'
import {updateInfo,deleteAdmin,getPageList,addAdmin,LikeSearch} from '@/api/admin'

const token = ref('')
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

//-----------------搜索框相关-------------------
//输入框数据
const input = ref('')
// 时间选择器数据
const overTime = ref(null)
//格式化时间数据
const startTime = ref()
const endTime = ref()
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
  if (overTime.value === null) {
    startTime.value = null
    endTime.value = null
  }else{
    startTime.value = toFormatTime(overTime.value[0])
    endTime.value = toFormatTime(overTime.value[1])
  }
  //-----------------有值传值，无值不传（Axios 的params参数会自动忽略undefined）
  const res=await LikeSearch(input.value,startTime.value,endTime.value)
  page.total = res.data.total
  tableData.value=res.data.rows
}
//重置搜索框
const resetSearch = () => { 
  console.log(overTime.value)
  input.value = ''
  overTime.value = ''
}


//------------------表格相关--------------------
//表格数据
const tableData = ref([])

//编辑行数据
const editRow=(index) => { 
  //index为当前行索引
  dialogTitle.value = '编辑信息'
  dialogButtonText.value = '保存'
  dialogData.id = tableData.value[index].id
  dialogData.name = tableData.value[index].name
  dialogData.phone = tableData.value[index].phone
  dialogData.avatar = tableData.value[index].avatar
  dialogData.password = tableData.value[index].password
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
    .then(async() => {
      const res = await deleteAdmin(tableData.value[index].id)
      handleCurrentChange(page.currentPage)
      if (res.code === 1) {
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
      }else {
        ElMessage({
          type: 'error',
          message: '删除失败，请重试',
        })
      }
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
let dialogVisible = ref(false)
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
  phone: '',
  password: '',
  avatar: '',
})

//对话框校验规则
const rules = {
  name: [
    { required: true, message: '请输入正确的用户名', trigger: 'blur' },
    { min:1 ,max: 15, message: '最大长度只能为15', trigger: 'blur' },
  ],
  phone: [
    { required: true, message: '请输入正确的电话号码', trigger: 'blur' },
    { min: 11,max: 11, message: '请输入11位号码', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入正确的密码', trigger: 'blur' },
    { min: 3, max: 18, message: '长度最少需要三位', trigger: 'blur' },
  ],
}
//打开添加管理员对话框（添加管理员按钮事件）
const openAddDialog = () => {
  //重置对话框，防止旧数据影响新数据
  resetDialog()
  dialogTitle.value = '添加信息'
  dialogButtonText.value = '添加'
  dialogVisible.value = true
}
//对话框按钮点击事件：根据按钮文本内容执行保存或修改
const dialogButton =async () => { 
  let res = '';
  if (dialogButtonText.value === '添加') {
    res = await addAdmin(dialogData)
  }
  if (dialogButtonText.value === '保存') {
    console.log(dialogData)
    res = await updateInfo(dialogData)
  }
  if (res.code === 1) {
    ElNotification({
      title: '操作成功',
      message: '数据已经正确更新',
      type: 'success',
    })
    dialogVisible.value = false
    handleCurrentChange(page.currentPage)
  }else {
    ElNotification({
      title: '操作失败',
      message: '数据更新失败，请重试',
      type: 'error',
    })
  }
}
// 重置对话框
const resetDialog = () => {
  dialogData.id = ''
  dialogData.name = ''
  dialogData.phone = ''
  dialogData.password = ''
  dialogData.avatar = ''
  // 清除表单校验状态
  nextTick(() => {
    if (dialogRules.value) {
      dialogRules.value.clearValidate()
    }
  })
}
//---------------------对话框图像
const handleAvatarSuccess=(response) => { 
  dialogData.avatar = response.data
}
// 上传前判断文件格式和大小
const beforeAvatarUpload=(file) => { 
  const isJPG = file.type === 'image/jpeg' || file.type === 'image/png'
  const isLt2M = file.size / 1024 / 1024 < 2
  if (!isJPG) {
    ElMessage.error('头像图片只能是 JPG/PNG 格式!')
    return false
  }
  if (!isLt2M) {
    ElMessage.error('头像图片大小不能超过 2MB!')
    return false
  }
}

//-----------------生命周期钩子函数----------------------
onMounted(async() => { 
  token.value = localStorage.getItem('jwt')
  const res = await getPageList(page.currentPage, page.pageSize)
  page.total = res.data.total
  tableData.value = res.data.rows
})
</script>

<template>
  <div class="common-layout">
    <el-container class="container">
      <!-- 头部：搜索相关组件 -->
      <el-header class="header">
        <el-row>
          <el-col :span="24"><span class="page-title">管理员列表</span></el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="24">
            <div class="search-container">
              <label class="search-label">搜索：</label>
              <el-input
                v-model="input"
                style="width: 50px;"
                placeholder="输入姓名进行查找"
                :prefix-icon="Search"
                class="search-input"
              />
              <label class="search-label">创建时间范围：</label>
              <el-date-picker
                v-model="overTime"
                style="width: 200px;"
                type="datetimerange"
                start-placeholder="起始时间"
                end-placeholder="结束时间"
                format="YYYY-MM-DD HH:mm:ss"
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
      <el-main class="main-content">
        <el-table :data="tableData" style="height: 90%">
          <el-table-column prop="img" label="头像" width="150" align="center">
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
          <el-table-column prop="id" label="ID" width="120" align="center"/>
          <el-table-column prop="name" label="姓名" width="120" align="center"/>
          <el-table-column prop="phone" label="电话" width="150" align="center"/>
          <el-table-column prop="createTime" label="创建时间" width="180" align="center"/>
          <el-table-column prop="updateTime" label="修改时间" width="180" align="center"/>
          <el-table-column fixed="right" label="操作" min-width="200" align="center">
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
      <el-form-item label="头像" prop="avatar">
        <!-- 头像上传：action为上传接口地址；on-success为当片上传成功后的回调函数；before-upload为上传前的回调函数 -->
        <el-upload
          class="avatar-uploader"
          action="/api/upload"
          :headers="{'token':token}"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
        <!-- 头像图片 -->
          <img v-if="dialogData.avatar" :src="dialogData.avatar" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <!-- ID -->
      <el-form-item label="ID" prop="id" >
        <el-input v-model="dialogData.id" disabled/>
      </el-form-item>
      <!-- 姓名 -->
      <el-form-item label="姓名" prop="name">
        <el-input v-model="dialogData.name" />
      </el-form-item>
      <!-- 电话 -->
      <el-form-item label="电话" prop="phone">
        <el-input v-model="dialogData.phone" />
      </el-form-item>
      <!-- 密码 -->
      <el-form-item label="密码" prop="password">
        <el-input v-model="dialogData.password" />
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