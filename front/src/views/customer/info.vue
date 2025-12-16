<script setup>
import { ref, reactive, onMounted } from 'vue'
//导入element-plus的提示
import { ElCard, ElRow, ElCol, ElInput, ElButton, ElMessage, ElUpload, ElImage, ElMessageBox,ElNotification } from 'element-plus'
import { useUserStore } from '@/store/user'
import {getInfoById,updateInfo,deleteCustomer} from '@/api/customer'

const userStore = useUserStore()
const token = ref('')
//个人信息对象
const form = reactive({
  id: userStore.id,
  name: '',
  phone: '',
  password: '',
  address: '',
  avatar: ''
})

//--------------------头像上传----------------------
const handleAvatarSuccess = (response,file) => {
  form.avatar = response.data
}

//上传文件格式与大小检查
const beforeAvatarUpload = (file) => { 
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
//--------------------按钮函数----------------------
//是否已经点击了修改按钮（只有点击了修改按钮后才能保存）
const hasAlter = ref(false)
//修改
const alter = () => { 
  hasAlter.value = true
}
//保存
const save =async () => { 
  const res=await updateInfo(form)
  if(res.code===1){
    hasAlter.value = false
    ElNotification({
      title: '保存成功',
      message: '您的信息已成功修改',
      type: 'success',
    })
    return
  }
}
//注销
const remove = () => { 
  ElMessageBox.confirm(
    '确定注销账户吗？此操作不可恢复！',
    '删除',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
  // then: 确定按钮点击事件
    .then(async() => {
      const res=await deleteCustomer(form.id)
      if(res.code===1){
        userStore.logout()
        ElMessage({
          type: 'success',
          message: '注销成功',
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

//----------------------生命周期钩子函数----------------------
onMounted(async() => { 
  token.value = localStorage.getItem('jwt')
  const res=await getInfoById(form.id)
  form.name = res.data.name
  form.phone = res.data.phone
  form.address = res.data.address
  form.avatar = res.data.avatar
})
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <span class="page-title">个人信息管理</span>
        <el-divider content-position="left">零件交易系统</el-divider>
      </el-header>
      <el-main class="container"> 
        <!-- 头像上传区域 -->
        <el-row class="avatar-section">
          <el-col :span="24">
            <el-upload
              class="avatar-uploader"
              action="/api/upload"
              :headers="{'token':token}"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="form.avatar" :src="form.avatar" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
            </el-upload>
            <p class="avatar-tip">点击上传头像（支持JPG/PNG格式，大小不超过2MB）</p>
          </el-col>
        </el-row>
        <!-- 基本信息表单 -->
        <el-card class="info-card">
          <template #header>
            <div class="card-header">
              <span>基本信息</span>
            </div>
          </template>
          <el-row :gutter="20" class="form-row">
            <el-col :span="3" class="label-col">ID：</el-col>
            <el-col :span="9">
              <el-input v-model="form.id" class="form-input" disabled />
            </el-col>
            <el-col :span="3" class="label-col">姓名：</el-col>
            <el-col :span="9">
              <el-input v-model="form.name" class="form-input"/>
            </el-col>
          </el-row>
          <el-row :gutter="20" class="form-row">
            <el-col :span="3" class="label-col">电话：</el-col>
            <el-col :span="9">
              <el-input v-model="form.phone" class="form-input" />
            </el-col>
            <el-col :span="3" class="label-col">密码：</el-col>
            <el-col :span="9">
              <el-input :disabled="!hasAlter" v-model="form.password" type="password" class="form-input" />
            </el-col>
          </el-row>
          <el-row :gutter="20" class="form-row">
            <el-col :span="3" class="label-col">地址：</el-col>
            <el-col :span="21">
              <el-input v-model="form.address" class="form-input" />
            </el-col>
          </el-row>
        </el-card>
        <!-- 操作按钮区域 -->
        <el-row class="button-row">
          <el-col :span="24">
            <el-button type="primary" class="action-button" @click="alter">修改</el-button>
            <el-tooltip
              class="box-item"
              effect="dark"
              content="只有点击了修改按钮后才能保存"
              placement="top"
            >
              <el-button type="success" class="action-button" :disabled="!hasAlter" @click="save">保存</el-button>
            </el-tooltip>
            <el-button type="danger" class="action-button" :disabled="form.name==='root'?true:false" @click="remove">注销</el-button>
          </el-col>
        </el-row>
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
/* 容器样式 */
.container {
  margin-top: 30px;
  padding: 20px;
  min-height: 60vh;
  border-radius: 15px;
}

/* 头像区域样式 */
.avatar-section {
  /* text-align: right; */
  margin-top: 10px;
  margin-bottom: 30px;
  margin-left: 180px;
}

/* 头像上传组件样式 */
.avatar-uploader .avatar {
  width: 150px;
  height: 150px;
  display: block;
  margin: 0 auto 10px;
  border-radius: 50%;
  border: 3px solid #7940ff;
  box-shadow: 0 4px 12px rgba(118, 64, 255, 0.3);
}

/* 头像上传区域样式 */
.avatar-uploader .el-upload {
  border: 2px dashed #d9d9d9;
  border-radius: 50%;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
  width: 150px;
  height: 150px;
  margin: 0 auto;
}

/* 头像上传区域悬停效果 */
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
  transform: scale(1.05);
  transition: all 0.3s ease;
}

/* 头像上传图标样式 */
.el-icon.avatar-uploader-icon {
  font-size: 40px;
  color: #8c939d;
  width: 150px;
  height: 150px;
  text-align: center;
  line-height: 150px;
}

/* 头像提示文字样式 */
.avatar-tip {
  color: #909399;
  font-size: 14px;
  margin-top: 10px;
}

/* 信息卡片样式 */
.info-card {
  margin-bottom: 30px;
  border-radius: 12px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  background: white;
}

/* 卡片头部样式 */
.card-header {
  font-weight: bold;
  color: #6640ff;
  font-size: 18px;
}

/* 表单行样式 */
.form-row {
  margin-bottom: 25px;
  align-items: center;
}

/* 标签列样式 */
.label-col {
  text-align: right;
  font-weight: 500;
  color: #606266;
  font-size: 15px;
}

/* 表单输入框样式 */
.form-input {
  width: 100%;
}

/* 按钮行样式 */
.button-row {
  text-align: center;
  margin-top: 20px;
}

/* 操作按钮样式 */
.action-button {
  margin: 0 15px;
  padding: 12px 30px;
  font-size: 16px;
  transition: all 0.3s ease;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

/* 操作按钮悬停效果 */
.action-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
}

/* 主要按钮悬停效果 */
.action-button:first-child:hover {
  background-color: #66b1ff;
}

/* 成功按钮悬停效果 */
.action-button:nth-child(2):hover {
  background-color: #85ce61;
}

/* 危险按钮悬停效果 */
.action-button:last-child:hover {
  background-color: #f78989;
}
</style>