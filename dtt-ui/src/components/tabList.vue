<template>
    <div
        class="tab-list flex align-items-center justify-content-between"
        :class="{ 'no-menu': system == 'noMenu' }"
    >
        <w-tabs
            v-model="tabValue"
            type="card"
            class="demo-tabs"
            clear-icon="w-icon-close"
            @tab-remove="removeTab"
        >
            <w-tab-pane
                v-for="item in tabs"
                :key="item.name"
                :label="item.title"
                :name="item.name"
                :closable="item.title != '首页' && tabs.length > 1"
            >
            </w-tab-pane>
        </w-tabs>
        <w-dropdown
            trigger="click"
            popper-width="200"
            class="tab-dropdown"
            :disabled="tabs.length == 1"
            v-if="system == 'menu'"
        >
            <i class="w-icon-triangle-bottom" style="font-size: 14px"></i>

            <w-dropdown-menu slot="dropdown-menu">
                <w-dropdown-menu-item @click="clearTab('all')"
                >关闭所有
                </w-dropdown-menu-item
                >
                <w-dropdown-menu-item @click="clearTab('left')"
                >关闭左侧
                </w-dropdown-menu-item
                >
                <w-dropdown-menu-item @click="clearTab('right')"
                >关闭右侧
                </w-dropdown-menu-item
                >
            </w-dropdown-menu>
        </w-dropdown>
    </div>
</template>

<script>
import {mapState} from "vuex";

export default {
    data() {
        return {
            tabValue: "",
        };
    },
    props: {
        system: {
            type: String,
            default: "menu",
        },
    },
    computed: {
        ...mapState("main", ["tabs", "tabsValue", "defaultActive", "menuList"]),
    },
    watch: {
        tabValue(val) {
            if (this.$route.path == val) {
                return;
            }
            this.$router.push(val).catch(() => {
            });
            this.$store.commit("main/updateActive", val);
        },
        tabsValue(val) {
            this.tabValue = val;
        },
    },
    created() {
        this.tabValue = this.tabsValue;
    },
    methods: {
        findKeepAlive() {
            //获取keep-alive组件实例
            return this.$vnode.context.$parent.$refs.currRoutePage.$options.parent;
        },
        removeTab(val) {

            this.$store.commit("main/updateTab", {to: val, type: "delete"});
            if (val === this.defaultActive) {
                this.$nextTick(() => {
                    //  删除当前tab页面，页面跳转至第一个tab页面
                    this.$store.commit("main/updateValue", this.tabs[0].name);
                    this.$store.commit("main/updateActive", this.tabs[0].name);
                });
            }

            //清除keepAlive缓存
            // 说明是关闭了路由，不是切换
            const keepAliveInstance = this.findKeepAlive()
            if (!keepAliveInstance) {
                return
            }

            const {cache} = keepAliveInstance
            const cacheKeys = Object.keys(cache)
            const delKey = cacheKeys.find(key => key.includes(val))
            delKey && (cache[delKey] = null)
            Reflect.deleteProperty(cache, delKey)
            // this.$destroy() // 调用组件的销毁
        },
        clearTab(type) {
            let first =
                this.menuList[0].children.length > 0
                    ? this.menuList[0].children[0].menuPath
                    : this.menuList[0].menuPath;
            this.$store.commit("main/clearTab", {
                path: this.$route.path,
                type: type,
            });
            if (type == "all") {
                if (first == this.$route.path) {
                    this.$store.commit("main/updateTab", {
                        to: {path: this.$route.path},
                        type: "add",
                    });
                }
                this.$router.replace(first).catch((err) => {
                    console.log(err);
                });
            }
        },
    },
};
</script>

<style lang="scss">
.tab-list {
    width: 100%;
    background: #ffffff;
    height: 30px;
    box-sizing: border-box;
    border-left: 1px solid #ebebeb;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.demo-tabs {
    width: calc(100% - 60px);
    flex-shrink: 0;

    > .w-tabs__header .w-tabs__item {
        background: #ffffff;
    }

    .w-tabs__header .w-tabs__item.is-active {
        font-weight: 500;
    }

    .w-tabs__nav-next,
    .w-tabs__nav-prev {
        line-height: 30px;
    }

    .w-tabs__header .w-tabs__item {
        border-bottom: none !important;
        border-top: none !important;
        height: 30px;
        line-height: 30px;
        font-size: 12px;
    }

    .w-tabs__header {
        margin: 0;
        border-bottom: none;
    }

    .w-tabs__header .w-tabs__nav {
        border-radius: 0;
        border: none;
        border-right: 1px solid #c9c9c9;
    }

    .w-tabs__nav-wrap {
        margin-bottom: 0;
    }
}

.no-menu {
    border-left: none !important;
    background: var(--COLOR-NORMAL);
    height: 60px;
    padding-left: 20px;

    .demo-tabs > .w-tabs__header .w-tabs__item {
        background: var(--COLOR-PRESS) !important;
        color: var(--COLOR-NORMAL-BG) !important;
        font-size: 16px;
    }

    .w-tabs__header .w-tabs__nav {
        border-right: 1px solid hsla(0, 0%, 100%, 0.3);
        border-left: 1px solid hsla(0, 0%, 100%, 0.3);
    }

    .demo-tabs > .w-tabs__header .w-tabs__item.is-active {
        color: #ffffff !important;
        background-image: linear-gradient(
                180deg,
                var(--COLOR-BTN-TABLE-HEADER-BG, #eaeefe) 0%,
                var(--COLOR-HOVER, #5175f4) 95%
        ) !important;
    }

    .demo-tabs {
        width: 100% !important;
    }

    .w-tabs__nav-next,
    .w-tabs__nav-prev {
        line-height: 60px !important;

        i {
            color: var(--COLOR-PRESS) !important;
        }
    }

    .w-tabs--card > .w-tabs__header .w-tabs__item {
        border-left: 1px solid hsla(0, 0%, 100%, 0.3) !important;
    }

    //.w-tabs__header .w-tabs__nav {
    //  border-right: 1px solid hsla(0,0%,100%,.3)!important;
    //}
    .w-tabs__header .w-tabs__item {
        height: 60px !important;
        line-height: 60px !important;
    }
}

.tab-dropdown {
    height: 100%;
    border-left: 1px solid #c9c9c9;
    border-right: 1px solid #c9c9c9;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 30px;
}
</style>
