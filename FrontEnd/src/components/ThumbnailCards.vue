<script setup>
import { ref, onMounted } from 'vue';
import { useYoutubeStore } from '../store/youtube.js';
import { useUserStore } from '../store/user.js';
import { storeToRefs } from "pinia";

// 피니아
const { youtubeList } = storeToRefs(useYoutubeStore());
const { token } = storeToRefs(useUserStore());
const { getMyYoutubeList, deleteYoutubeFromToken } = useYoutubeStore();


// 비디오 재생 페이지로 이동
const openVideo = (youtubeUUID) => {
  window.open(`https://www.youtube.com/watch?v=${youtubeUUID}`, '_blank');
};

// 초기화 시 모든 비디오 로딩 상태 설정
onMounted(() => {
  if (token.value) {
    getMyYoutubeList();
  }
});

// 비디오 삭제 함수
const removeVideo = async (youtubeId) => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    await deleteYoutubeFromToken(youtubeId);
    await getMyYoutubeList();
  }
};
</script>

<template>
  <div class="youtube-gallery-container">
    <div class="youtube-cards">
      <div v-for="video in youtubeList" :key="video.youtubeId" class="youtube-card">
        <div class="thumbnail-wrapper" @click="openVideo(video.youtubeUUID)">
          <img
              :src="`https://img.youtube.com/vi/${video.youtubeUUID}/hqdefault.jpg`"
              :alt="`YouTube 비디오 ${video.youtubeId}`"
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
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 7px;
}

.youtube-card {
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  background-color: white;
}

.youtube-card:hover {
  transform: translateY(-10px);
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
  width: 40px;
  height: 40px;
  background-color: rgba(255, 0, 0, 0.5);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0.8;
  transition: opacity 0.3s ease, transform 0.3s ease;
}

.thumbnail-wrapper:hover .play-button {
  opacity: 1;
  transform: translate(-50%, -50%) scale(1.2);
}

.video-info {
  padding: 15px;
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