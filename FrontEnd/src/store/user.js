import { ref } from 'vue'
import { defineStore } from "pinia";
import { POST, GET } from "@/services/fetchApi.js";
import router from "@/router/index.js";

export const useUserStore = defineStore('user', () => {

    const token = ref(localStorage.getItem('token') || '');

    const userCreateResult = ref({});
    const userLoginResult = ref({});
    const userList = ref({});

    const createUser = async (userData) => {
        userCreateResult.value = await POST('/user/create',userData);
    }

    const loginUser = async (userData) => {
        userLoginResult.value = await POST('/user/login',userData);
        if (userLoginResult.value.accessToken) {
            token.value = userLoginResult.value.accessToken;
            localStorage.setItem('token', token.value);
        }
    }

    const logout = () => {
        localStorage.removeItem('token')
        userLoginResult.value = "";
        token.value = "";
        alert('로그아웃 되었습니다.')
        router.push('/login');
    }

    const getUserList = async () => {
        const data = await GET('/admin/user/list', token.value)
        if (data[0].userId) userList.value = data; // 아이디 형태의 자료가 있으면 저장
        else userList.value = []; // 없으면 빈 배열 리턴.
    }

    const getMyInfoFromToken = async () => {
        userLoginResult.value = await GET('/user/token/myinfo', token.value)
    }

    return {
        userCreateResult,
        userLoginResult,
        userList,
        createUser,
        loginUser,
        logout,
        getUserList,
        getMyInfoFromToken,
        token
    }
})