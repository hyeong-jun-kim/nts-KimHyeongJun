import Vue from "vue";
import VueRouter from "vue-router";
import PostWrite from "./views/post/PostWrite";
import PostList from "./views/post/PostList";

Vue.use(VueRouter);

const router = new VueRouter({
    mode: "history",
    routes: [
        {path: "/posts", component: PostList},
        {path:"/post/write", component: PostWrite}
    ]
});

export default router;