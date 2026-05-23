<template>
  <div class="login-container">
    <div class="page-title">零件交易系统</div>
    <el-form :model="form" label-width="auto" style="width: 300px" class="login-form">
      <div class="form-header">
        <h2>用户注册</h2>
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
      <el-form-item label="确认密码">
        <el-input v-model="form.password1" type="password" />
      </el-form-item>
      <!-- 验证码填写 -->
      <el-row :gutter="5">
        <el-col :span="12">
          <el-form-item label="验证码">
            <el-input v-model="form.code" placeholder="请输入验证码"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-button 
            @click="getCode" 
            :disabled="isCountingDown"
            :loading="isCountingDown">
            {{ isCountingDown ? `${countdown}s后重新获取` : '获取验证码' }}
          </el-button>
        </el-col>
      </el-row>
      <!-- 角色单选按钮 -->
      <el-form-item label="角色">
        <el-radio-group v-model="form.role" class="role-radio-group">
          <el-radio value="1" class="role-radio" border>供应商</el-radio>
          <el-radio value="2" class="role-radio" border>顾客</el-radio>
        </el-radio-group>
      </el-form-item>
      <!-- 表单按钮 -->
      <el-form-item>
        <el-button type="success" @click="onSubmit">注册</el-button>
        <el-button type="primary" @click="goToLogin">登录</el-button>
        <el-button type="info" @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { reactive, ref } from 'vue'
import { ElNotification } from 'element-plus'
import {getRegister,getValidCode} from '@/api/login'

// 路由：用于快速跳转
const router = useRouter()

const form = reactive({
  name: '',
  phone: '',
  password: '',
  password1: '',
  role: '',
  code: ''
})

const register = reactive({
  name: '',
  phone: '',
  password: '',
  role: '',
  code: ''
})

// 验证码和倒计时相关变量
const code = ref('')
const countdown = ref(60)
const isCountingDown = ref(false)
const notificationRef = ref(null) // 用于存储通知实例

const getCode = async () => {
  if (isCountingDown.value) return  // 如果正在倒计时则直接返回

  const res = await getValidCode()
  code.value = res.data
  startCountdown()  // 开始倒计时
}

// 倒计时函数
const startCountdown = () => {
  isCountingDown.value = true
  countdown.value = 60
  
  // 只显示一次弹窗，在倒计时开始时
  notificationRef.value = ElNotification({
    title: '验证码信息，请在有效期内填写',
    message: code.value+", 在五分钟内有效。",
    type: 'info',
    duration: 0, // 不自动关闭，直到用户手动关闭或倒计时结束
  })
  
  const timer = setInterval(() => {
    countdown.value--
    if (countdown.value <= 0) {
      clearInterval(timer)
      isCountingDown.value = false
      // 倒计时结束时关闭通知
      if (notificationRef.value) {
        notificationRef.value.close()
        notificationRef.value = null
      }
    }
  }, 1000)
}

const onSubmit = async () => {
  /* 向后端发送请求，根据返回的信息进行跳转
  （成功：回到登录页面；失败：提示错误信息）*/
  if (form.name === ''){
    ElNotification({
      title: '名字不能为空',
      message: '请正确输入您的名字进行注册',
      type: 'info',
    })
    return;
  }
  if (form.password !== form.password1) {
    ElNotification({
      title: '密码错误',
      message: '您两次输入的密码不一致，请检查您的密码',
      type: 'info',
    })
    return;
  } else {
    register.name = form.name
    register.phone = form.phone
    register.password = form.password
    register.role = form.role
    register.code = code.value  // 注意这里改为 code.value
    const res = await getRegister(register)
    if (res.code === 1) {
      ElNotification({
        title: '注册成功',
        message: '您已经成功注册，请登录',
        type: 'success',
      })
      goToLogin()
    } else {
      ElNotification({
        title: '注册失败，请检查相关信息并重试',
        type: 'error',
      })
      return;
    }
  }
}

const goToLogin = () => {
  // 跳转回登录页面进行登录
  router.push('/login')
}

const reset = () => {
  form.name = ''
  form.phone = ''
  form.password = ''
  form.password1 = ''
  form.role = ''
  form.code = ''
  countdown.value = 0  // 重置倒计时
  isCountingDown.value = false  // 重置倒计时状态
  // 重置时也关闭通知
  if (notificationRef.value) {
    notificationRef.value.close()
    notificationRef.value = null
  }
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
  margin-top: 40px;
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