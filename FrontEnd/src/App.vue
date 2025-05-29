<script setup>
import { onMounted, ref } from 'vue'
import { useUserStore } from './store/user.js'
import { storeToRefs } from "pinia";

const activeTab = ref('home');

const { token, userLoginResult } = storeToRefs(useUserStore())
const { getMyInfoFromToken, logout } = useUserStore();

onMounted(() => {
  // 로컬스토리지에 토큰이 존재하면 로그인 처리
  if (token.value && Object.keys(userLoginResult.value).length === 0) {
    getMyInfoFromToken();
  }
})

// 회원가입 성공 시 처리 함수
</script>

<template>
  <div class="app-container">
    <header>
      <h1> {{ userLoginResult.name || '로그인 하세요' }} </h1>
      <nav>
        <router-link to="signup">
          <button
              @click="activeTab = 'signup'"
              :class="{ signup: activeTab === 'signup' }"
          >
            회원가입
          </button>
        </router-link>

        <router-link to="login">
          <button
              @click="activeTab = 'login'"
              :class="{ login: activeTab === 'login' }"
          >
            로그인
          </button>
        </router-link>

        <router-link to="youtubelist">
          <button
              @click="activeTab = 'youtube'"
              :class="{ youtube: activeTab === 'youtube' }"
          >
            유튜브 영상
          </button>
        </router-link>

        <router-link to="userlist">
          <button
              @click="activeTab = 'userlist'"
              :class="{ userlist: activeTab === 'userlist' }"
          >
            유저리스트
          </button>
        </router-link>

        <button @click="logout">
          로그아웃
        </button>
      </nav>
    </header>

    <main>

      <router-view/>

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