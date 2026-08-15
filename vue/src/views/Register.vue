<template>
  <div class="container">
    <h2>註冊</h2>
    <el-input v-model="name" placeholder="姓名" />
    <el-input v-model="email" placeholder="Email" />
    <el-input v-model="phone" placeholder="手機號碼" />
    <el-input v-model="password" type="password" placeholder="密碼" />
    <el-button @click="handleRegister">註冊</el-button>

    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    <p v-if="successMessage" class="success">{{ successMessage }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { useCommon } from '../composable/useCommon'

const name = ref('')
const email = ref('')
const phone = ref('')
const password = ref('')
const errorMessage = ref('')
const successMessage = ref('')

const router = useRouter()
const { origin } = useCommon()

async function handleRegister() {
  errorMessage.value = ''
  successMessage.value = ''

  try {
    const response = await axios.post(origin+'/Register', {
      user_name: name.value,
      email: email.value,
      phone: phone.value,
      password: password.value
    }, { withCredentials: true })

    console.log(JSON.stringify(response.data));

    if(response.data.STATE === 'OK'){
        successMessage.value = '註冊成功,即將跳轉到登入頁'

        setTimeout(() => {
            router.push('/login')
        }, 1500)
    }else{
        errorMessage.value = '註冊失敗：'+response.data.MSG;
    }

  } catch (error) {
    console.log(error)
    errorMessage.value = '註冊失敗,請稍後再試'
  }
}
</script>

<style scoped>
.container {
  max-width: 400px;
  margin: 50px auto;
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.error {
  color: red;
}
.success {
  color: green;
}
</style>