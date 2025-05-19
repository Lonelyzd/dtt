<template>
    <div class="header">
        <div style="display: flex; align-items: center; height: 100%">
            <div class="logo-img">
                <img src="../assets/logo.png" @click="jump"/>
                <span>互联互通标准化软件&nbsp;| {{ organization }}</span>
            </div>
        </div>
        <div
            style="
                display: flex;
                align-items: center;
                flex-shrink: 0;
                margin-left: 20px;
            "
        >
            <el-popover
                trigger="click"
                placement="bottom"
                style="font-size: 0"
                :visible-arrow="true"
            >
                <i class="w-icon-flow header_icon" slot="reference"></i>
                <div>
                    <div class="portal" @click="clearCache">
                        <el-iconx
                            key="1"
                            name="w-iconx-jd"
                        />&nbsp;&nbsp;清除后台缓存
                    </div>
                    <div class="portal" @click="organInfo">
                        <w-iconx
                            key="2"
                            name="w-iconx-yy"
                        />&nbsp;&nbsp;机构信息维护
                    </div>
                </div>
            </el-popover>
            <el-popover
                trigger="click"
                placement="bottom"
                style="font-size: 0"
                :visible-arrow="true"
            >
                <i class="w-icon-skin header_icon" slot="reference"></i>
                <el-radio-group v-model="theme">
                    <div>
                        <div
                            style="
                                border: 1px #dedede solid;
                                padding: 10px 20px;
                                border-radius: 5px;
                                display: flex;
                                justify-content: space-between;
                                margin: 0 20px 20px 0;
                                min-width: 200px;
                            "
                            v-for="(theme, index) in themes"
                            :key="index"
                        >
                            <el-radio :label="index">{{ theme.label }}</el-radio>
                            <div
                                :style="{ background: theme['--COLOR-NORMAL'] }"
                                style="height: 15px; width: 15px"
                            ></div>
                        </div>
                    </div>
                </el-radio-group>
            </el-popover>
            <el-dropdown
                trigger="click"
                popper-width="200"
                placement="bottom"
                :visible-arrow="true"
            >
                <div
                    style="display: flex; align-items: center; cursor: pointer"
                >
                    <el-avatar
                        name="w-avatar-admin-drk"
                        style="
                            width: 32px;
                            height: 32px;
                            border-radius: 50%;
                            margin: 0 8px 0 0;
                        "
                        shape="square"
                    ></el-avatar>
                    <div class="timeSpan">{{ user.name }}</div>
                </div>
                <el-dropdown-menu slot="dropdown-menu">
                    <el-dropdown-menu-item @click="pwdVisible = true"
                    >修改密码
                    </el-dropdown-menu-item
                    >
                </el-dropdown-menu>
                <el-dropdown-menu slot="dropdown-menu">
                    <el-dropdown-menu-item @click="outLogin"
                    >退出登录
                    </el-dropdown-menu-item
                    >
                </el-dropdown-menu>
            </el-dropdown>
        </div>
        <el-modal
            :visible.sync="organVisible"
            title="医疗机构信息维护"
            :close-on-click-modal="false"
            width="600px"
            top="100px"
            color
        >
            <w-form
                :model="orgInfo"
                label-width="100px"
                :rules="rules"
                ref="form"
            >
                <w-form-item label="医疗机构id" prop="userid" v-show="false">
                    <w-input v-model="orgInfo.id" placeholder=""></w-input>
                </w-form-item>
                <w-form-item label="医疗机构代码" prop="yljgdm">
                    <w-input
                        v-model="orgInfo.yljgdm"
                        :maxlength="20"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="组织机构代码" prop="yljgzzjgdm">
                    <w-input
                        v-model="orgInfo.yljgzzjgdm"
                        :maxlength="10"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="医疗机构名称" prop="yljgzzjgmc">
                    <w-input
                        v-model="orgInfo.yljgzzjgmc"
                        :maxlength="25"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="医院联系电话" prop="yljglxdh">
                    <w-input
                        v-model="orgInfo.yljglxdh"
                        :maxlength="15"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="医院法人姓名" prop="yljgfzrxm">
                    <w-input
                        v-model="orgInfo.yljgfzrxm"
                        :maxlength="20"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="医院法人电话" prop="yljgfzrlxdh">
                    <w-input
                        v-model="orgInfo.yljgfzrlxdh"
                        :maxlength="20"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="地址-省" prop="dzS">
                    <w-input
                        v-model="orgInfo.dzS"
                        :maxlength="20"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="地址-市" prop="dzCs">
                    <w-input
                        v-model="orgInfo.dzCs"
                        :maxlength="20"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="地址-区" prop="dzXq">
                    <w-input
                        v-model="orgInfo.dzXq"
                        :maxlength="20"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="地址-街道" prop="dzXz">
                    <w-input
                        v-model="orgInfo.dzXz"
                        :maxlength="20"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="地址-村" prop="dzC">
                    <w-input
                        v-model="orgInfo.dzC"
                        :maxlength="20"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="地址-门牌号" prop="dzMphm">
                    <w-input
                        v-model="orgInfo.dzMphm"
                        :maxlength="20"
                        clearable
                    ></w-input>
                </w-form-item>
                <w-form-item label="邮政编码" prop="yzbm">
                    <w-input
                        v-model="orgInfo.yzbm"
                        :maxlength="6"
                        clearable
                    ></w-input>
                </w-form-item>
            </w-form>

            <span slot="footer" class="dialog-footer">
                <w-button @click="organVisible = false">取 消</w-button>
                <w-button
                    style="margin-left: 16px"
                    type="primary"
                    @click="saveOrgInfo"
                >保 存</w-button
                >
            </span>
        </el-modal>

        <change-pwd
            :userCode="userCode"
            :pwdVisible.sync="pwdVisible"
        ></change-pwd>
    </div>
</template>

<script>
import TabList from "./tabList.vue";
import changePwd from "./changePwd.vue";
import {mapState} from "vuex";
import Store from "@/store";

export default {
    components: {TabList, changePwd},
    data() {
        return {
            theme: 0,
            organVisible: false,
            themes: [],
            pwdVisible: false,
            userCode: null,
            organization: "organization",
            orgInfo: {
                dzC: null,
                dzCs: null,
                dzMphm: null,
                dzS: null,
                dzXq: null,
                dzXz: null,
                gxsj: null,
                pcxh: null,
                sjcqsj: null,
                xh: null,
                yljgdm: null,
                yljgfzrlxdh: null,
                yljgfzrxm: null,
                yljglxdh: null,
                yljgzzjgdm: null,
                yljgzzjgmc: null,
                yzbm: null,
            },
            rules: {
                yljgdm: [
                    {
                        required: true,
                        message: "请输入医疗机构代码",
                        trigger: "blur",
                    },
                ],
                yljgzzjgdm: [
                    {
                        required: true,
                        message: "请输入组织机构代码",
                        trigger: "blur",
                    },
                    {
                        pattern: /^[\w-]{10}$/,
                        message: "组织机构代码固定长度10",
                        trigger: "blur",
                    },
                ],
                yljgzzjgmc: [
                    {
                        required: true,
                        message: "请输入医疗机构名称",
                        trigger: "blur",
                    },
                ],
                yljglxdh: [
                    {
                        required: true,
                        message: "请输入医疗机构联系电话",
                        trigger: "blur",
                    },
                ],
                yljgfzrxm: [
                    {
                        required: true,
                        message: "请输入医院法人姓名",
                        trigger: "blur",
                    },
                ],
                yljgfzrlxdh: [
                    {
                        required: true,
                        message: "请输入医院法人电话",
                        trigger: "blur",
                    },
                ],
                dzS: [
                    {
                        required: true,
                        message: "请输入医院地址-省",
                        trigger: "blur",
                    },
                ],
                dzCs: [
                    {
                        required: true,
                        message: "请输入医院地址-市",
                        trigger: "blur",
                    },
                ],
                dzXq: [
                    {
                        required: true,
                        message: "请输入医院地址-区",
                        trigger: "blur",
                    },
                ],
                dzXz: [
                    {
                        required: true,
                        message: "请输入医院地址-街道",
                        trigger: "blur",
                    },
                ],
                dzC: [
                    {
                        required: true,
                        message: "请输入医院地址-村",
                        trigger: "blur",
                    },
                ],
                dzMphm: [
                    {
                        required: true,
                        message: "请输入医院地址-门牌号",
                        trigger: "blur",
                    },
                ],
                yzbm: [
                    {
                        required: true,
                        message: "请输入医院邮政编码",
                        trigger: "blur",
                    },
                ],
            },
        };
    },
    props: {
        system: {
            type: String,
            default: "menu",
        },
    },
    watch: {
        theme: function (newVal, oldVal) {
            this.changeThemes();
        },
    },
    created() {
        this.theme = (localStorage.getItem("theme") || 0) * 1;
        this.themes = themes;
        if (!Store.state.main.user.code) {
            Store.dispatch("main/getInfo")
                .then(() => {
                    this.userCode = Store.state.main.user.code;
                })
                .catch((error) => {
                });
        } else {
            this.userCode = Store.state.main.user.code;
        }

        getConfigByCfgCode({key: "tss.show.organization"}).then((res) => {
            this.organization = res.data.cfgValue;
        });
    },
    computed: {
        ...mapState("main", ["user"]),
    },
    methods: {
        outLogin() {
            logoutApi().then(() => {
                clearAll();
                window.location.href = window.location.origin;
            });
        },
        changeThemes() {
            const _THEME_VARS = [
                "--COLOR-NORMAL",
                "--COLOR-NORMAL-RGB",
                "--COLOR-HOVER",
                "--COLOR-PRESS",
                "--COLOR-NORMAL-BG",
                "--COLOR-ACTIVE-BG",
            ];

            let _THEMES = this.themes[this.theme];

            _THEME_VARS.forEach((key) => {
                // 在 html 节点上添加 CSS 变量
                document.documentElement.style.setProperty(key, _THEMES[key]);
                localStorage.setItem("theme", this.theme);
            });
        },
        clearCache() {
            removeUserPermission();
            clearCackCache().then((res) => {
                this.$Message.success("缓存清除成功");
            });
        },
        organInfo() {
            this.organVisible = true;
            getOrganInfo().then((res) => {
                if (res?.data) {
                    this.orgInfo = res?.data;
                }
            });
        },
        saveOrgInfo() {
            this.$refs.form.validateForm((valid) => {
                if (valid) {
                    saveOrgan(this.orgInfo).then((res) => {
                        this.$Message.success("更新成功");
                        this.organVisible = false;
                    });
                }
            });
        },
        jump() {
            let url = sessionStorage.getItem(SSO_PORTAL_URL);
            if (url) {
                // window.location.href = url
                window.open(url,"_self")
            }
        }
    },
};
</script>

<style scoped lang="scss">
.header {
    display: flex;
    align-items: center;
    height: 100%;
    justify-content: space-between;
    background: var(--COLOR-NORMAL);

    .header_icon {
        margin-right: 12px;
        color: #ffffff;
        cursor: pointer;
        font-size: 18px;
    }
}

.logo-img {
    height: 100%;
    padding: 0px 15px;
    background: var(--COLOR-NORMAL);
    box-sizing: border-box;
    cursor: pointer;
    display: flex;
    align-items: center;

    img {
        height: 29px;
        width: 55px;
        margin-right: 10px;
    }

    span {
        font-size: 18px;
        font-weight: 600;
        color: #ffffff;
    }
}

.timeSpan {
    font-size: 16px;
    color: #ffffff;
    margin-right: 15px;
}

.portal {
    padding: 5px;
    border: 1px solid #dedede;
    border-radius: 5px;
    margin-bottom: 5px;
    cursor: pointer;
    display: flex;
    align-items: center;

    img {
        margin: 0 5px 0 10px;
    }
}
</style>
