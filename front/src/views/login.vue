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
      <!-- <el-form-item label="电话">
        <el-input v-model="form.phone" />
      </el-form-item> -->
      <el-form-item label="密码">
        <el-input v-model="form.password" type="password" />
      </el-form-item>
      <!-- 角色单选按钮 -->
      <el-form-item label="角色">
        <el-radio-group v-model="form.role" class="role-radio-group">
          <el-radio value="0" class="role-radio" border>管理员</el-radio>
          <el-radio value="1" class="role-radio" border>供应商</el-radio>
          <el-radio value="2" class="role-radio" border>顾客</el-radio>
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
import { ElNotification } from 'element-plus'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/user'
import { getLogin } from '@/api/login'
// 当前用户信息
const userStore = useUserStore()
// 路由：用于快速跳转
const router = useRouter()
// 表单数据
const form = reactive({
  name: '',
  // phone: '',
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
  // form.phone = ''
  form.password = ''
  form.role = ''
}



// 登录
const doLogin = async () => {
  //todo: 进行登录操作，成功则下发jwt令牌
  const res = await getLogin(form)
  if (res.code === 1) {
    /* //jwt1:admin;jwt2:merchant;jwt3:customer;jwt4:root
    const jwt1="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjowLCJuYW1lIjoi5bCP5piOIiwiaWQiOjEsImV4cCI6MTc2MDc3NDM2MH0.ctDQg9Inl0wtToWGG2LH_TDhPQPfUGDIEHfFxPrchgs"
    const jwt2="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoxLCJuYW1lIjoi5ZOH5ZOI5ZOIIiwiaWQiOjEsImV4cCI6MTc1OTkyNjY4NX0.EW-gkWrqtl4LclYZugy_QkfhQlgKO4Y5oHDKZLxm-2c"
    const jwt3="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoyLCJuYW1lIjoi5bCP5piOIiwiaWQiOjEsImV4cCI6MTc1OTkyNjc5NH0.kG4lGKEigaKkBmlNWYp0WRBTkvXh4By7fP8o3YIbTyM"
    const jwt4="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjowLCJuYW1lIjoicm9vdCIsImlkIjoxLCJleHAiOjE3NjA3NzQ1MzJ9.JzwHtRciNwQpbcvIdvBRCCbqp6tIEzA8wR82ncq0L_o"
    // 先存储jwt
    localStorage.setItem('jwt', jwt4)    
    // 安装对应角色路由，设置登录的用户的基本信息
    await userStore.setUserInfo(jwt4)               */
    const jwt=res.data
    // 先存储jwt
    localStorage.setItem('jwt', jwt)
    // 安装对应角色路由，设置登录的用户的基本信息
    await userStore.setUserInfo(jwt)
    router.replace('/home')
    ElNotification({
      title: '登录成功',
      message: '欢迎您进入零件交易系统',
      type: 'success',
    })
  }else {
    ElNotification({
      title: '登录失败',
      message: res.msg,
      type: 'error',
    })
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, rgba(105, 3, 214, 0.7) 20%, rgba(5, 89, 235, 0.7) 100%);
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
  border: 2px solid rgb(147, 71, 255);
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
  color: rgb(147, 71, 255);
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
  border-color: rgb(147, 71, 255);
}

:deep(.el-radio__inner::after) {
  background-color: rgb(147, 71, 255);
}
</style>