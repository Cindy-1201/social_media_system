<template>
  <el-row style="width: 100%;">
    <el-col class="header">
      <el-row style="width: 100%;">
        <el-col :span="12" class="header-left">
          <span class="welcome-text">歡迎 {{ userName }}!</span>
        </el-col>
        <el-col :span="12" class="header-right">
          <el-button
            v-if="isLogin"
            type="danger"
            plain
            @click="handleLogout"
          >
            登出
          </el-button>
          <el-button v-else type="primary" @click="goLogin">
            登入
          </el-button>
        </el-col>
      </el-row>
    </el-col>

    <el-col>
      <RouterView />
    </el-col>
  </el-row>
</template>

<script setup lang="ts">
import { RouterLink, RouterView, useRouter } from 'vue-router'
import axios from 'axios'
import { onMounted, ref } from 'vue'
import { useCommon } from './composable/useCommon'
// import { RefSymbol } from '@vue/reactivity'

const { isLogin, userName, Logout, checkUser } = useCommon()
const router = useRouter()

onMounted(() => {
    checkUser();
})

async function handleLogout() {
    await Logout()
    checkUser()
}

function goLogin(){
   router.push('/login')
}

</script>

<style scoped>
.header {
  border-bottom: 1px solid #e4e7ed;
  background-color: #fff;
  display: flex;
  align-items: center;
}

.header-left {
  display: flex;
  align-items: center;
}

.header-right {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.welcome-text {
  font-size: 20px;
  color: #606266;
}
</style>