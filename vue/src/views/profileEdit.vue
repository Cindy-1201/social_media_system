<template>
  <div class="edit-page">
    <el-card class="edit-card">
      <h2>編輯個人資料</h2>

      <el-form label-width="100px" @submit.prevent>
        <el-form-item label="封面照網址">
          <el-input v-model="form.cover_image" placeholder="請貼上圖片網址" />
        </el-form-item>

        <div class="preview" v-if="form.cover_image">
          <img :src="form.cover_image" class="cover-preview" @error="onImageError" />
        </div>

        <el-form-item label="姓名">
          <el-input v-model="form.user_name" placeholder="請輸入姓名" />
        </el-form-item>

        <el-form-item label="Email">
          <el-input v-model="form.email" placeholder="請輸入 Email" />
        </el-form-item>

        <el-form-item label="手機號碼">
          <el-input v-model="form.phone" placeholder="請輸入手機號碼" />
        </el-form-item>

        <el-form-item label="自我介紹">
          <el-input
            v-model="form.biography"
            type="textarea"
            :rows="4"
            placeholder="這個人很懶,還沒有寫自我介紹"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" :loading="submitting" @click="handleSubmit">
            儲存
          </el-button>
          <el-button @click="goBack">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { useCommon } from '../composable/useCommon'

interface UserForm {
  user_id: string
  user_name: string
  email: string
  phone: string
  biography: string
  cover_image: string
}

const router = useRouter()
const { origin, checkUser } = useCommon()
const submitting = ref(false)

const form = reactive<UserForm>({
  user_id: '',
  user_name: '',
  email: '',
  phone: '',
  biography: '',
  cover_image: ''
})

onMounted(() => {
  const stored = localStorage.getItem('userData')
  if (!stored) {
    router.push('/login')
    return
  }
  const data = JSON.parse(stored)
  form.user_id = data.user_id
  form.user_name = data.user_name
  form.email = data.email
  form.phone = data.phone
  form.biography = data.biography
  form.cover_image = data.cover_image
})

function onImageError() {
  ElMessage.warning('圖片網址無法載入,請確認網址是否正確')
}

async function handleSubmit() {
  submitting.value = true
  try {
    const response = await axios.put(
      origin+'/UpdataUser',
      {
        user_id: form.user_id,
        user_name: form.user_name,
        email: form.email,
        phone: form.phone,
        cover_image: form.cover_image,
        biography: form.biography
      },
      { withCredentials: true }
    )

    if (response.data.STATE === 'OK') {
      localStorage.setItem('userData', JSON.stringify(response.data.UserData))
      checkUser()
      ElMessage.success('更新成功')
      router.push('/Profile')
    } else {
      ElMessage.error(response.data.MSG || '更新失敗')
    }
  } catch (error) {
    ElMessage.error('更新失敗,請稍後再試')
  } finally {
    submitting.value = false
  }
}

function goBack() {
  router.push('/Profile')
}
</script>

<style scoped>
.edit-page {
  display: flex;
  justify-content: center;
  padding: 40px 20px;
}

.edit-card {
  width: 100%;
  max-width: 500px;
}

.edit-card h2 {
  text-align: center;
  margin-top: 0;
  margin-bottom: 24px;
}

.preview {
  margin-bottom: 16px;
}

.cover-preview {
  width: 100%;
  height: 180px;
  object-fit: cover;
  border-radius: 8px;
  display: block;
}
</style>