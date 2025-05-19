import VueRouter from 'vue-router'
import Vue from 'vue'
import Store from '@/store'
import HelloWorld from "@/components/HelloWorld.vue";
import H2 from "@/components/h2.vue";

Vue.use(VueRouter)

// 解决编程式路由往同一地址跳转时会报错的情况
const originalPush = VueRouter.prototype.push;
const originalReplace = VueRouter.prototype.replace;

// push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalPush.call(this, location, onResolve, onReject);
    return originalPush.call(this, location).catch(err => err);
};

//replace
VueRouter.prototype.replace = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalReplace.call(this, location, onResolve, onReject);
    return originalReplace.call(this, location).catch(err => err);
};

const routes = [
    {
        path: '/main',
        name:'main',
        component: () =>import('@/views/main/index.vue'),
        children:[{
            path:'/main/h1',
            name:'H1',
            component: () =>import('@/components/h1.vue')
        },{
            path:'/main/h2',
            name:'H2',
            component:() =>import('@/components/h2.vue')
        },
        ]
    }
]


const router = new VueRouter({
    routes
})

router.beforeEach(async (to, form, next) => {
    /* if (to.path === '/login' || to.path === '/403' || to.path === '/404') {
         return next()
     }*/
    //单点登录逻辑
    //  校验token存在
    // setToken('asddas')
    debugger
    return next()
})


export function getFristMenu() {
    /*  if (!getToken()) {
          return "/login"
      }*/
    return "/system/datasource";
    // return sessionStorage.getItem("firstMenu")
}

export default router


