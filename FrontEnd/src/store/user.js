import { ref } from 'vue'
import { defineStore } from "pinia";
import { POST } from "@/services/fetchApi.js";

export const useUserStore = defineStore('user', () => {

    const userCreateResult = ref({});
    const userLoginResult = ref({});

    const createUser = async (userData) => {
        userCreateResult.value = await POST('/user/create',userData);
    }

    const loginUser = async (userData) => {
        userLoginResult.value = await POST('/user/login',userData);
    }

    return {
        userCreateResult,
        userLoginResult,
        createUser,
        loginUser,
    }

})