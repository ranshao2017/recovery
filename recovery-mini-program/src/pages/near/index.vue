<template>
    <div class="page-near">
        <!--附近回收机列表 <br>-->
        <!--点击列表去导航 <br>-->
        <!--<div @click="map">点击</div>-->
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
        </map>
        <div class="map-detail0">
            <div class="daohang-msg">
                <img src="/static/imgs/icon_near.png">{{distance}} km
            </div>
            <div class="daohang" @click="goTo">
                <img src="/static/imgs/daohang.png">
            </div>
        </div>
        <div class="map-detail">
            <div class="time-msg">
                <img src="/static/imgs/time.png">周一至周日 08:30 - 21:00
            </div>
            <div v-if="equiStatus != ''" class="status-msg">
                {{equiStatus}}
            </div>
        </div>
        <div class="rec-obj">
            <img src="/static/imgs/recobj.png">
        </div>
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
                circles: [],
                currentAddress: "",
                currentAddressIcon: require("../../../static/imgs/icon_map.png"),
                equiStatus:"",
                beginLat: 22.5542080000,
                beginLon: 113.8878770000,
                distance: 0.00
            };
        },
        async onLoad() {
            this.init();
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

                res.data.forEach(item => {
                    var obj = {
                        id: item.eid,
                        latitude: item.latitude,
                        longitude: item.longitude,
                        iconPath: "/static/imgs/near_" + item.status +".png",
                        s_status: item.s_status,
                        width: 26,
                        height: 32
                    };
                    this.markers.push(obj);
                });
            },
            goTo() {
                wx.openLocation({
                    latitude: this.mapCenter.latitude,//要去的纬度-地址
                    longitude: this.mapCenter.longitude//要去的经度-地址
                });
            },
            init() {
                this.mapCtx = wx.createMapContext("near");//获取地图对象同canvas相似，获取后才能调用相应的方法
                wx.getLocation({
                    success: res => {
                        console.log("wx.getLocation执行" + res.latitude + "-" + res.longitude);
                        this.mapCenter.latitude = res.latitude;
                        this.mapCenter.longitude = res.longitude;

                        this.beginLat = res.latitude;
                        this.beginLon = res.longitude;

                        this.mapCtx.moveToLocation();
                    }
                });

            },
            showDetail(e) {
                this.markers.forEach(item => {
                    if (item.id == e.mp.markerId) {
                        this.mapCenter.latitude = item.latitude;
                        this.mapCenter.longitude = item.longitude;
                        this.equiStatus = item.s_status;

                        let dis = this.getDistance(this.beginLat, this.beginLon, this.mapCenter.latitude, this.mapCenter.longitude);
                        this.distance = dis.toFixed(2);
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
            },
            //进行经纬度转换为距离的计算
            rad(d){
                return d * Math.PI / 180.0;//经纬度转换成三角函数中度分表形式。
            },
            //计算距离，参数分别为第一点的纬度，经度；第二点的纬度，经度
            getDistance(lat1, lng1, lat2, lng2){
                let radLat1 = this.rad(lat1);
                let radLat2 = this.rad(lat2);
                let a = radLat1 - radLat2;
                let  b = this.rad(lng1) - this.rad(lng2);
                let s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
                s = s *6378.137 ;// EARTH_RADIUS;
                s = Math.round(s * 10000) / 10000; //输出为公里
                return s;
            }
        }
    };
</script>

<style lang="less">

    @import "../../styles/mixin";

    .page-near{
        .map-detail0{
            margin-top: 15px;
            color: @fontColor6;
            font-size: 15px;
            height: 35px;
            width: 100%;

            .daohang-msg{
                float: left;
                margin-left: 15px;
                img {
                    width: 22px;
                    height: 22px;
                    margin-right: 15px;
                }
            }

            .daohang{
                float: right;
                margin-right: 15px;
                img{
                    width: 28px;
                    height: 28px;
                }
            }
        }
        .map-detail{
            color: @fontColor6;
            font-size: 15px;
            height: 35px;
            width: 100%;

            .time-msg{
                float: left;
                margin-left: 15px;
                img {
                    width: 22px;
                    height: 22px;
                    margin-right: 8px;
                }
            }
            .status-msg{
                margin-left: 20px;
                float: left;
                width: 80px;
                height: 25px;
                border-radius: 23px;
                text-align: center;
                line-height: 25px;
                border: 2px solid #d4c10d;
                color: #d4c10d;
            }

        }
        .rec-obj{
            border-top: 1px solid #cdcaca;
            margin-top: 15px;
            img{
                width: 400px;
                height: 100px;
            }
        }
    }

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
