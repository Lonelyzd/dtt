// state

const state = {
    menuList: null,
    defaultActive: '',
    tabs: [],
    tabsValue: '',
    user: {},
    config:true,
    orgInfo: null
}
// mutations
const mutations = {
    updateActive(state, val) {
        state.defaultActive = val
    },
    updateTab(state, val) {
        let path = val.to
        let type = val.type
        if (path.path === '/' || path.path === '/login' || path.path === '/main' || path.path === '/home') {
            state.tabs = []
            return
        }
        let arr = {}
        let menu = state.menuList || []
        menu.forEach(item => {
            if (item.menuPath) arr[item.menuPath] = item.menuName
            item.children.forEach(chil => {
                if (chil.menuPath) arr[chil.menuPath] = chil.menuName
            })
        })
        if (type === 'add') {
            //  不在menuList中的菜单不添加到tabs和tabValue
            if ((state.tabs.filter(item => item.name == path.path).length === 0) && arr[path.path]) {
                state.tabs.push({
                    title: arr[path.path],
                    name: path.path
                })
            }
            if (arr[path.path]) {
                this.commit('main/updateValue', path.path)
                this.commit('main/updateActive', path.path)
            }
        } else {
            state.tabs = state.tabs.filter(item => item.name != path)
        }
    },
    clearTab(state, {path, type}) {
        //     path 当前页面路由
        //    type 关闭标签类型，all：关闭所有tabs置空主动跳转首页，left：关闭左侧并保留首页，right：关闭右侧页面
        let num
        if (type == 'all') {
            state.tabs = []
            return
        }
        state.tabs.forEach((item, index) => {
            if (item.name == path) num = index
        })
        if (type == 'left') {
            state.tabs = state.tabs.filter((item, index) => (index >= num))
        }
        if (type == 'right') {
            state.tabs = state.tabs.filter((item, index) => (index <= num))
        }
    },
    updateValue(state, val) {
        state.tabsValue = val
    },
    updateMenu(state, val) {
        state.menuList = val
    },
    updateUser(state, val) {
        state.user = val
    },
    updateOrgInfo(state, val) {
        state.orgInfo = val
    }
}
// getters
const getters = {}

//function

const getArray = function (array) {
}

// actions
const actions = {
    getMenu(context, vm) {
        debugger
        //    动态获取menu数据
        return new Promise((resolve, reject) => {
            /*if (!getToken()) {
                return router.replace('/login')
            }*/debugger
            if (!context.state.menuList) {
             let menu=   [{
                    "menuId": 1,
                    "menuName": "系统",
                    "menuPath": "/system",
                    "enable": 1,
                    "sort": 1,
                    "parentId": null,
                    "menuType": null,
                    "menuPower": null,
                    "menuIcon": "w-icon-home",
                    "children": [
                        {
                            "menuId": 101,
                            "menuName": "数据源",
                            "menuPath": "/system/datasource",
                            "enable": 1,
                            "sort": 1,
                            "parentId": 1,
                            "menuType": null,
                            "menuPower": null,
                            "menuIcon": "w-icon-bar-chart",
                            "children": [],
                            "menuLevel": 2,
                            "parentMenuName": "监控概览"
                        }
                    ],
                    "menuLevel": 1
                }]
                //菜单信息保存到store
                context.commit('updateMenu', menu);
                //首页信息保存    到sessionStorage
                let reduce = menu.reduce((res, item) => {
                    const {children, ...i} = item
                    return res.concat(children && children.length ? children : [])
                }, []);

                let firstMenu = reduce[0]?.menuPath;
                sessionStorage.setItem("firstMenu", firstMenu)
                resolve(menu)
            }else {
                let reduce = context.state.menuList.reduce((res, item) => {
                    const {children, ...i} = item
                    return res.concat(children && children.length ? children : [])
                }, []);

                let firstMenu = reduce[0]?.menuPath;
                sessionStorage.setItem("firstMenu", firstMenu)

                resolve(context.state.menuList)
            }
        })

    },


}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}

