import Vue from "vue";
import VueRouter from "vue-router";
import PostWrite from "./views/post/PostWrite";
import PostList from "./views/post/PostList";
import PostDetail from "./views/post/PostDetail";


Vue.use(VueRouter);

const router = new VueRouter({
    mode: "history",
    routes: [
        {path: "/posts", component: PostList},
        {path:"/post/write", component: PostWrite},
        {path:"/post", component: PostDetail},
    ]
});

export default router;