import Vue from "vue";
import VueRouter from "vue-router";
import PostWrite from "./views/post/PostWrite";
import PostList from "./views/post/PostList";
import PostDetail from "./views/post/PostDetail";
import PostModify from "./views/post/PostModify";

Vue.use(VueRouter);

const router = new VueRouter({
    mode: "history",
    routes: [
        {path: "/posts", component: PostList},
        {path:"/post/write", component: PostWrite},
        {path:"/post/modify/:postId", component: PostModify},
        {path:"/post/:postId", component: PostDetail},
    ]
});

export default router;