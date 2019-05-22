<template>
    <div class="page-bindCard">
        <i-input @change="change1" :value="username" i-class="red-input" title="姓名" placeholder="请输入姓名"/>
        <i-input @change="change2" :value="card" i-class="red-input" type="number" title="银行卡" placeholder="请输入银行卡号"/>
        <i-input @change="change3" :value="cardBank" i-class="red-input" title="开户行" placeholder="请输入开户行"/>
        <div @click="bind" class="code">
            <div class="txt">确认绑定</div>
        </div>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from "api/apiList";

    export default {
        data() {
            return {
                username: "",
                card: "",
                cardBank: "",
                backFlag: false
            };
        },
        onShow() {
            const bankInfo = wx.getStorageSync("bankInfo");
            if (bankInfo) {
                this.cardBank = bankInfo.bankname;
                this.card = bankInfo.bankno;
                this.username = bankInfo.username;
            }
            this.backFlag = this.$root.$mp.query.back || false;
        },
        methods: {
            change1({ mp }) {
                this.username = mp.detail.detail.value;
            },
            change2({ mp }) {
                this.card = mp.detail.detail.value;
            },
            change3({ mp }) {
                this.cardBank = mp.detail.detail.value;
            },
            async bind() {
                if (this.username === "") {
                    this.$toast("姓名不能为空");
                    return;
                }
                if (this.card === "") {
                    this.$toast("银行卡号不能为空");
                    return;
                }
                if (this.cardBank === "") {
                    this.$toast("开户行不能为空");
                    return;
                }
                let userId = wx.getStorageSync("userId");
                const res = await this.$post(API.addBank, {
                    bankname: this.cardBank,
                    bankno: this.card,
                    username: this.username
                });
                if (res.status !== 200) return;
                wx.setStorageSync("bindFlag", true);
                wx.setStorageSync("bankInfo", {
                    bankname: this.cardBank,
                    bankno: this.card,
                    username: this.username
                })
                if(this.backFlag) {
                    this.$router.go()
                    return
                }
                this.$router.replace({
                    path: "/pages/withdrawal/main"
                });
            }
        }
    };
</script>

<style lang="less">
    @import "../../styles/mixin";

    .page-bindCard {
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
