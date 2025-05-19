<template>
    <div class="win_60_aside">
        <div class="menu-collapse" @click="toggleSideBar">
            <i v-if="!collapse" class="w-icon-d-arrow-left"></i>
            <i v-else class="w-icon-d-arrow-right"></i>
        </div>
        <w-menu
            :default-active="defaultActive"
            router
            theme="light"
            fold-icon="w-icon-arrow-bottom"
            :collapse-transition="false"
            :collapse="collapse"
            :unique-opened="true"
            mode="vertical"
            style="height: 100%; overflow-y: auto; padding-top: 15px"
            :style-config="styleConfig"
        >
            <template
                v-for="menu in menuList"
                style="height: calc(100% - 40px); overflow-y: auto"
            >
                <w-submenu
                    :index="menu.menuPath"
                    v-if="menu.children.length > 0"
                >
                    <template slot="sub-title">
                        <i :class="menu.menuIcon" class="iconfont-menu"></i>
                        <span v-text="menu.menuName"></span>
                    </template>
                    <w-menu-group>
                        <w-menu-item
                            :index="menuItem.menuPath"
                            v-for="menuItem in menu.children"
                            :key="menuItem.menuPath"
                        >
                            {{ menuItem.menuName }}
                        </w-menu-item>
                    </w-menu-group>
                </w-submenu>
                <w-menu-item :index="menu.menuPath" v-else>
                    <i :class="menu.menuIcon" class="iconfont-menu"></i>
                    <span slot="title">{{ menu.menuName }}</span>
                </w-menu-item>
            </template>
        </w-menu>
    </div>
</template>

<script>
import { mapState } from "vuex";

export default {
    props: {
        collapse: {
            type: Boolean,
            default: false,
        },
    },
    data() {
        return {
            styleConfig: {
                background: "#FFFFFF",
                item: {
                    activedTextColor: "#FFFFFF",
                    submenuBackground: "#FFFFFF",
                    textColor: "#000000",
                    activedBackground: "#2D5AFA",
                    hoverBackground: "#2D5AFA",
                },
            },
        };
    },
    computed: {
        ...mapState("main", ["menuList", "defaultActive"]),
    },
    methods: {
        toggleSideBar() {
            this.$emit("update:collapse", !this.collapse);
        },
    },
};
</script>

<style lang="scss">
.win_60_aside {
    border-right: 1px solid #e8e8e8;
    height: 100%;
    position: relative;
    width: 100%;
    z-index: 100;
    .w-menu {
        &.is-light {
            .w-submenu {
                font-size: 14px;
                .w-submenu--title,
                .w-menu-item--content {
                    font-size: 14px;
                }
            }
        }
    }
}
.menu-collapse {
    position: absolute;
    cursor: pointer;
    -webkit-transform: translateY(-50%);
    transform: translateY(-50%);
    top: 50%;
    width: 10px;
    height: 72px;
    right: -10px;
    box-sizing: border-box;
    background: var(--COLOR-NORMAL, #2d5afa);
    border-radius: 0px 100px 100px 0px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    i {
        top: 30px;
        left: 1px;
        position: absolute;
        font-size: 10px;
        color: #ffffff;
    }
}

.w-menu {
    border-right: none;
}

.w-menu-item-group__title {
    display: none;
}

.sider-bar {
    box-sizing: border-box;
    background: var(--COLOR-NORMAL-BG) !important;
    line-height: 30px;
    text-align: center;
    font-size: 30px;
    height: 40px;
    color: black;
}

.w-submenu--title span,
.w-menu.is-vertical > .w-menu-item span {
    margin-left: 5px;
}

.w-menu.is-vertical.is-light .w-menu-item:not(.collapse-menu-item):hover,
.w-menu.is-vertical.is-light > .w-menu-item:hover,
.w-menu.is-vertical.is-light > .w-submenu .w-submenu--content:hover {
    color: #ffffff !important;
}

.w-menu.is-vertical > .w-menu-item,
.w-menu.is-vertical > .w-submenu > .w-submenu--content {
    padding: 0 0 0 20px !important;
    border-radius: 5px;
    width: auto;
    margin: 0 5px !important;
}
.w-menu-group {
    width: auto;
    margin: 0 5px !important;
}
.w-menu.is-vertical .w-menu-item {
    border-radius: 5px;
}
</style>
