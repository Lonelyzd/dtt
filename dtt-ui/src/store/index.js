import Vue from 'vue'
import Vuex from 'vuex'
import main from './main'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        main
    },
    plugins: [
        createPersistedState({
            storage: window.sessionStorage, // 修改存储的状态
            paths: ['main.defaultActive','main.tabs', 'main.tabsValue']
        })
    ] // 状态持久化
})

export default store
