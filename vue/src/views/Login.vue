<template>
  <div class="login-page">
    <el-card class="login-card">
      <h2>登入</h2>
      <el-input v-model="phone" placeholder="手機號碼" class="input-field" />
      <el-input v-model="password" type="password" placeholder="密碼" class="input-field" />
      <el-button type="primary" class="login-btn" @click="handleLogin">登入</el-button>
      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
      <p class="register-link">還沒有帳號?<router-link to="/register">前往註冊</router-link></p>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { useCommon } from '../composable/useCommon'

const { origin, checkUser } = useCommon()
const phone = ref('')
const password = ref('')
const errorMessage = ref('')

const router = useRouter()

async function handleLogin() {
  errorMessage.value = ''

  try {
    const response = await axios.post(origin+'/Login', {
      phone: phone.value,
      password: password.value
    }, { withCredentials: true })

    if(response.data.STATE==='OK'){
        localStorage.setItem('userData', JSON.stringify(response.data.UserData))
        checkUser()
        router.push('/Profile')
    }else{
        errorMessage.value = '登入失敗：'+response.data.MSG;
    }
  } catch (error) {
    errorMessage.value = '帳號或密碼錯誤'
  }
}
</script>

<style scoped>
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 64px);
  padding: 20px;  
  width: 100%;
  box-sizing: border-box;
}

.login-card {
  width: 100%;
  max-width: 400px;
}

.login-card h2 {
  text-align: center;
  margin-top: 0;
  margin-bottom: 20px;
}

.input-field {
  margin-bottom: 16px;
}

.login-btn {
  width: 100%;
}

.error {
  color: red;
  text-align: center;
}

.register-link {
  text-align: center;
  font-size: 14px;
  color: #606266;
}

</style>