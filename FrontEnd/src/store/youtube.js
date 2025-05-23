import { defineStore, storeToRefs } from "pinia";
import { POST } from "@/services/fetchApi.js";
import { useUserStore } from "@/store/user.js";
import { ref } from "vue";


export const useYoutubeStore = defineStore('youtube', () => {

    const { token } = storeToRefs(useUserStore());

    const linkResult = ref({});


    const linkYoutubeToUser = async (youtubeUUID) => {

        if (!token.value) alert('로그인이 필요합니다.');
        else
            linkResult.value = await POST('/youtube/add', { youtubeUUID }, token.value);

    }

    return {
        linkResult,
        linkYoutubeToUser,
    }

})

