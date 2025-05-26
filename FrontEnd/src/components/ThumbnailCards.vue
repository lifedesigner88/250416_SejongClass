<script setup>
import { ref, onMounted } from 'vue';
import { useYoutubeStore } from '../store/youtube.js';
import { storeToRefs } from "pinia";

// 데이터 예시
const { youtubeList } = storeToRefs(useYoutubeStore());
const { getMyYoutubeList } = useYoutubeStore();


// 로딩 상태 관리
const loadingStates = ref({});

// 썸네일 이미지 로딩 처리
const handleImageLoad = (id) => {
  loadingStates.value[id] = false;
};

// 썸네일 이미지 로드 오류 처리
const handleImageError = (id) => {
  loadingStates.value[id] = 'error';
};

// 비디오 재생 페이지로 이동
const openVideo = (youtubeUUID) => {
  window.open(`https://www.youtube.com/watch?v=${youtubeUUID}`, '_blank');
};

// 초기화 시 모든 비디오 로딩 상태 설정
onMounted(() => {

  youtubeList.value.forEach(video => {
    loadingStates.value[video.youtubeId] = true;
  });

  getMyYoutubeList();

});

// 비디오 삭제 함수
const removeVideo = (youtubeId) => {
  youtubeList.value = youtubeList.value.filter(video => video.youtubeId !== youtubeId);
};
</script>

<template>
  <div class="youtube-gallery-container">
    <h2>나의 YouTube 동영상 모음</h2>

    <div class="youtube-cards">
      <div v-for="video in youtubeList" :key="video.youtubeId" class="youtube-card">
        <div class="thumbnail-wrapper" @click="openVideo(video.youtubeUUID)">
          <div v-if="loadingStates[video.youtubeId] === true" class="loading">
            로딩 중...
          </div>
          <div v-else-if="loadingStates[video.youtubeId] === 'error'" class="error">
            썸네일을 불러올 수 없습니다.
          </div>
          <img
              :src="`https://img.youtube.com/vi/${video.youtubeUUID}/hqdefault.jpg`"
              :alt="`YouTube 비디오 ${video.youtubeId}`"
              @load="handleImageLoad(video.youtubeId)"
              @error="handleImageError(video.youtubeId)"
              class="thumbnail-image"
          />
          <div class="play-button">
            <svg viewBox="0 0 24 24" width="24" height="24">
              <path fill="#fff" d="M8 5v14l11-7z"/>
            </svg>
          </div>
        </div>
        <div class="video-info">
          <button class="remove-button" @click="removeVideo(video.youtubeId)">삭제</button>
        </div>
      </div>
    </div>

    <div v-if="youtubeList.length === 0" class="empty-state">
      저장된 YouTube 동영상이 없습니다.
    </div>
  </div>
</template>

<style scoped>
.youtube-gallery-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h2 {
  text-align: center;
  margin-bottom: 30px;
  color: #333;
}

.youtube-cards {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 25px;
}

.youtube-card {
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  background-color: white;
}

.youtube-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
}

.thumbnail-wrapper {
  position: relative;
  width: 100%;
  height: 0;
  padding-bottom: 56.25%; /* 16:9 비율 */
  overflow: hidden;
  cursor: pointer;
  background-color: #f0f0f0;
}

.thumbnail-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.5s ease;
}

.thumbnail-wrapper:hover .thumbnail-image {
  transform: scale(1.05);
}

.play-button {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 60px;
  height: 60px;
  background-color: rgba(255, 0, 0, 0.8);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0.8;
  transition: opacity 0.3s ease, transform 0.3s ease;
}

.thumbnail-wrapper:hover .play-button {
  opacity: 1;
  transform: translate(-50%, -50%) scale(1.1);
}

.video-info {
  padding: 15px;
}


.loading, .error {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f8f8f8;
  color: #666;
}

.error {
  color: #d9534f;
}

.remove-button {
  padding: 6px 12px;
  background-color: #f44336;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.9rem;
  transition: background-color 0.3s;
}

.remove-button:hover {
  background-color: #d32f2f;
}

.empty-state {
  text-align: center;
  padding: 40px;
  color: #666;
  font-size: 1.1rem;
  background-color: #f8f8f8;
  border-radius: 8px;
  margin-top: 20px;
}
</style>