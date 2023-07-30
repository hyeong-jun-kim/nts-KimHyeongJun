import Vue from "vue";
import VueRouter from "vue-router";
import PostWrite from "./views/post/PostWrite";
import PostList from "./views/post/PostList";
import PostDetail from "./views/post/PostDetail";
import PostModify from "./views/post/PostModify";
import PageNotFound from "./views/PageNotFound";

Vue.use(VueRouter);

const router = new VueRouter({
    mode: "history",
    routes: [
        {path: "/", component: PostList}, // 글 목록
        {path:"/post/write", component: PostWrite}, // 글 작성
        {path:"/post/modify/:postId", component: PostModify}, // 글 수정
        {path:"/post/:postId", component: PostDetail}, // 상세 글 보기
        {path: "*", redirect: "/404"},
        {path: "/404", component: PageNotFound},
    ]
});

export default router;