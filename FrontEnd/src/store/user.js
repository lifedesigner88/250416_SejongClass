import { ref } from 'vue'
import { defineStore } from "pinia";
import { POST } from "@/services/fetchApi.js";

export const useUserStore = defineStore('user', () => {

    const userCreateResult = ref({});
    const userLoginResult = ref({});
    const token = ref(localStorage.getItem('token') || '');

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

    return {
        userCreateResult,
        userLoginResult,
        createUser,
        loginUser,
        token
    }

})