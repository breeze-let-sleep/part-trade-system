<script setup>
import { onMounted, ref, nextTick, onUnmounted } from 'vue'
import { useUserStore } from '@/store/user'
import { marked } from 'marked'


const userStore = useUserStore()
const conversationId = ref('')
const baseURL = 'http://localhost:8080'
const list = ref([
  { role: 'assistant', content: '您好，24h不间断客服-小零✌️为您提供服务😘' },
])
const msg = ref('')
const isLoading = ref(false)
let abortController = null // 使用全局变量管理 AbortController

// 配置 marked 选项
marked.setOptions({
  breaks: true, // 支持 GitHub 风格的换行
  gfm: true, // 支持 GitHub Flavored Markdown
})

// 创建新的流式连接
const createStreamConnection = (url) => {
  const token = localStorage.getItem('jwt')
  console.log(`jwt: ${token}`)
  // 先取消已存在的请求
  if (abortController) {
    abortController.abort()
  }
  
  abortController = new AbortController()
  
  const fetchData = async () => {
    try {
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          'Accept': 'text/html;charset=utf-8',
          'Cache-Control': 'no-cache',
          'token': token,
        },
        signal: abortController.signal
      })

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`)
      }

      // 获取响应体作为 ReadableStream
      const reader = response.body.getReader()
      const decoder = new TextDecoder()
      let buffer = ''
      let assistantText = ''
      const assistantIndex = list.value.length - 1

      while (true) {
        const { done, value } = await reader.read()
        
        if (done) {
          console.log('Stream complete')
          break
        }

        // 解码接收到的数据
        const chunk = decoder.decode(value, { stream: true })
        buffer += chunk
        
        // 直接处理整个块的内容
        if (chunk) {
          assistantText += chunk
          
          // 更新助手消息内容（直接替换最后一项）
          list.value[list.value.length - 1] = {
            role: 'assistant',
            content: assistantText,
            isMarkdown: true // 标记内容包含 Markdown
          }
          
          // 触发响应式更新
          list.value = [...list.value]
          
          // 滚动到底部
          nextTick(() => {
            const box = document.querySelector('.chat-box')
            if (box) {
              box.scrollTop = box.scrollHeight
            }
          })
        }
      }
      
      // 完成后设置加载状态为false
      isLoading.value = false
      
    } catch (error) {
      if (error.name === 'AbortError') {
        console.log('Request was aborted')
      } else {
        console.error('Fetch error:', error)
        // 添加错误提示到列表中
        list.value[list.value.length - 1] = {
          role: 'assistant', 
          content: '抱歉，连接出现问题，请稍后重试😔',
          isMarkdown: false
        }
        list.value = [...list.value] // 触发响应式更新
      }
      isLoading.value = false
    }
  }

  fetchData()
}

// 发送消息
const send = async () => {
  const val = msg.value.trim()
  if (!val || isLoading.value) return

  try {
    // 添加用户消息
    list.value.push({ role: 'user', content: val, isMarkdown: false })
    msg.value = ''
    
    // 添加空的助手消息（占位）
    list.value.push({ role: 'assistant', content: '', isMarkdown: true })
    
    // 触发响应式更新
    list.value = [...list.value]
    
    nextTick(scrollToBottom)

    // 设置加载状态
    isLoading.value = true
    
    // 创建新连接
    const url = `${baseURL}/chat?prompt=${encodeURIComponent(val)}&chatId=${conversationId.value}&curId=${userStore.id}`
    createStreamConnection(url)
    
  } catch (error) {
    console.error('发送失败:', error)
    list.value.push({
      role: 'assistant',
      content: '发送失败，请检查网络连接😔',
      isMarkdown: false
    })
    // 触发响应式更新
    list.value = [...list.value]
    isLoading.value = false
  }
}

const scrollToBottom = () => {
  const box = document.querySelector('.chat-box')
  if (box) box.scrollTop = box.scrollHeight
}

onMounted(() => {
  conversationId.value = Math.floor(100000 + Math.random() * 900000).toString()
})

onUnmounted(() => {
  if (abortController) {
    abortController.abort()
  }
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
        <div class="chat-box" ref="chatBox">
          <div
            v-for="(item, idx) in list"
            :key="idx"
            :class="['bubble', item.role === 'user' ? 'right' : 'left']"
          >
            <span 
              v-if="!item.isMarkdown" 
              class="text"
            >
              {{ item.content }}
            </span>
            <div 
              v-else 
              class="text markdown-content"
              v-html="marked.parse(item.content)"
            ></div>
          </div>
          <!-- 加载指示器 -->
          <div v-if="isLoading" class="bubble left">
            <span class="text loading">正在输入中<span class="dots">...</span></span>
          </div>
        </div>

        <!-- 底部输入区 -->
        <div class="footer">
          <textarea 
            v-model="msg" 
            placeholder="请输入内容" 
            rows="1"
            @keydown.enter.prevent="send"
          ></textarea>
          <button @click="send" :disabled="isLoading">
            {{ isLoading ? '发送中...' : '发送' }}
          </button>
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

.markdown-content {
  /* 为 Markdown 渲染内容设置样式 */
  line-height: 1.6;
  word-wrap: break-word;
}

.markdown-content p {
  margin: 0.5em 0;
}

.markdown-content p:first-child {
  margin-top: 0;
}

.markdown-content p:last-child {
  margin-bottom: 0;
}

.markdown-content pre {
  background-color: #f4f4f4;
  padding: 10px;
  border-radius: 5px;
  overflow-x: auto;
  margin: 0.5em 0;
}

.markdown-content code {
  background-color: #f4f4f4;
  padding: 2px 6px;
  border-radius: 3px;
  font-family: 'Courier New', Courier, monospace;
  font-size: 0.9em;
}

.markdown-content pre code {
  background-color: transparent;
  padding: 0;
}

.markdown-content ul, .markdown-content ol {
  margin: 0.5em 0;
  padding-left: 20px;
}

.markdown-content li {
  margin: 0.3em 0;
}

.markdown-content blockquote {
  border-left: 4px solid #ddd;
  padding-left: 10px;
  margin: 0.5em 0;
  color: #666;
}

.markdown-content table {
  width: 100%;
  border-collapse: collapse;
  margin: 0.5em 0;
  font-size: 14px;
}

.markdown-content th, .markdown-content td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.markdown-content th {
  background-color: #f4f4f4;
  font-weight: bold;
  text-align: center;
}

.markdown-content tr:nth-child(even) {
  background-color: #f9f9f9;
}

.markdown-content a {
  color: #4063ff;
  text-decoration: none;
}

.markdown-content a:hover {
  text-decoration: underline;
}

.markdown-content strong {
  font-weight: bold;
}

.markdown-content em {
  font-style: italic;
}

/* 使用深度选择器确保样式生效 */
:deep(.markdown-content p) {
  margin: 0.5em 0;
}

:deep(.markdown-content pre) {
  background-color: #f4f4f4;
  padding: 10px;
  border-radius: 5px;
  overflow-x: auto;
  margin: 0.5em 0;
}

:deep(.markdown-content code) {
  background-color: #f4f4f4;
  padding: 2px 6px;
  border-radius: 3px;
  font-family: 'Courier New', Courier, monospace;
  font-size: 0.9em;
}

:deep(.markdown-content ul), :deep(.markdown-content ol) {
  margin: 0.5em 0;
  padding-left: 20px;
}

:deep(.markdown-content li) {
  margin: 0.3em 0;
}

:deep(.markdown-content blockquote) {
  border-left: 4px solid #ddd;
  padding-left: 10px;
  margin: 0.5em 0;
  color: #666;
}

:deep(.markdown-content table) {
  width: 100%;
  border-collapse: collapse;
  margin: 0.5em 0;
  font-size: 14px;
}

:deep(.markdown-content th), :deep(.markdown-content td) {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

:deep(.markdown-content th) {
  background-color: #f4f4f4;
  font-weight: bold;
  text-align: center;
}

:deep(.markdown-content tr:nth-child(even)) {
  background-color: #f9f9f9;
}

:deep(.markdown-content a) {
  color: #4063ff;
  text-decoration: none;
}

:deep(.markdown-content strong) {
  font-weight: bold;
}

:deep(.markdown-content em) {
  font-style: italic;
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

.loading .dots {
  display: inline-block;
  animation: dots 1.5s infinite;
}

@keyframes dots {
  0%, 20% { content: '.'; }
  40% { content: '..'; }
  60%, 100% { content: '...'; }
}

/* 禁用按钮样式 */
.footer button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>