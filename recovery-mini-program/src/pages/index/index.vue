<template>
    <div class="page-index">
        <div class="banner">
            <img mode="widthFix" src="../../assets/imgs/banner.png" alt="">
        </div>
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
            <div class="card-item">
                <div class="title">
                    <h5>环保金奖励</h5>
                    <p>{{greenGold}} <span>元</span></p>
                    <div class="link">微信提现</div>
                </div>
            </div>
            <div class="card-item">
                <div class="title">
                    <h5>累计投递</h5>
                    <p>{{count}} <span>次</span></p>
                    <div class="link">查看记录</div>
                </div>
            </div>
        </div>
        <div @click="code" class="code">
            <div class="icon-wrapper">
                <i-icon size="20" color="#fff" type="scan"/>
            </div>
            <div class="txt">扫描二维码投递</div>
        </div>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from "api/apiList";

    export default {
        data() {
            return {
                navList: [{
                    title: "回收分类",
                    icon: require("../../assets/imgs/icon_type.png")
                }, {
                    title: "附近回收机",
                    icon: require("../../assets/imgs/icon_near.png")
                }, {
                    title: "投递指南",
                    icon: require("../../assets/imgs/icon_guide.png")
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
        async onLoad(options) {
            if (options.q !== undefined) {
                console.log("获取到options");
                if (this.checkLogin()) return;
                let scan_url = decodeURIComponent(options.q);
                console.log(scan_url);
                let deviceId = scan_url.replace(API.baseUrlForProd + 'equipment/code/','');
                let userId = wx.getStorageSync("userId");
                let res = await this.$post(API.scanDevice, {
                    device_id: deviceId,
                    uid: userId
                }, false);
                if(res.err_code === 0) {
                    this.$toast("扫码识别设备成功");
                }else{
                    this.$toast("扫码识别设备失败");
                }
            }else{
                console.log("未获取到options");
            }
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
                const res = await this.$post(API.getUserInfo, {
                    uid: userId
                }, false);
                if (res.err_code !== 0) return;
                wx.setStorageSync("userInfo", res.data);
                this.greenGold = res.data.s_current_profit;
                this.count = res.data.deliver_count;
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
                wx.scanCode({
                    success: res => {
                        console.log(res);
                        let deviceId = res.result.replace(API.baseUrlForProd + 'equipment/code/','');
                        let userId = wx.getStorageSync("userId");
                        console.log("deviceId:" + deviceId);
                        console.log("userId:" + userId);
                        let result = this.$post(API.scanDevice, {
                            device_id: deviceId,
                            uid: userId
                        }, false);
                        if(result.err_code === 0) {
                            this.$toast("扫码识别设备成功");
                        }else{
                            this.$toast("扫码识别设备失败");
                        }
                    },
                    fail: res => {
                        this.$toast("扫码识别设备失败");
                        console.log(res);
                    }
                })
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
                        this.$toast("该功能即将开放，敬请期待");
                        break;
                    case 1:
                        this.$toast("该功能即将开放，敬请期待");
                        break;
                    case 2:
                        this.$toast("该功能即将开放，敬请期待");
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
        .banner {
            display: flex;
            margin: 2px 4px;
            width: 100%;
            img{
                width: 100%;
            }
        }
        .nav-list {
            display: flex;
            padding: 10px 0;
            margin-top: 14px;
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
                    width: 47px;
                }
                .img1 {
                    width: 42px;
                }
                .img2 {
                    width: 55px;
                }
                .img3 {
                    width: 40px;
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
                margin: 20px auto 0;
                padding: 15px;
                width: 50%;
                float: left;
                text-align: center;
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
                    font-size: @fs14;
                    color: @fontColor9;
                    padding: 4px 10px;
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
