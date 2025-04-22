import { ref } from 'vue'
import { defineStore } from "pinia";
import fetchApi from "@/services/fetchApi.js";

export const useUserStore = defineStore('user', () => {

    const userCreateResult = ref({});

    const createUser = async (userData) => {
        userCreateResult.value = await fetchApi('/user/create', {
            method: 'POST',
            body: JSON.stringify(userData)
        })
    }

    return {
        userCreateResult,
        createUser,
    }

})