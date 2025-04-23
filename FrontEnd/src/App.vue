<script setup>
import { ref } from 'vue'
import SignupForm from './components/SignupForm.vue'
import YoutubeThumbnail from './components/YoutubeThumbnail.vue'

// 현재 활성화된 탭을 관리하는 반응형 변수
const activeTab = ref('signup') // 'signup' 또는 'youtube'

// 회원가입 성공 시 처리 함수
const handleSignupSuccess = (userData) => {
  console.log('회원가입 성공:', userData)
  // 성공 후 원하는 로직 (예: 다른 탭으로 전환)
}
</script>

<template>
  <div class="app-container">
    <header>
      <h1>Vue 애플리케이션</h1>
      <nav>
        <button
            @click="activeTab = 'signup'"
            :class="{ active: activeTab === 'signup' }"
        >
          회원가입
        </button>
        <button
            @click="activeTab = 'youtube'"
            :class="{ active: activeTab === 'youtube' }"
        >
          유튜브 썸네일
        </button>
      </nav>
    </header>

    <main>
      <SignupForm
          v-if="activeTab === 'signup'"
          @signup-success="handleSignupSuccess"
      />
      <YoutubeThumbnail
          v-if="activeTab === 'youtube'"
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

nav button.active {
  background-color: #4CAF50;
  color: white;
}

nav button:hover:not(.active) {
  background-color: #ddd;
}

main {
  padding: 20px;
}
</style>