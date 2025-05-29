import { createRouter, createWebHistory } from "vue-router";


const routes = [
    {
        path: "/",
        name: "Home",
    },
    {
        path: "/signup",
        name: "Signup",
        component: () => import("@/components/SignupForm.vue"),
    },
    {
        path: "/login",
        name: "Login",
        component: () => import("@/components/LoginForm.vue"),
    },
    {
        path: "/youtubelist",
        name: "YoutubeList",
        component: () => import("@/components/YoutubeThumbnail.vue"),
    },
    {
        path: "/userlist",
        name: "UserList",
        component: () => import("@/components/UserList.vue")
    }
]


const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;