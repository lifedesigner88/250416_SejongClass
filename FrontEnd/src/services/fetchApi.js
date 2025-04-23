const BASE_URL = import.meta.env.VITE_API_URL;

// API 요청 헬퍼 함수
export async function POST(endpoint, userData) {
    const url = `${BASE_URL}${endpoint}`

    try {
        const response = await fetch(url, {
            method : 'POST',
            headers : {
                'Content-Type': 'application/json'
            },
            body : JSON.stringify(userData)
        })

        return await response.json()

    } catch (error) {
        console.error('API 요청 오류:', error)
        throw error
    }
}

export async function GET(endpoint) {
    const url = `${BASE_URL}${endpoint}`

    try {
        const response = await fetch(url, {
            method : 'GET',
            headers : {
                'Content-Type': 'application/json'
            }
        })

        return await response.json()

    } catch (error) {
        console.error('API 요청 오류:', error)
        throw error
    }
}
