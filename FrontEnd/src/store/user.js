import { ref } from 'vue'
import { defineStore } from "pinia";
import { POST, GET } from "@/services/fetchApi.js";

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

    const getUserList = async () => {
        userList.value = await GET('/admin/user/list', token.value);
    }

    return {
        userCreateResult,
        userLoginResult,
        userList,
        createUser,
        loginUser,
        getUserList,
        token
    }

})