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
      <!-- <el-form-item label="电话">
        <el-input v-model="form.phone" />
      </el-form-item> -->
      <el-form-item label="密码">
        <el-input v-model="form.password" type="password" />
      </el-form-item>
      <el-form-item label="确认密码">
        <el-input v-model="form.password1" type="password" />
      </el-form-item>
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
import { reactive } from 'vue'
import { ElNotification } from 'element-plus'
import {getRegister} from '@/api/login'

// 路由：用于快速跳转
const router = useRouter()

const form = reactive({
  name: '',
  // phone: '',
  password: '',
  password1: '',
  role: '',
})

const register = reactive({
  name: '',
  password: '',
  role: '',
})

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
  }else{
    register.name = form.name
    register.password = form.password
    register.role = form.role
    const res = await getRegister(register)
    if (res.code === 1) {
      ElNotification({
        title: '注册成功',
        message: '您已经成功注册，请登录',
        type: 'success',
      })
      goToLogin()
    }else{
      ElNotification({
        title: '注册失败，请重试',
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
  // form.phone = ''
  form.password1 = ''
  form.password2 = ''
  form.role = ''
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