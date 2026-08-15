import { useRouter } from 'vue-router'
import axios from 'axios'
import { ref } from 'vue'

const isLogin = ref(false)
const userName = ref('')

const origin = 'http://localhost:8080'
// const origin = window.location.origin

export function useCommon() {
  const router = useRouter()

  
async function Logout() {
  try {
    const response = await axios.post(origin+'/Logout', {
    }, { withCredentials: true })
    console.log(JSON.stringify(response.data))
  } catch (error) {
    console.error('登出時發生錯誤', error)
  } finally {
    localStorage.removeItem('userData')
    router.push('/')
  }
}

function checkUser(){
    const userData = localStorage.getItem('userData')
    console.log('使用者資訊：', JSON.stringify(userData))
    isLogin.value = false
    userName.value = ''
    if(userData !== null){
        isLogin.value = true
        const data = JSON.parse(userData)
        userName.value = data.user_name
    }
    console.log('isLogin? ', isLogin.value)
}

  return { 
    isLogin,
    userName,
    origin,
    Logout, 
    checkUser 
  }
}