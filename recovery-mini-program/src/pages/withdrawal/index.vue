<template>
    <div class="page-withdrawal">
        <i-input @change="change" i-class="red-input" :value="money" type="number" title="提现金额"
                 placeholder="请输入提现金额, 10或以上整数"/>
        <div @click="withdrawal" class="code">
            <div class="txt">确认提现</div>
        </div>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from "api/apiList";

    export default {
        data() {
            return {
                money: 11
            };
        },
        methods: {
            change({ mp }) {
                this.money = mp.detail.detail.value;
            },
            async withdrawal() {
                console.log(this.money);
                if (this.money === "") {
                    this.$toast("金额不能为空");
                    return;
                }

                if (this.money < 10) {
                    this.$toast("金额必须为10或以上整数");
                    return;
                }
                // const res = await this.$post(API.withdrawal, {
                //     amount: this.money
                // })
                // if (res.status !== 200) return;
                // console.log(res);
                // this.$toast(res.message)
                // this.$router.go()
                // console.log(API.wxWithDraw);
                const res = await this.$post(API.wxWithDraw, {
                    amount: this.money
                });
                if (res.status === 10018) {
                    wx.login({
                        success: async result => {
                            if (result.code) {
                                const res_bak = await this.$post(API.getOpenId, {
                                    code: result.code
                                });
                                if (res_bak.status === 200) {
                                    this.withdrawal();
                                }
                            }
                        }
                    });
                } else {
                    this.$toast(res.message)
                    this.$router.go()
                }

            }
        }
    };
</script>

<style lang="less">
    @import "../../styles/mixin";

    .page-withdrawal {
        padding: 15px;
        box-sizing: border-box;
        .red-input {
            /*height: 40px!important;*/
            margin: 10px 0;
        }
        .code {
            padding: 10px 20px;
            /*background: rgba(245, 215, 57, 0.8);*/
            background: @mainColor;
            /*background: linear-gradient(rgba(245, 215, 57, 0.8), rgba(245, 215, 57, 1));*/
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 20px auto 0;
            width: 120px;
            border-radius: 30px;
            box-shadow: 0 0 4px rgba(0, 0, 0, .1);
            color: #fff;
            &:active {
                opacity: .8;
            }
            .txt {
                font-size: @fs16;
                margin-left: 6px;
            }
        }
    }
</style>
