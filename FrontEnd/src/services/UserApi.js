
const BASE_URL = import.meta.env.VITE_API_URL;


// API 요청 헬퍼 함수
async function fetchApi(endpoint, options = {}) {
    const url = `${BASE_URL}${endpoint}`

    // 기본 헤더 설정
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers
    }

    try {
        const response = await fetch(url, {
            ...options,
            headers
        })

        // HTTP 에러 확인
        if (!response.ok) {
            const errorData = await response.json().catch(() => ({}))
            throw new Error(errorData.message || `서버 오류: ${response.status}`)
        }

        // 응답이 비어있는지 확인
        const contentType = response.headers.get('content-type')
        if (contentType && contentType.includes('application/json')) {
            return await response.json()
        }

        return await response.text()
    } catch (error) {
        console.error('API 요청 오류:', error)
        throw error
    }
}

// 사용자 관련 API
export const userService = {
    // 회원가입
    register: (userData) => {
        return fetchApi('/users/register', {
            method: 'POST',
            body: JSON.stringify(userData)
        })
    },
}

export default {
    user: userService
}
