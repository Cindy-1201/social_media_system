<template>
  <div>
    <div v-if="user" class="profile-card">
      <div class="cover-image" :style="coverStyle"></div>

      <div class="profile-info">
        <h3>{{ user.user_name }}</h3>
        <p class="biography">{{ user.biography || '這個人很懶,還沒有寫自我介紹' }}</p>
        <p class="contact">電話 {{ user.phone }}</p>
        <p class="contact">信箱 {{ user.email }}</p>
      </div>
      <br/>
      <el-button class="edit-btn" @click="goToEdit">編輯個人資料</el-button>
    </div>

    <!-- <div class="post-section">
      <h3>我的貼文</h3>
    </div> -->
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { useCommon } from '../composable/useCommon'

const { origin } = useCommon()
const router = useRouter()
const user:any = ref(null)
const myPosts = ref([])
const loading = ref(true)
const coverStyle = computed(() => {
  if ((user.value.cover_image === null) || (user.value.cover_image === '')) {
    return { backgroundColor: '#e0e0e0' }
  }
  return {
    backgroundImage: `url(${user.value.cover_image})`,
    backgroundSize: 'cover',
    backgroundPosition: 'center'
  }
})

onMounted(async () => {
  const userData = localStorage.getItem('userData')
  if (userData === null) {
    router.push('/login')
    return
  }
  user.value = JSON.parse(userData)  
  // await loadMyPosts()
})

// async function loadMyPosts() {
//   loading.value = true
//   try {
//     const response = await axios.get(
//       origin+'/SelectPost',
//       { 
//         params: {
//           user_id: user.value.user_id
//         },
//         withCredentials: true 
//       }
//     )
//     myPosts.value = response.data      
//     console.log("myPosts=>"+myPosts.value);
//   } catch (error) {
//     console.error('取得貼文失敗', error)
//   } finally {
//     loading.value = false
//   }
// }

async function handleLogout() {
    const { Logout } = useCommon()
    Logout()
    router.push('/login')
}

function goToEdit() {
  router.push('/profile/edit')
}
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 24px;
  border-bottom: 1px solid #e0e0e0;
}

.header h2 {
  margin: 0;
}

.profile-card {
  max-width: 600px;
  margin: 24px auto;
  padding: 20px;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
}

.biography {
  color: #666;
  margin: 4px 0;
}

.contact {
  font-size: 14px;
  color: #444;
  margin: 2px 0;
}

.cover-image {
  width: 100%;
  height: 180px;
  border-radius: 8px;
  margin-bottom: 12px;
}
</style>