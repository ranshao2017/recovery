<template>
    <div class="page-setting">
        <div class="setting-list">
            <i-cell-group>

            </i-cell-group>
        </div>
        <i-button
            i-class="btn-get-code"
            @click="logout"
            type="tika"
        >
            退出登录
        </i-button>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";

    export default {
        methods: {
            logout() {
                wx.clearStorageSync();
                this.$router.push({
                    path: '/pages/index/main',
                    reLaunch: true
                })
            }
        }
    };
</script>

<style lang="less">
    .btn-get-code {
        margin-top: 30px!important;
    }
</style>
