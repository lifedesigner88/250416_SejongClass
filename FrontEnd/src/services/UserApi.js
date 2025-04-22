const BASE_URL = import.meta.env.VITE_API_URL;

// API 요청 헬퍼 함수
async function fetchApi(endpoint, options = {}) {
    const url = `${BASE_URL}${endpoint}`
    const headers = {'Content-Type': 'application/json'}

    console.log("안녕하세요.");
    console.log(headers, "테스트");

    try {
        const response = await fetch(url, {
            ...options,
            headers
        })

        return await response.text()

    } catch (error) {
        console.error('API 요청 오류:', error)
        throw error
    }
}


export const userService = {
    register: (userData) => {
        return fetchApi('/user/create', {
            method: 'POST',
            body: JSON.stringify(userData)
        })
    },
}
