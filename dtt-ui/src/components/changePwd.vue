<template>
    <el-modal
        :visible.sync="pwdVisible"
        title="修改密码"
        width="600px"
        color
        :close-on-click-modal="false"
        :close-on-press-escape="showClose"
        :show-close="showClose"
        @close="close()"
    >
        <w-form :model="form" ref="form" label-width="100px" :rules="rules" size="large" label-align="right">
            <w-form-item label="当前密码" prop="password">
                <w-input v-model="form.password" show-password placeholder="请输入"></w-input>
            </w-form-item>
            <w-form-item label="新密码" prop="newPassword">
                <w-input v-model="form.newPassword" show-password placeholder="请输入"></w-input>
            </w-form-item>
        </w-form>
        <span slot="footer" class="dialog-footer">
        <w-button @click="close" v-if="showClose">取 消</w-button>
        <w-button style="margin-left: 16px" type="primary" @click="changePwd">确 定</w-button>
    </span>

    </el-modal>
</template>

<script>
import {setToken} from "@/util/auth";
import {changePassword} from "@/api/user";
import jsencrypt from "@/util/jsencrypt";
import Store from "@/store";
import {Message} from "win-design";
import {getFristMenu} from "@/router";

export default {
    name: "changePwd",
    props: {
        pwdVisible: {
            type: Boolean,
            default: false
        },
        showClose: {
            type: Boolean,
            default: true
        },
        userCode: {
            type: String
        }
    },
    data() {
        const validatePassword = (rule, value, callback) => {
            if ((value.toString()).length < 6) {
                callback(new Error('密码长度不能少于6位！'))
            } else {
                callback()
            }
        }
        return {

            form: {
                password: '',
                newPassword: '',
            },
            rules: {
                password: [{required: true, message: "请输入当前密码", trigger: "blur"},
                    {required: true, trigger: 'blur', validator: validatePassword}],
                newPassword: [
                    {required: true, message: "请输入新密码", trigger: "blur"},
                    {required: true, trigger: 'blur', validator: validatePassword}
                ],
            },
        }
    },
    watch: {
        pwdVisible() {
            this.$nextTick(() => {
                this.$refs.form.resetFields()
            })
        }
    },
    methods: {
        close() {
            this.$emit('update:pwdVisible', false)
        },
        changePwd() {
            this.$refs.form.validateForm(valid => {
                if (valid) {
                    changePassword({
                        userCode: this.userCode,
                        password: jsencrypt.encrypt(this.form.password),
                        newPassword: jsencrypt.encrypt(this.form.newPassword),
                    }).then((res) => {
                        //res.data.status  异常状态的判断 0正常登陆 1强制登陆 2密码过期自愿修改 3密码过期强制修改
                        let token = res.data?.tokenValue;
                        let status = res.code;
                        // let { data: {tokenValue: token},code:status } = res;

                        setToken(token)
                        if (status === 200) {
                            //登录成功
                            Store.dispatch('main/getMenu')
                            this.$store.dispatch('main/getInfo').then(() => {
                                // let firstMenu = getFristMenu();
                                this.$Message.success('密码修改成功')
                                this.close()
                                // this.$router.replace(firstMenu)
                            })
                        } else {
                            // 登录失败
                            // this.$Confirm(res.message, "提示", {type: 'warning'})
                            Message.error(res.message || '未知错误')
                        }
                    })
                }
            })
        }
    }
}
</script>

<style scoped>

</style>
