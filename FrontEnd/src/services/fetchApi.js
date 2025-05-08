const BASE_URL = import.meta.env.VITE_API_URL;

// API 요청 헬퍼 함수
export async function POST(endpoint, userData, token = '') {
    const url = `${BASE_URL}${endpoint}`

    const headers = { 'Content-Type': 'application/json' }
    if (token) headers['Authorization'] = `Bearer ${token}`

    try {
        const response = await fetch(url, {
            method : 'POST',
            headers,
            body : JSON.stringify(userData)
        })
        if (response.status === 409) {
            alert('이미 존재하는 이메일입니다.');
        }
        return await response.json()

    } catch (error) {
        console.error('API 요청 오류:', error)
        throw error
    }
}

export async function GET(endpoint, token = '') {
    const url = `${BASE_URL}${endpoint}`

    const headers = { 'Content-Type': 'application/json' }
    if (token) headers['Authorization'] = `Bearer ${token}`

    try {
        const response = await fetch(url, {
            method : 'GET',
            headers
        })

        if (response.status === 403) {
            alert('권한이 없습니다.');
        }
        return await response.json()

    } catch (error) {
        console.error('API 요청 오류:', error)
        throw error
    }
}
