<template>
  <div class="login-container">
    <div class="page-title">零件交易系统</div>
    <el-form :model="form" label-width="auto" style="width: 300px" class="login-form">
      <div class="form-header">
        <h2>用户登录</h2>
      </div>
      <!-- 基本信息 -->
      <el-form-item label="姓名">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" type="password" />
      </el-form-item>
      <!-- 角色单选按钮 -->
      <el-form-item label="角色">
        <el-radio-group v-model="form.role" class="role-radio-group">
          <el-radio value="admin" class="role-radio" border>管理员</el-radio>
          <el-radio value="merchant" class="role-radio" border>商家</el-radio>
          <el-radio value="customer" class="role-radio" border>顾客</el-radio>
        </el-radio-group>
      </el-form-item>
      <!-- 表单按钮 -->
      <el-form-item>
        <el-button type="success" @click="doLogin">登录</el-button>
        <el-button type="primary" @click="goToRegister">注册</el-button>
        <el-button type="info" @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref,reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/user'

// 路由：用于快速跳转
const router = useRouter()
// 表单数据
const form = reactive({
  name: '',
  phone: '',
  password: '',
  role: '',
})

// 跳转到注册页面
const goToRegister = () => {
  router.push('/register')
}

// 重置表单
const reset = () => {
  form.name = ''
  form.phone = ''
  form.password = ''
  form.role = ''
}

// 用户仓库
const userStore = useUserStore()

// 登录
const doLogin = async () => {
  //------------调用后端获得jwt
  const jwt="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjowLCJuYW1lIjoicm9vdCIsImlkIjoxLCJleHAiOjE3NTk5MTA1Mzl9.Dyv7cYA6JpL52WnJNRM8jXGLgwGj0AryzV0GzpSkAtI"
  localStorage.setItem('jwt', jwt)        // 先存储jwt
  await userStore.login(jwt)              // 再装路由
  router.replace('/home')
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, rgba(106, 17, 203, 0.7) 0%, rgba(37, 117, 252, 0.7) 100%);
  position: relative;
}

.page-title {
  position: absolute;
  top: 10%;
  left: 50%;
  transform: translateX(-50%);
  font-size: 42px;
  font-weight: bold;
  color: white;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.login-form {
  border: 2px solid #409eff;
  border-radius: 12px;
  padding: 30px;
  background-color: rgba(255, 255, 255, 0.9);
  box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
  backdrop-filter: blur(4px);
  -webkit-backdrop-filter: blur(4px);
}

.form-header {
  text-align: center;
  margin-bottom: 20px;
}

.form-header h2 {
  color: #409eff;
  margin: 0;
  font-size: 28px;
  text-shadow: 1px 1px 2px rgba(0,0,0,0.1);
}

.role-radio-group {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 10px;
}

.role-radio {
  flex: 1;
  text-align: center;
  min-width: 80px;
  margin: 0 !important;
  border-radius: 8px !important;
  transition: all 0.3s ease;
}

:deep(.role-radio .el-radio__input) {
  display: none;
}

:deep(.role-radio .el-radio__label) {
  padding-left: 0;
  font-weight: 500;
}

:deep(.el-radio__inner) {
  border-color: #409eff;
}

:deep(.el-radio__inner::after) {
  background-color: #409eff;
}
</style>