<script setup>
import { onMounted, ref, nextTick } from 'vue'

//会话id
const conversationId = ref('')
//基础路径
const baseURL = 'http://localhost:8080'
// 聊天数据
const list = ref([
  { role: 'assistant', content: '您好，我是小零，很高兴遇见你，有什么我可以帮到你吗？' },
])
// 输入框
const msg = ref('')

// 发送消息
const send = async () => {
  try{
    //将用户消息展示在聊天区域内
    const val = msg.value.trim()
    if (!val) return
    list.value.push({ role: 'user', content: val })
    msg.value = ''
    //这段代码的功能是：使用`nextTick`确保DOM更新完成后，获取`.chat-box`元素并将其滚动到底部，实现聊天窗口自动定位到最新消息的位置。
    nextTick(() => {
      const box = document.querySelector('.chat-box')
      box.scrollTop = box.scrollHeight
    })
  /* ---------- 关键：SSE 接收流 ---------- */
  // 拼 url（encode 中文）
  const url = `${baseURL}/chat?prompt=${val}&conversationId=${conversationId.value}`
  //实际发送异步请求到后端
  const evtSource = new EventSource(url)

  let assistantText = ''            // 累加器
  list.value.push({ role: 'assistant', content: '' }) //插入一个空的助手消息占位符

  //每收到一段数据就追加到 assistantText 中，并更新聊天列表中最后一条(助手)消息的内容。
  evtSource.onmessage = e => {
    assistantText += e.data         // 逐段拼
    // 更新最后一条 assistant 消息
    list.value[list.value.length - 1].content = assistantText
    nextTick(() => {
      document.querySelector('.chat-box').scrollTop = 99999
    })
  }
  evtSource.onerror = () => evtSource.close() // 结束自动关

  }catch(error){
    console.error(error)
  }
}


onMounted(() => {
  //刚进入页面时产生一个会话id
  conversationId.value=Math.floor(100000 + Math.random() * 900000).toString()
})
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="24">
            <h1 class="page-title">智能客服-小零🤖</h1>
            <el-divider content-position="left">零件交易系统</el-divider>
          </el-col>
        </el-row>
      </el-header>
      <el-main class="main">
        <!-- 聊天内容区 -->
        <div class="chat-box">
          <div
            v-for="(item, idx) in list"
            :key="idx"
            :class="['bubble', item.role === 'user' ? 'right' : 'left']"
          >
            <span class="text">{{ item.content }}</span>
          </div>
        </div>

        <!-- 底部输入区 -->
        <div class="footer">
          <textarea v-model="msg" placeholder="请输入内容" rows="1"></textarea>
          <button @click="send">发送</button>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
.page-title {
  font-size: 28px;
  font-weight: bold;
  color: rgb(147, 71, 255);
}

.main {
  display: flex;
  flex-direction: column;
  height: 80vh;
  margin-top: 20px;
  padding: 20px;
}

/* 聊天内容框 */
.chat-box {
  flex: 1;
  overflow-y: auto;
  padding: 20px;
  background: rgba(255, 255, 255, 0.7);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  box-shadow: 0 8px 32px rgba(31, 38, 135, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.3);
  margin-bottom: 10px;
}

/* 底部输入区保持不变 */
.footer {
  display: flex;
  align-items: flex-end;
  padding: 15px;
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.7);
  backdrop-filter: blur(10px);
  box-shadow: 0 8px 32px rgba(31, 38, 135, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.3);
}

.footer textarea {
  flex: 1;
  resize: none;
  padding: 12px 16px;
  font-size: 14px;
  border: 1px solid rgba(102, 126, 234, 0.2);
  border-radius: 15px;
  background: rgba(255, 255, 255, 0.8);
  transition: all 0.3s ease;
}

.footer textarea:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.1), 0 0 0 3px rgba(102, 126, 234, 0.1);
}

.footer button {
  margin-left: 15px;
  padding: 12px 24px;
  cursor: pointer;
  border: none;
  border-radius: 15px;
  background: linear-gradient(135deg, #1a44ff 0%, #8e1cff 100%);
  color: white;
  font-weight: 600;
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
  transition: all 0.3s ease;
}

.footer button:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(102, 126, 234, 0.4);
}
/* 聊天气泡 */
.bubble {
  display: flex;
  align-items: flex-end;
  margin-bottom: 15px;
}

.bubble.left {
  justify-content: flex-start;
}

.bubble.right {
  justify-content: flex-end;
}

.text {
  max-width: 60%;
  padding: 12px 16px;
  border-radius: 18px;
  background: rgba(255, 255, 255, 0.9);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
  word-wrap: break-word;
  position: relative;
}

.bubble.left .text {
  background: rgba(255, 255, 255, 0.9);
  border-bottom-left-radius: 5px;
}

.bubble.right .text {
  background: linear-gradient(135deg, #4063ff 0%, #9328ff 100%);
  color: white;
  border-bottom-right-radius: 5px;
}
</style>