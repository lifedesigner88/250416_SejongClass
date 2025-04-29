<script setup>
import { ref, onMounted } from 'vue';
import { useUserStore } from '../store/user.js'
import { storeToRefs } from "pinia";


const userStore = useUserStore();

const { getUserList } = userStore;
const { userList } = storeToRefs(userStore);

userList.value = [];

onMounted( async () => {
   await getUserList();
})

</script>

<template>
  <div class="user-list-container">
    <h2>회원 정보 목록</h2>

    <div v-if="userList.length === 0" class="no-data">
      회원 정보가 없습니다.
    </div>

    <table v-else class="user-table">
      <thead>
      <tr>
        <th>ID</th>
        <th>이름</th>
        <th>이메일</th>
        <th>권한</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in userList" :key="user.userId">
        <td>{{ user.userId }}</td>
        <td>{{ user.name }}</td>
        <td>{{ user.email }}</td>
        <td>
            <span :class="{ 'admin-role': user.role === 'ROLE_ADMIN', 'user-role': user.role === 'ROLE_USER' }">
              {{ user.role === 'ROLE_ADMIN' ? '관리자' : '일반사용자' }}
            </span>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.user-list-container {
  width: 100%;
  margin: 0 auto;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

h2 {
  margin-bottom: 20px;
  color: #333;
  text-align: center;
}

.no-data {
  text-align: center;
  padding: 20px;
  color: #888;
}

.user-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.user-table th, .user-table td {
  padding: 12px 15px;
  text-align: left;
  border-bottom: 1px solid #eee;
}

.user-table th {
  background-color: #f8f9fa;
  color: #333;
  font-weight: bold;
}

.user-table tr:hover {
  background-color: #f5f5f5;
}

.admin-role {
  background-color: #ff7675;
  color: white;
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 0.8rem;
}

.user-role {
  background-color: #74b9ff;
  color: white;
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 0.8rem;
}
</style>