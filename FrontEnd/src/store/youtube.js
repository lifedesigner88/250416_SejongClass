import { defineStore, storeToRefs } from "pinia";
import { POST, GET, DELETE } from "@/services/fetchApi.js";
import { useUserStore } from "@/store/user.js";
import { ref } from "vue";


export const useYoutubeStore = defineStore('youtube', () => {

    const { token } = storeToRefs(useUserStore());

    const linkResult = ref({});
    const youtubeList = ref([]);


    const linkYoutubeToUser = async (youtubeUUID) => {

        if (!token.value) alert('로그인이 필요합니다.');
        else
            linkResult.value = await POST('/youtube/add', { youtubeUUID }, token.value);
    }

    const getMyYoutubeList = async () => {
        youtubeList.value = await GET('/youtube/list', token.value);
    }

    const deleteYoutubeFromToken = async (youtubeId) => {
        await DELETE(`/youtube/token/delete/${youtubeId}`, token.value);
    }

    return {
        linkResult,
        youtubeList,
        linkYoutubeToUser,
        getMyYoutubeList,
        deleteYoutubeFromToken
    }

})

