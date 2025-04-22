<script setup>
import { ref } from 'vue'
import { useUserStore } from './store/user.js'

// 사용자 정보를 저장할 반응형 객체
const userInfo = ref({
  name: '',
  email: '',
  password: ''
})


const userStore = useUserStore();

// => 참조
// const { increment, decrement, incrementAsync } = counterStore
// // storeToRefs를 사용하여 반응성 유지하면서 구조 분해
// const { count, name, doubleCount } = storeToRefs(store)
//


// 폼 제출 처리 함수
const handleSubmit = () => {


  userStore.createUser(userInfo.value);


  console.log('회원가입 정보:', userInfo.value)
  console.log('회원가입 반응:', userStore.userCreateResult)

  // 회원가입 후 폼 초기화
  userInfo.value = {
    name: '',
    email: '',
    password: ''
  }
}
</script>

<template>
  <div class="signup-container">
    <h2>회원가입</h2>
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label for="name">이름</label>
        <input
            type="text"
            id="name"
            v-model="userInfo.name"
            required
            placeholder="이름을 입력하세요"
        />
      </div>

      <div class="form-group">
        <label for="username">이메일</label>
        <input
            type="text"
            id="email"
            v-model="userInfo.email"
            required
            placeholder="사용할 아이디를 입력하세요"
        />
      </div>

      <div class="form-group">
        <label for="password">비밀번호</label>
        <input
            type="password"
            id="password"
            v-model="userInfo.password"
            required
            placeholder="비밀번호를 입력하세요"
        />
      </div>

      <button type="submit">가입하기</button>
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
  background-color: #4CAF50;
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
</style>