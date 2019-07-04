<template>
    <div class="page-near">
        <!--附近回收机列表 <br>-->
        <!--点击列表去导航 <br>-->
        <div @click="map">点击</div>
        <!--<map @regionchange="regionChange" ></map>-->

        <map
        subkey="RLRBZ-PUYOP-DXDDT-VV5P2-5VJHF-RHFCL"
        :markers="markers"
        :circles="circles"
        @markertap="showDetail"
        @regionchange="regionChange"
        @begin="regionChangeBegin"
        @end="regionChangeEnd"
        show-location
        id="near">
        <!--<cover-view>-->
        <!--<cover-view class="btn-list">列表模式</cover-view>-->
        <!--<cover-image-->
        <!--style="width: 26px; height: 30px;display: block;position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%)"-->
        <!--class="icon" src="/static/imgs/icon_near.png"></cover-image>-->
        <!--<cover-view-->
        <!--style="position: fixed;left: 50%;top: 25%;transform: translate(-50%, -50%);text-align: center"-->
        <!--class="address">-->
        <!--<cover-image style="width: 26px;display: block;margin: 0 auto 10px" class="icon_near"-->
        <!--src="/static/imgs/icon_near.png"/>-->
        <!--<cover-view style="font-size: 16px;font-weight: bold">{{currentAddress}}</cover-view>-->
        <!--</cover-view>-->
        <!--</cover-view>-->
        </map>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from "api/apiList";

    let touchTimeStamp = 0;
    export default {
        data() {
            return {
                mapCenter: {
                    latitude: 22.5542080000, //要去的纬度-地址
                    longitude: 113.8878770000 //要去的经度-地址
                },
                mapCtx: null,
                markers: [],
                //     {
                //         id: 0,
                //         latitude: 36.6744,
                //         longitude: 117.141091,
                //         iconPath: require("../../assets/imgs/icon_map.png"),
                //         width: 32,
                //         height: 32
                //     },
                //     {
                //         id: 1,
                //         latitude: 36.676236,
                //         longitude: 117.142268,
                //         iconPath: require("../../assets/imgs/icon_map.png"),
                //         width: 32,
                //         height: 32
                //     },
                //     {
                //         id: 2,
                //         latitude: 36.67545,
                //         longitude: 117.144487,
                //         iconPath: require("../../assets/imgs/icon_map.png"),
                //         width: 32,
                //         height: 32
                //     }
                // ],
                circles: [],
                currentAddress: "",
                currentAddressIcon: require("../../assets/imgs/icon_map.png"),
                qqmapSdk: null
            };
        },
        async onLoad() {
            // this.qqmapSdk = new QQMapWX({
            //     key: "RLRBZ-PUYOP-DXDDT-VV5P2-5VJHF-RHFCL"
            // });
            this.init();
            // this.mapCenter = this.$root.$mp.query;
            this.loadEquip();
        },
        mounted() {

        },
        methods: {
            async loadEquip() {
                console.log("获取设备列表");
                const res = await this.$get(API.getEquipList);
                console.log(res);
                if (res.err_code !== 0) return;
                for (var i = 0; i < res.data.length; i ++){
                    this.markers[i] = {
                        id: res.data[i].eid,
                        latitude: res.data[i].latitude,
                        longitude: res.data[i].longitude,
                        iconPath: require("../../assets/imgs/" + res.data[i].status +".png")
                    };
                }
                // this.markers = res.data;
            },
            map() {
                wx.openLocation({
                    latitude: this.mapCenter.latitude,//要去的纬度-地址
                    longitude: this.mapCenter.longitude//要去的经度-地址
                    // name: "宝安中心A地铁口",
                    // address: "宝安中心A地铁口"
                });
            },
            init() {
                this.mapCtx = wx.createMapContext("near");//获取地图对象同canvas相似，获取后才能调用相应的方法
                wx.getLocation({
                    success: res => {
                        console.log("wx.getLocation执行" + res.latitude + "-" + res.longitude);
                        this.mapCenter.latitude = res.latitude;
                        this.mapCenter.longitude = res.longitude;
                        this.mapCtx.moveToLocation();
                    }
                });

            },
            showDetail(e) {
                console.log(e);
                console.log(e.mp.markerId);
                this.markers.forEach(item => {
                    if (item.id == e.mp.markerId) {
                        this.mapCenter.latitude = item.latitude;
                        this.mapCenter.longitude = item.longitude;
                    }
                });
            },
            regionChange() {
                this.mapCtx.getCenterLocation({
                    success: (res) => {
                        console.log(res);
                        this.mapCenter.latitude = res.latitude;
                        this.mapCenter.longitude = res.longitude;
                        // this.mapCtx.moveToLocation()
                    }
                });
            },
            regionChangeBegin({ timeStamp }) {
                touchTimeStamp = timeStamp;
            },
            regionChangeEnd(e) {
                if (e.timeStamp - touchTimeStamp > 120) {
                    this.regionChange();
                }

            }
        }
    };
</script>

<style lang="less">

    @import "../../styles/mixin";

    map {
        width: 100vw;
        height: 50vh;
    }

    .btn-list {
        width: 100px;
        height: 30px;
        text-align: center;
        line-height: 30px;
        position: absolute;
        right: 15px;
        top: 15px;
        border-radius: 15px;
        color: #fff;
        font-size: 14px;
        background: @mainColor;
        .address {
            /*width: 50vw;*/

        }
        .cover-image {
            width: 32px;
            height: 32px;
        }
    }
</style>
