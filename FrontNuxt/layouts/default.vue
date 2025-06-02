<script setup lang="ts">
import {ref} from 'vue';

// 모바일 메뉴 표시 여부를 제어하는 상태
const isMobileMenuOpen = ref(false);

// 모바일 메뉴 토글 함수
const toggleMobileMenu = () => {
  isMobileMenuOpen.value = !isMobileMenuOpen.value;
};

// 화면 크기에 따라 사이드바 접기/펼치기 상태
const isSidebarCollapsed = ref(false);

// 사이드바 토글 함수
const toggleSidebar = () => {
  isSidebarCollapsed.value = !isSidebarCollapsed.value;
};

// 네비게이션 항목
const navItems = [
  {name: '홈', path: '/', icon: 'pi pi-home'},
  {name: '대시보드', path: '/dashboard', icon: 'pi pi-chart-bar'},
  {name: '프로필', path: '/profile', icon: 'pi pi-user'},
  {name: '설정', path: '/settings', icon: 'pi pi-cog'},
];
</script>

<template>
  <div class="layout-container">
    <!-- 헤더 -->
    <header class="header">
      <div class="header-content">
        <!-- 로고 및 사이드바 토글 버튼 -->
        <div class="header-left">
          <button class="sidebar-toggle" @click="toggleSidebar">
            <span class="toggle-icon"></span>
          </button>
          <div class="logo">
            <h1>내 앱</h1>
          </div>
        </div>

        <!-- 데스크탑 네비게이션 -->
        <nav class="desktop-nav">
          <ul>
            <li v-for="item in navItems" :key="item.name">name
              <NuxtLink :to="item.path">{{ item.name }}</NuxtLink>
            </li>
          </ul>
        </nav>

        <!-- 사용자 메뉴 및 모바일 메뉴 토글 -->
        <div class="header-right">
          <div class="user-menu">
            <span>사용자</span>
          </div>
          <button class="mobile-menu-toggle" @click="toggleMobileMenu">
            <span class="toggle-icon"></span>
          </button>
        </div>
      </div>

      <!-- 모바일 네비게이션 -->
      <nav class="mobile-nav" :class="{ 'active': isMobileMenuOpen }">
        <ul>
          <li v-for="item in navItems" :key="item.name">
            <NuxtLink :to="item.path" @click="isMobileMenuOpen = false">
              <i :class="item.icon"></i>
              {{ item.name }}
            </NuxtLink>
          </li>
        </ul>
      </nav>
    </header>

    <div class="main-container">
      <!-- 사이드바 -->
      <aside class="sidebar" :class="{ 'collapsed': isSidebarCollapsed }">
        <nav class="sidebar-nav">
          <ul>
            <li v-for="item in navItems" :key="item.name">
              <NuxtLink :to="item.path">
                <i :class="item.icon"></i>
                <span v-if="!isSidebarCollapsed">{{ item.name }}</span>
              </NuxtLink>
            </li>
          </ul>
        </nav>
      </aside>

      <!-- 메인 콘텐츠 -->
      <main class="main-content" :class="{ 'sidebar-collapsed': isSidebarCollapsed }">
        <slot>
        </slot>
      </main>
    </div>

    <!-- 푸터 -->
    <footer class="footer">
      <div class="footer-content">
        <p>&copy; 2025 내 앱. 모든 권리 보유.</p>
      </div>
    </footer>
  </div>
</template>

<style scoped>
/* 전체 레이아웃 컨테이너 */
.layout-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* 헤더 스타일 */
.header {
  position: sticky;
  top: 0;
  z-index: 100;
  background-color: #ffffff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  height: 60px;
}

.header-left, .header-right {
  display: flex;
  align-items: center;
}

.logo h1 {
  font-size: 1.5rem;
  margin: 0;
  margin-left: 15px;
}

/* 네비게이션 스타일 */
.desktop-nav {
  display: none;
}

.desktop-nav ul {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
}

.desktop-nav li {
  margin: 0 15px;
}

.desktop-nav a {
  text-decoration: none;
  color: #333;
  font-weight: 500;
}

.desktop-nav a:hover {
  color: #007bff;
}

/* 모바일 네비게이션 */
.mobile-nav {
  display: none;
  position: absolute;
  top: 60px;
  left: 0;
  right: 0;
  background-color: #ffffff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transform: translateY(-100%);
  transition: transform 0.3s ease-in-out;
}

.mobile-nav.active {
  display: block;
  transform: translateY(0);
}

.mobile-nav ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.mobile-nav li {
  border-bottom: 1px solid #eee;
}

.mobile-nav a {
  display: flex;
  align-items: center;
  padding: 15px 20px;
  text-decoration: none;
  color: #333;
}

.mobile-nav a i {
  margin-right: 10px;
}

/* 토글 버튼 스타일 */
.sidebar-toggle, .mobile-menu-toggle {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 40px;
  background: none;
  border: none;
  cursor: pointer;
}

.toggle-icon {
  position: relative;
  width: 20px;
  height: 2px;
  background-color: #333;
}

.toggle-icon::before, .toggle-icon::after {
  content: '';
  position: absolute;
  width: 20px;
  height: 2px;
  background-color: #333;
  transition: all 0.3s ease;
}

.toggle-icon::before {
  top: -6px;
}

.toggle-icon::after {
  top: 6px;
}

/* 메인 컨테이너 스타일 */
.main-container {
  display: flex;
  flex: 1;
}

/* 사이드바 스타일 */
.sidebar {
  width: 250px;
  background-color: #f8f9fa;
  transition: width 0.3s ease;
  overflow-x: hidden;
}

.sidebar.collapsed {
  width: 70px;
}

.sidebar-nav {
  padding: 20px 0;
}

.sidebar-nav ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.sidebar-nav li {
  margin-bottom: 5px;
}

.sidebar-nav a {
  display: flex;
  align-items: center;
  padding: 10px 20px;
  text-decoration: none;
  color: #333;
  transition: background-color 0.2s;
}

.sidebar-nav a:hover {
  background-color: #e9ecef;
}

.sidebar-nav a i {
  margin-right: 15px;
  font-size: 1.2rem;
}

.sidebar.collapsed .sidebar-nav a span {
  display: none;
}

/* 메인 콘텐츠 영역 */
.main-content {
  flex: 1;
  padding: 20px;
  transition: margin-left 0.3s ease;
}

.main-content.sidebar-collapsed {
  margin-left: 0;
}

/* 푸터 스타일 */
.footer {
  background-color: #f8f9fa;
  padding: 20px 0;
  text-align: center;
  margin-top: auto;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

/* 반응형 스타일 */
@media (min-width: 768px) {
  .desktop-nav {
    display: block;
  }

  .mobile-menu-toggle {
    display: none;
  }

  .main-content {
    margin-left: 0;
  }
}

@media (max-width: 767px) {
  .sidebar {
    position: fixed;
    height: 100%;
    z-index: 99;
    transform: translateX(-100%);
    width: 250px;
  }

  .sidebar.collapsed {
    transform: translateX(0);
    width: 250px;
  }

  .main-content {
    margin-left: 0;
  }

  .sidebar-toggle {
    display: none;
  }
}
</style>