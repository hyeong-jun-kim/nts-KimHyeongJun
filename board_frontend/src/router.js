import Vue from "vue";
import VueRouter from "vue-router";
import Home from "./views/Home";
import PostWrite from "./views/post/PostWrite";

Vue.use(VueRouter);

const router = new VueRouter({
    mode: "history",
    routes: [
        {path: "/", component: Home},
        {path:"/post/write", component: PostWrite}
    ]
});

export default router;