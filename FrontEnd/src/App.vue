<script setup>
import { ref } from 'vue'
import SignupForm from './components/SignupForm.vue'
import YoutubeThumbnail from './components/YoutubeThumbnail.vue'
import LoginForm from "@/components/LoginForm.vue";
import UserList from "@/components/UserList.vue";

// 현재 활성화된 탭을 관리하는 반응형 변수
const activeTab = ref('signup') // 'signup' 또는 'youtube'

import { useUserStore } from './store/user.js'
import { storeToRefs } from "pinia";

const { token, userLoginResult } = storeToRefs(useUserStore())

const logout = () => {
  localStorage.removeItem('token')
  userLoginResult.value = "";
  token.value = "";
  alert('로그아웃 되었습니다.')
}

// 회원가입 성공 시 처리 함수
</script>

<template>
  <div class="app-container">
    <header>
      <h1> {{ userLoginResult.name || '로그인 하세요' }} </h1>
      <nav>
        <button
            @click="activeTab = 'signup'"
            :class="{ signup: activeTab === 'signup' }"
        >
          회원가입
        </button>
        <button
            @click="activeTab = 'login'"
            :class="{ login: activeTab === 'login' }"
        >
          로그인
        </button>
        <button @click="logout" >
          로그아웃
        </button>
        <button
            @click="activeTab = 'youtube'"
            :class="{ youtube: activeTab === 'youtube' }"
        >
          유튜브 썸네일
        </button>
        <button
            @click="activeTab = 'userlist'"
            :class="{ userlist: activeTab === 'userlist' }"
        >
          유저리스트
        </button>
      </nav>
    </header>

    <main>
      <SignupForm
          v-if="activeTab === 'signup'"
      />
      <LoginForm
          v-if="activeTab === 'login'"
      />
      <YoutubeThumbnail
          v-if="activeTab === 'youtube'"
      />
      <UserList
          v-if="activeTab === 'userlist'"
      />
    </main>
  </div>
</template>

<style>
/* 전역 스타일 */
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

body {
  font-family: 'Noto Sans KR', sans-serif;
  background-color: #f5f5f5;
  color: #333;
  line-height: 1.6;
}

.app-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

header {
  margin-bottom: 30px;
  text-align: center;
}

h1 {
  margin-bottom: 20px;
  color: #333;
}

nav {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

nav button {
  padding: 10px 20px;
  margin: 0 10px;
  background-color: #eee;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;
}

nav button.signup {
  background-color: #4CAF50;
  color: white;
}

nav button.login {
  background-color: #1790b5;
  color: white;
}

nav button.userlist {
  background-color: #9900e6;
  color: white;
}

nav button.youtube {
  background-color: #FF0000;
  color: white;
}


nav button:hover:not(.active) {
  background-color: #ddd;
}

main {
  padding: 20px;
}
</style>