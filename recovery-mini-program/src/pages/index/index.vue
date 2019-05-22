<template>
    <div class="page-index">
        <swiper autoplay class="swiper-content">
            <swiper-item :key="index" v-for="(item, index) in bannerList" class="swiper-slide">
                <div class="swiper-slide-img-wrapper">
                    <image mode="aspectFit" class="slide-image" :src="item.url"></image>
                </div>
            </swiper-item>
        </swiper>
        <div class="nav-list">
            <div @click="navItem(index)" class="nav-item" v-for="(item, index) in navList" :key="index">
                <img :class="'img'+index" mode="widthFix" :src="item.icon" alt="">
                <h4>{{item.title}}</h4>
            </div>
        </div>
        <div v-if="!loginFlag" class="goLogin">
            <div class="btn" @click="goLogin">
                登录/注册
            </div>
        </div>
        <div class="card-list">
            <div class="card-item card-green-gold">
                <div class="title">
                    <h5>我的环保金</h5>
                    <p>{{greenGold}} <span>元</span></p>
                </div>
                <div @click="goWallet" class="link">我的钱包
                    <div class="icon-wrapper">
                        <i-icon color="#666" type="enter"/>
                    </div>
                </div>
            </div>
            <div class="card-item card-count">
                <div class="title">
                    <h5>累计投递</h5>
                    <p>{{count}} <span>次</span></p>
                </div>
                <div @click="goDelivery" class="link">查看记录
                    <div class="icon-wrapper">
                        <i-icon color="#666" type="enter"/>
                    </div>
                </div>
            </div>
        </div>
        <div @click="code" class="code">
            <div class="icon-wrapper">
                <i-icon size="20" color="#fff" type="scan"/>
            </div>
            <div class="txt">生成二维码投递</div>
        </div>
        <i-modal title="授权提示" :visible="visible" :show-cancel="false" :show-ok="false">
            <view style="padding: 0 15px">附近回收机需要获取当前地址位置，请点击授权</view>
            <button @click="settingClick" class="btn-setting" open-type="openSetting">授权</button>
        </i-modal>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from "api/apiList";


    export default {
        data() {
            return {
                navList: [{
                    title: "附近回收机",
                    icon: require("../../assets/imgs/icon_near.png")
                }, {
                    title: "投递指南",
                    icon: require("../../assets/imgs/icon_guide.png")
                }, {
                    title: "大件上门回收",
                    icon: require("../../assets/imgs/icon_take.png")
                }, {
                    title: "个人中心",
                    icon: require("../../assets/imgs/icon_user.png")
                }],
                greenGold: "0.00",
                count: 0,
                bannerList: [],
                loginFlag: false,
                visible: false,
                hasClick: false
            };
        },
        async onLoad() {
            this.getBannerList();

        },
        onShow() {
            if (!wx.getStorageSync("isLogin")) {
                this.greenGold = "0.00";
                this.count = 0;
                this.loginFlag = false;
                return;
            }
            this.loginFlag = true;
            this.getUserInfo();

        },
        methods: {
            settingClick() {
                this.visible = false;
            },
            goLogin() {
                this.$router.push({
                    path: "/pages/login/main"
                });
            },
            // 获取个人信息
            async getUserInfo() {
                if (this.checkLogin()) return;
                let userId = wx.getStorageSync("userId");
                const res = await this.$get(API.getUserInfo, { userId });
                if (res.status !== 200) return;
                wx.setStorageSync("userInfo", res.result);
                this.greenGold = res.result.current_profit;
                this.count = res.result.deliver_count;
                wx.setStorageSync("bindFlag", res.result.banks && res.result.banks.length || false);
                wx.setStorageSync("bankInfo", res.result.banks[0]);
            },
            // banner
            async getBannerList() {
                const res = await this.$get(API.banner);
                console.log(res);
                if (res.status !== 200) return;
                this.bannerList = res.result.list || [];
            },
            code() {
                if (this.checkLogin()) return;
                this.$router.push({
                    path: "/pages/qrcode/main"
                });

            },
            closeQcode() {
                this.qcodeShowFlag = false;
                // this.qrcode = null;
            },
            checkLogin() {
                if (wx.getStorageSync("isLogin")) return false;
                this.$router.push({
                    path: "/pages/login/main"
                });
                return true;
            },
            navItem(index) {
                switch (index) {
                    case 0:
                        // wx.getLocation({
                        //     success: res => {
                        //         console.log(res);
                        //         this.visible = false;
                        //         this.$router.push({
                        //             path: `/pages/near/main?latitude=${res.latitude}&longitude=${res.longitude}`
                        //         });
                        //         // wx.chooseLocation({
                        //         //     success: res => {
                        //         //         console.log(res);
                        //         //         this.$router.push({
                        //         //             path: `/pages/near/main?latitude=${res.latitude}&longitude=${res.longitude}`
                        //         //         });
                        //         //     }
                        //         // });
                        //     },
                        //     fail: err => {
                        //         this.visible = true;
                        //     }
                        // });

                        this.$toast("当前城市尚未开通服务，敬请期待");
                        // this.$router.push({
                        //     path: "/pages/near/main"
                        // });
                        break;
                    case 1:
                            this.$router.push({
                                path: "/pages/guide/main"
                            });
                        // this.$toast("当前城市尚未开通服务，敬请期待");
                        break;
                    case 2:
                        this.$toast("当前城市尚未开通服务，敬请期待");
                        break;
                    case 3:
                        if (this.checkLogin()) return;
                        this.$router.push({
                            path: "/pages/user/main"
                        });
                        break;
                }
            },
            goWallet() {
                if (this.checkLogin()) return;
                this.$router.push({
                    path: "/pages/wallet/main"
                });
            },
            goDelivery() {
                if (this.checkLogin()) return;
                this.$router.push({
                    path: "/pages/delivery/main"
                });
            }
        }
    };
</script>

<style lang="less">
    @import "../../styles/mixin";

    .page-index {
        padding-top: 10px;
        .btn-setting {
            width: 100%;
            background: #fff;
            border: none;
            position: relative;
            bottom: -15px;
            border-top: 1 rpx solid #d7d7d7;
            border-radius: 0;
            &:after, &:before {
                border: none;
            }
        }
        .swiper-content {
            height: 150px;
            .swiper-slide {
                .swiper-slide-img-wrapper {
                    width: 93%;
                    height: 150px;
                    border-radius: 6px;
                    overflow: hidden;
                    margin: 0 auto;
                }
                .slide-image {
                    width: 100%;
                    height: 150px;
                }
            }

        }
        .nav-list {
            display: flex;
            padding: 10px 0;
            .nav-item {
                flex: 1;
                text-align: center;
                h4 {
                    font-size: @fs14;
                    text-align: center;
                    color: @fontColor3;
                }
                img {
                    width: 32px;
                    /*height: 32px;*/
                    display: block;
                    margin: 0 auto 10px;
                }
                .img0 {
                    width: 24px;
                }
                .img1 {
                    width: 31px;
                }
                .img2 {
                    width: 43px;
                }
                .img3 {
                    width: 28px;
                }
            }
        }
        .goLogin {
            margin: 10px 0;
            display: flex;
            justify-content: center;
            .btn {
                width: 160px;
                height: 45px;
                border-radius: 23px;
                background: @mainColor;
                /*background: linear-gradient(rgba(245, 215, 57, 0.8), rgba(245, 215, 57, 1));*/
                /*box-shadow: 0 0 4px rgba(0, 0, 0, .1);*/
                text-align: center;
                line-height: 45px;
                color: #fff;
                &:active {
                    opacity: .8;
                }
            }
        }
        .icon-wrapper {
            position: relative;
            top: -1px;
        }
        .card-list {
            .card-item {
                box-shadow: 0 0 4px rgba(0, 0, 0, .1);
                width: 93%;
                margin: 20px auto 0;
                border-radius: 6px;
                padding: 15px;
                display: flex;
                position: relative;
                .title {
                    h5 {
                        font-size: @fs14;
                        color: @fontColor6;
                    }
                    p {
                        font-size: @fs30;
                        color: #000;
                        margin-top: 4px;
                        font-weight: bold;
                        span {
                            font-size: @fs16;
                        }
                    }
                }
                .link {
                    position: absolute;
                    top: 50%;
                    right: 15px;
                    transform: translate(0, -50%);
                    font-size: @fs14;
                    color: @fontColor9;
                    border: 1 rpx solid @fontColor9;
                    padding: 4px 10px;
                    border-radius: 20px;
                    display: flex;
                    align-items: center;
                    &:active {
                        opacity: .8;
                    }
                }
            }
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
            width: 220px;
            border-radius: 30px;
            box-shadow: 0 0 4px rgba(0, 0, 0, .1);
            color: #fff;
            position: fixed;
            bottom: 10px;
            left: 50%;
            transform: translate(-50%, 0);
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
