<script setup>
import { ref, computed } from 'vue'

const youtubeUrl = ref('')

const isLoading = ref(false)
const hasError = ref(false)


const extractVideoId = (url) => {
  if (!url) return null

  //      유튜브 URL 형식:
  //     "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
  //     "https://youtu.be/dQw4w9WgXcQ",
  //     "https://youtube.com/embed/dQw4w9WgXcQ",
  //     "https://youtube.com/v/dQw4w9WgXcQ",
  //     "https://youtube.com/u/username/dQw4w9WgXcQ"

  const regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#&?]*).*/
  const match = url.match(regExp)

  console.log(match, "🚀");

  // URL이 youtu.be/VIDEO_ID 형식인 경우
  if (url.includes('youtu.be/')) {
    const parts = url.split('/')
    return parts[parts.length - 1].split('?')[0]
  }

  return (match && match[7].length === 11) ? match[7] : null
}

// 썸네일 URL을 생성하는 계산된 속성
const thumbnailUrl = computed(() => {
  const videoId = extractVideoId(youtubeUrl.value)
  if (!videoId) return null

  // 고화질 썸네일 URL 반환
  return `https://img.youtube.com/vi/${videoId}/hqdefault.jpg`
})

// 유튜브 영상 링크를 생성하는 계산된 속성
const videoLink = computed(() => {
  const videoId = extractVideoId(youtubeUrl.value)
  if (!videoId) return null

  return `https://www.youtube.com/watch?v=${videoId}`
})


// 썸네일 이미지 로딩 처리
const handleImageLoad = () => {
  isLoading.value = false
}

// 썸네일 이미지 로드 오류 처리
const handleImageError = () => {
  isLoading.value = false
  hasError.value = true
}

// 썸네일 가져오기 처리
const fetchThumbnail = () => {
  if (!youtubeUrl.value) return

  isLoading.value = true
  hasError.value = false
}
</script>

<template>
  <div class="youtube-thumbnail-container">
    <h2>유튜브 썸네일 가져오기</h2>

    <div class="input-group">
      <label for="youtube-url">유튜브 URL 입력:</label>
      <input
          type="text"
          id="youtube-url"
          v-model="youtubeUrl"
          placeholder="https://www.youtube.com/watch?v=VIDEO_ID"
      />
      <button @click="fetchThumbnail">썸네일 가져오기</button>
    </div>

    <div v-if="thumbnailUrl" class="thumbnail-display">
      <div v-if="isLoading" class="loading">
        썸네일 로딩 중...
      </div>
      <div v-else-if="hasError" class="error">
        썸네일을 가져올 수 없습니다.
      </div>
      <div v-else class="thumbnail">
        <h3>썸네일:</h3>
        <a :href="videoLink" target="_blank">
          <img
              :src="thumbnailUrl"
              alt="유튜브 썸네일"
              @load="handleImageLoad"
              @error="handleImageError"
          />
        </a>
        <p>비디오 ID: {{ extractVideoId(youtubeUrl) }}</p>
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

.loading, .error {
  text-align: center;
  padding: 20px;
  background-color: #f8f8f8;
  border-radius: 4px;
}

.error {
  color: #d9534f;
}
</style>