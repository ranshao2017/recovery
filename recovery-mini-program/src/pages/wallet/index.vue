<template>
    <div class="page-wallet">
        <div class="icon">
            <img mode="widthFix" src="../../assets/imgs/icon_wallet.png" alt="">
        </div>
        <div class="title">可提现余额</div>
        <div class="money">{{money}}<span>元</span></div>
        <div class="btn">
            <div @click="goWalletDetail" class="btn-walletDetail">查看明细</div>
            <!--<div @click="goWithDraw" class="btn-walletDetail withdrawal">提现</div>-->
            <div @click="goWithDrawWx" class="btn-walletDetail withdrawal">提现</div>
        </div>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from 'api/apiList'

    export default {
        data() {
            return {
                money: '0.00'
            }
        },
        onLoad() {
            this.money = wx.getStorageSync('userInfo').current_profit;
        },
        onShow() {
            console.log(123);
            this.getUserInfo()
        },
        methods: {
            async getUserInfo() {
                let userId = wx.getStorageSync("userId");
                const res = await this.$get(API.getUserInfo, { userId });
                if (res.status !== 200) return;
                wx.setStorageSync("userInfo", res.result);
                this.greenGold = res.result.total_profit;
                this.count = res.result.deliver_count;
                wx.setStorageSync("bindFlag", res.result.banks && res.result.banks.length || false);
                wx.setStorageSync("bankInfo",res.result.banks[0])
                this.money = res.result.current_profit;
            },
            goWalletDetail() {
                this.$router.push({
                    path: '/pages/walletDetail/main'
                })
            },
            goWithDraw() {
                const bindFlag = wx.getStorageSync('bindFlag');
                if(bindFlag) {
                    this.$router.push({
                        path: '/pages/withdrawal/main'
                    })
                }else {
                    this.$router.push({
                        path: '/pages/bindCard/main'
                    })
                }
            },
            goWithDrawWx() {
                this.$router.push({
                    path: '/pages/withdrawal/main'
                })
            }
        }
    };
</script>

<style scoped lang="less">
    @import "../../styles/mixin";

    .page-wallet {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding-top: 90px;
        .icon {
            width: 90px;
            height: 90px;
            display: flex;
            align-items: flex-end;
            justify-content: center;
            img {
                width: 60px;
            }
        }
        .title {
            font-size: @fs16;
            margin: 20px 0 4px;
        }
        .money {
            font-size: 40px;
            color: #000;
            font-weight: bold;
            span {
                font-size: @fs16;
                margin-left: 6px;
            }
        }
        .btn {
            display: flex;
            justify-content: space-around;
        }
        .btn-walletDetail {
            width: 120px;
            height: 36px;
            border: 1rpx solid @mainColor;
            border-radius: 20px;
            text-align: center;
            line-height: 36px;
            color: @mainColor;
            margin-top: 30px;
            font-size: @fs16;
            overflow: hidden;
            &:active {
                background: @mainColor;
                color: #fff;
            }
            &.withdrawal {
                margin-left: 10px;
            }
        }
    }
</style>
