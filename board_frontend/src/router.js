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
        {path: "/", component: PostList},
        {path:"/post/write", component: PostWrite},
        {path:"/post/modify/:postId", component: PostModify},
        {path:"/post/:postId", component: PostDetail},
        {path: "*", redirect: "/404"},
        {path: "/404", component: PageNotFound},
    ]
});

export default router;