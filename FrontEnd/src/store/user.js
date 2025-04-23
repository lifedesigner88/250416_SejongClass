import { ref } from 'vue'
import { defineStore } from "pinia";
import { POST } from "@/services/fetchApi.js";

export const useUserStore = defineStore('user', () => {

    const userCreateResult = ref({});

    const createUser = async (userData) => {
        userCreateResult.value = await POST('/user/create',userData);
    }

    return {
        userCreateResult,
        createUser,
    }

})