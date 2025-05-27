<script setup>
import { ref, computed } from 'vue'
import { useYoutubeStore } from '../store/youtube.js'
import { storeToRefs } from "pinia";
import ThumbnailCards from "@/components/ThumbnailCards.vue";

const youtubeUrl = ref('')

const extractVideoId = (url) => {
  if (!url) return null
  const regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#&?]*).*/
  const match = url.match(regExp)

  // URL이 youtu.be/VIDEO_ID 형식인 경우
  if (url.includes('youtu.be/')) {
    const parts = url.split('/')
    return parts[parts.length - 1].split('?')[0]
  }
  return (match && match[7].length === 11) ? match[7] : null
}

const thumbnailUrl = computed(() => {
  const videoId = extractVideoId(youtubeUrl.value)
  if (!videoId) return null

  // 고화질 썸네일 URL 반환
  return `https://img.youtube.com/vi/${videoId}/hqdefault.jpg`
})

// 썸네일 가져오기 처리
const { linkYoutubeToUser, getMyYoutubeList } = useYoutubeStore()
const { linkResult } = storeToRefs(useYoutubeStore());

const callLinkYoutubeToUser = async () => {
  const youtubeUUID = extractVideoId(youtubeUrl.value)
  await linkYoutubeToUser(youtubeUUID);
  await getMyYoutubeList();
  youtubeUrl.value = "";
}

</script>

<template>
  <ThumbnailCards></ThumbnailCards>
  <div class="youtube-thumbnail-container">
    <div class="input-group">
      <input
          type="text"
          id="youtube-url"
          v-model="youtubeUrl"
          placeholder="유튜브 URL 입력"
      />
      <button @click="callLinkYoutubeToUser">유튜브 연결</button>
    </div>
    <div v-if="thumbnailUrl" class="thumbnail-display">
      <div class="thumbnail">
        <img
            :src="thumbnailUrl"
            alt="유튜브 썸네일"
        />
      </div>
    </div>
  </div>
</template>

<style scoped>
.youtube-thumbnail-container {
  max-width: 600px;
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

.input-group {
  margin-bottom: 20px;
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
  margin-bottom: 10px;
}

button {
  padding: 10px;
  background-color: #FF0000;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
}

button:hover {
  background-color: #CC0000;
}

.thumbnail-display {
  margin-top: 20px;
}

.thumbnail img {
  max-width: 100%;
  border-radius: 4px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s ease;
}

.thumbnail img:hover {
  transform: scale(1.02);
}

</style>