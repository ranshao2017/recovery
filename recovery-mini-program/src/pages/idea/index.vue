<template>
    <div class="page-idea">
        <div class="title">
            问题类型
        </div>
        <div class="content">
            <div class="type-list">
                <div @click="toggleType(item.id)" :class="currentIdeaType === item.id ? 'active': ''" :key="index" v-for="(item, index) in typeList"
                     class="type-item">
                    {{item.name}}
                </div>
            </div>
        </div>
        <div class="title">
            反馈内容
        </div>
        <div class="content">
            <textarea v-model="content" placeholder="请输入反馈内容"></textarea>
        </div>
        <div class="title">
            联系方式
        </div>
        <div class="content">
            <div class="wrapper-input">
                <input maxlength="11" type="number" v-model="tel" placeholder="请输入电话号码">
            </div>
        </div>
        <i-button
            i-class="btn-get-code"
            @click="submitIdea"
            type="tika"
        >
            提交反馈
        </i-button>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from "api/apiList";

    export default {
        data() {
            return {
                typeList: [
                    {
                        name: "建议",
                        id: 0
                    },
                    {
                        name: "吐槽",
                        id: 1
                    },
                    {
                        name: "其他",
                        id: 9
                    }
                ],
                currentIdeaType: 0,
                content: "",
                tel: ""
            };
        },
        methods: {
            async submitIdea() {
                const userId = wx.getStorageSync('userId');
                if(!this.content) {
                    this.$toast('反馈内容不能为空')
                    return
                }
                if(!/^1[34578]\d{9}$/.test(this.tel)) {
                    this.$toast('请填写正确手机号码')
                    return
                }
                const res = await this.$post(API.addIdea, {
                    type: this.currentIdeaType,
                    description: this.content,
                    phone: this.tel,
                    userId
                });
                console.log(res);
                if(res.status !== 200)  return;
                this.$toast(res.message);
                this.$router.go()
                Object.assign(this.$data, this.$options.data())
            },
            toggleType(id) {
                this.currentIdeaType = id;
            }
        }
    };
</script>

<style lang="less">
    @import "../../styles/mixin";

    .page-idea {
        min-height: 100vh;
        background: #f7f7f7;
        .title {
            background: #f7f7f7;
            font-size: 14px;
            padding: 4px 15px;

        }
        .content {
            padding: 0 15px;
            background: #fff;
            textarea {
                font-size: 14px;
                padding: 10px 0;
            }
            .wrapper-input {
                padding: 10px 0;
            }
            input {
                font-size: 14px;
            }
        }
        .btn-get-code {
            margin-top: 80px;
        }
        .type-list {
            display: flex;
            padding: 15px 0;
            .type-item {
                padding: 2px 6px;
                font-size: 14px;
                border-radius: 4px;
                border: 1px solid #d7d7d7;
                margin-right: 20px;
                &.active {
                    border-color: @mainColor
                }
            }
        }
    }
</style>
