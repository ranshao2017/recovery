<template>
    <div class="page-ideaList">
        <div v-if="ideaList.length"  class="idea-list">
            <div class="idea-item" :key="index" v-for="(item, index) in ideaList">
                <div class="title">
                    <div class="type">{{item.type}}</div>
                    <div class="time">{{item.createTime}}</div>
                </div>
                <div class="content">
                    {{item.description}}
                </div>
            </div>
        </div>
        <div v-else class="empty">
            暂无反馈记录
        </div>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from "api/apiList";

    export default {
        onLoad() {
            this.getIdeaList();
        },
        methods: {
            async getIdeaList() {
                let userId = wx.getStorageSync("userId");
                const res = await this.$get(API.ideaList, {
                    userId
                });
                console.log(res);
                if (res.status !== 200) return;
                this.ideaList = [...res.result.data];
            }
        },
        data() {
            return {
                ideaList: []
            };
        }
    };
</script>

<style lang="less">
    .empty {
        width: 100vw;
        height: 100vh;
        display: flex;
        justify-content: center;
        padding-top: 200px;
        font-size: 16px;
    }
    .page-ideaList {
        width: 100vw;
        min-height: 100vh;
        background: #f2f2f2;
        .idea-list {
            padding: 15px;
            font-size: 14px;
            .idea-item {
                padding: 10px;
                background: #fff;
                border-radius: 4px;
                margin-bottom: 10px;
                position: relative;
                .title {
                    display: flex;
                    justify-content: space-between;
                    margin-bottom: 6px;
                    .type {
                        font-weight: bold;
                    }
                }
            }
        }

    }
</style>
