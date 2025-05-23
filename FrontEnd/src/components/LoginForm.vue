<script setup>
import { ref } from 'vue'
import { useUserStore } from '../store/user.js'

// 사용자 정보를 저장할 반응형 객체
const loginInfo = ref({
  email: '',
  password: ''
})

const { loginUser } = useUserStore()

// 폼 제출 처리 함수
const handleSubmit = async () => {
  await loginUser(loginInfo.value);
}
</script>

<template>
  <div class="signup-container">
    <h2>로그인</h2>
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label for="username">이메일</label>
        <input
            type="text"
            id="email"
            v-model="loginInfo.email"
            required
            placeholder="로그인할 아이디를 입력하세요"
        />
      </div>

      <div class="form-group">
        <label for="password">비밀번호</label>
        <input
            type="password"
            id="password"
            v-model="loginInfo.password"
            required
            placeholder="비밀번호를 입력하세요"
        />
      </div>
      <button type="submit">로그인</button>
    </form>
  </div>
</template>

<style scoped>
.signup-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #eee;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #1790b5;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 10px;
}

button:hover {
  background-color: #45a049;
}

.result-message {
  margin-top: 15px;
  padding: 10px;
  background-color: #f8f9fa;
  border-radius: 4px;
  text-align: center;
}
</style>