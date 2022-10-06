<template>
    <div class="nearby-container">
        <div id="map"></div>
        <v-dialog v-model="dialog" width="500">
            <v-card>
                <v-card-text>
                    <div class="cafe_info upper">
                        <div class="cafe_image">
                            <img :src="cafeImg" style="width: 100%">
                        </div>
                        <div class="cafe_blank"></div>
                        <div class="cafe_description">
                            <p class="cafe_name">{{ cafeInfo.cafeName }}</p>
                            <p class="cafe_address">{{ cafeInfo.cafeAddress }}</p>
                        </div>
                    </div>
                    <div v-if="lastNoiseRecord.length != 0" class="cafe_info middle">
                        <p class="cafe_last_decibel">{{ lastNoiseRecord.noise.toFixed(2) }} dB</p>
                        <p class="cafe-average-decibel">평균 {{ averageNoiseRecord.toFixed(2) }} dB</p>
                        <p class="cafe_last_record">마지막 측정 : {{ lastNoiseTime }}</p>
                    </div>
                    <div v-else>
                        <p class="cafe-no-record-message">측정 결과가 없습니다.</p>
                    </div>
                    <div class="cafe_info lower">
                        <div class="cafe_noise">
                            <!--div class="cafe_noise_graph"></div-->
                            <v-btn class="record-btn" @click="recordNoise">{{ recordMessage }}</v-btn>
                        </div>
                        <div class="good-bad-area">
                            <div class="count-area">
                                <div class="like good-image">
                                    <img :src="likeImg" style="width: 100%; height: 100%" @click="likeCafe">
                                </div>
                                <div class="count-number">
                                    <p class="count">{{ cafeGoodBadInfo.goods }}</p>
                                </div>
                            </div>
                            <div class="count-area">
                                <div class="like bad-image">
                                    <img :src="dislikeImg" style="width: 100%; height: 100%" @click="dislikeCafe">
                                </div>
                                <div class="count-number">
                                    <p class="count">{{ cafeGoodBadInfo.bads }}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </v-card-text>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
import { mapActions } from "vuex";
import * as Tone from 'tone';
// import { chart } from 'chart.js';

const mapStore = "mapStore";
const likeStore = "likeStore";

export default {
    name: "nearByView",
    data() {
        return {
            cafeRedImg: require('../assets/book_icons/book_red.png'),
            cafeYellowImg: require('../assets/book_icons/book_yellow.png'),
            cafeGreenImg: require('../assets/book_icons/book_green.png'),
            cafeGreyImg: require('../assets/book_icons/book_grey.png'),
            dislikeSelectedImg: require('../assets/likes/dislike_selected.png'),
            dislikeUnselectedImg: require('../assets/likes/dislike_unselected.png'),
            likeSelectedImg: require('../assets/likes/like_selected.png'),
            likeUnselectedImg: require('../assets/likes/like_unselected.png'),
            likeImg: require('../assets/likes/like_unselected.png'),
            dislikeImg: require('../assets/likes/dislike_unselected.png'),
            cafeImg: require('../assets/img/cafe.jpg'),
            latitude: 33.450701,
            longitude: 126.570667,
            cafeList: [],
            positions: [],
            markers: [],
            cafeInfo: {
                cafeName: "카페 이름",
                cafeAddress: "카페 주소"
            },
            lastNoiseRecord: {
                noise: 12.345678,
            },
            lastNoiseTime: '2022-10-07',
            currentCafeNo: 0,
            currentUserId: 0,
            dialog: false,
            recordMessage: '주변 소음 측정하기',
            averageNoiseRecord: 45.67,
            cafeGoodBadInfo: {
                goods: 999,
                bads: 999,
            },
        }
    },
    created() {
    },
    mounted() {
        if(navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                this.latitude = position.coords.latitude;
                this.longitude = position.coords.longitude;
            });
        }

        if(window.kakao && window.kakao.maps) {
            this.initMap();
            
            navigator.geolocation.getCurrentPosition((position) => {
                this.latitude = position.coords.latitude;
                this.longitude = position.coords.longitude;
                this.map.setCenter(new kakao.maps.LatLng(this.latitude, this.longitude));
            });
        } else {
            const script = document.createElement("script");
            /* global kakao */
            script.onload = () => kakao.maps.load(this.initMap);
            script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + process.env.VUE_APP_KAKAO_MAP_APP_KEY;
            document.head.appendChild(script);
        }
    },
    watch: {
        lastNoiseRecord(newRecord) {
            if(newRecord.length !== 0) {
                let dateTime = newRecord.time.split("T");
                let time = dateTime[1].split(":");
                console.log(time[0]);
                console.log(time[1]);
                this.lastNoiseTime = dateTime[0] + " " + time[0] + ":" + time[1] + ":" + time[2].substring(0, 2);
            }
        },
    },
    methods: {
        ...mapActions(mapStore, ["getCafeList", "getCafeInfo", "getLastNoiseRecordInfo", "getAverageNoiseInfo", "recordCafeNoise", "getCafeGoodBadInfo"]),
        ...mapActions(likeStore, ["voteUpCafe", "voteDownCafe", "removeVoteCafe", "getCafeLikeInfo"]),

        initMap() {
            const mapArea = document.getElementById("map");
            
            navigator.geolocation.getCurrentPosition((position) => {
                this.latitude = position.coords.latitude;
                this.longitude = position.coords.longitude;
            });

            const options = {
                center: new kakao.maps.LatLng(this.latitude, this.longitude),
                level: 3,
            };

            this.map = new kakao.maps.Map(mapArea, options);
            // 확대 축소 막기 : 카페 정보가 너무 많아서 부하 걸릴 수 있음
            this.map.setZoomable(false);
            // 지도 드래그 앤 드롭 이벤트 발생 시 알아서 새로 그리도록
            kakao.maps.event.addListener(this.map, 'dragend', () => {
                this.createPositions();
            })
            this.createPositions();
        },
        createPositions() {
            let bounds = this.map.getBounds();
            let south = bounds.getSouthWest().getLat();
            let west = bounds.getSouthWest().getLng();
            let north = bounds.getNorthEast().getLat();
            let east = bounds.getNorthEast().getLng();
            let latLngInfo = [south, north, west, east];

            this.getCafeList(latLngInfo).then((res) => {
                this.cafeList = res.cafeList;
                this.createMarkers();
            });
        },
        async createMarkers() {
            let imageSize = new kakao.maps.Size(25, 25);
            let markerImgSrc = [this.cafeGreyImg, this.cafeGreenImg, this.cafeYellowImg, this.cafeRedImg];
            let imageOption = { spriteSize: new kakao.maps.Size(25, 25) };
            let markerImg = [];
            markerImgSrc.forEach(src => {
                markerImg.push(this.createMarkerImage(src, imageSize, imageOption));
            });

            for(const cafe of this.cafeList) {
                let coord = new kakao.maps.LatLng(cafe.latitude, cafe.longitude);
                let cafeNo = cafe.id;
                
                let idx = 0;
                let avgNoise = await this.getAverageNoiseInfo(cafeNo);

                if(avgNoise === 0.0) idx = 0;
                else if(avgNoise < 45) idx = 1;
                else if(avgNoise < 55) idx = 2;
                else idx = 3;
                
                let marker = this.createMarker(coord, markerImg[idx]);
                kakao.maps.event.addListener(marker, 'click', () => {
                    this.getCafeInfo(cafeNo).then((res) => {
                        this.cafeInfo = res.cafe;
                    });
                    this.currentCafeNo = cafeNo;
                    let userCafePair = [cafeNo, this.$store.state.accountStore.user.userId];
                    this.getCafeLikeInfo(userCafePair).then((res) => {
                        console.log("res is : ");
                        console.log(res);
                        this.likeImg = (res === true) ? this.likeSelectedImg : this.likeUnselectedImg;
                        this.dislikeImg = (res === false) ? this.dislikeSelectedImg : this.dislikeUnselectedImg;

                        this.getLastNoiseRecordInfo(cafeNo).then((res) => {
                            this.lastNoiseRecord = res;
                        });
                        this.getAverageNoiseInfo(cafeNo).then((res) => {
                            this.averageNoiseRecord = res;
                        });
                        this.getCafeGoodBadInfo(cafeNo).then((res) => {
                            this.cafeGoodBadInfo = res;
                        });

                        this.dialog = true;
                    });
                });
                this.markers.push(marker);
            }

            this.displayMarkers();
        },
        displayMarkers() {
            this.markers.forEach(marker => {
                marker.setMap(this.map);
            });
        },
        createMarkerImage(src, size, options) {
            return new kakao.maps.MarkerImage(src, size, options);
        },
        createMarker(position, image) {
            return new kakao.maps.Marker({
                position: position,
                image: image,
                clickable: true
            });
        },
        likeCafe() {
            if(this.dislikeImg === this.dislikeSelectedImg) this.cafeGoodBadInfo.bads -= 1;
            this.dislikeImg = this.dislikeUnselectedImg;
            let userCafePair = [this.currentCafeNo, this.$store.state.accountStore.user.userId];
            if(this.likeImg === this.likeSelectedImg) {
                this.cafeGoodBadInfo.goods -= 1;
                this.removeVoteCafe(userCafePair).then(() => {
                    this.likeImg = this.likeUnselectedImg;
                });
            } else {
                this.cafeGoodBadInfo.goods += 1;
                this.voteUpCafe(userCafePair).then(() => {
                    this.likeImg = this.likeSelectedImg;
                });
            }
        },
        dislikeCafe() {
            if(this.likeImg === this.likeSelectedImg) this.cafeGoodBadInfo.goods -= 1;
            this.likeImg = this.likeUnselectedImg;
            let userCafePair = [this.currentCafeNo, this.$store.state.accountStore.user.userId];
            if(this.dislikeImg === this.dislikeSelectedImg) {
                this.cafeGoodBadInfo.bads -= 1;
                this.removeVoteCafe(userCafePair).then(() => {
                    this.dislikeImg = this.dislikeUnselectedImg;
                });
            } else {
                this.cafeGoodBadInfo.bads += 1;
                this.voteDownCafe(userCafePair).then(() => {
                    this.dislikeImg = this.dislikeSelectedImg;
                });
            }
        },
        recordNoise() {
            if(Tone.context.state !== 'running') {
                Tone.context.resume();
            }

            async () => await Tone.start();
            this.recordMessage = "측정중...";

            let meter = new Tone.Meter();
            let mic = new Tone.UserMedia();

            mic.open();
            mic.connect(meter);

            let soundData = [];

            setTimeout(() => {
                let timerId = setInterval(() => {
                    let value = meter.getValue() + 100;
                    value = Number.isNaN(value) ? 0 : value;
                    soundData.push(value);
                }, 10);

                setTimeout(() => {
                    clearInterval(timerId);
                    const initialValue = 0;
                    const sum = soundData.reduce((r, b) => r + b, initialValue);
                    let average = sum / soundData.length;
                    this.recordMessage = `측정 완료   ${average.toFixed(2)} dB`;

                    let noiseInfo = [this.currentCafeNo, this.$store.state.accountStore.user.userId, average];
                    this.recordCafeNoise(noiseInfo);

                    mic.disconnect(meter);
                    mic.close();
                }, 10000);
            }, 1000);
        },
        // drawChart() {
        // },
    },
};
</script>

<style scoped>
.nearby-container {
    width: 100%;
    height: 100%;
}

#map {
    width: 100%;
    height: 100%;
}

.cafe_info {
    width: 100%;
    display: flex;
    align-items: center;
}

.upper {
    height: 230px;
}

.middle {
    height: 50px;
    display: flex;
    justify-content: space-between;
    align-items: start;
}

.lower {
    height: 100px;
}

.cafe_image {
    width: 30%;
    height: 80%;
}

.cafe_blank {
    width: 5%;
    height: 100%;
}

.cafe_description {
    width: 65%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.cafe_name {
    width: 100%;
    text-align: center;
    font-size: 4vh;
}

.cafe_address {
    width: 80%;
    text-align: left;
    font-size: 1.8vh;
    padding-top: 20px;
}

.cafe_last_decibel {
    width: 30%;
    height: 100%;
    text-align: center;
    font-size: 2.5vh;
}

.cafe-average-decibel {
    width: 35%;
    height: 100%;
    text-align: center;
    font-size: 2.2vh;
}

.cafe-no-record-message {
    width: 100%;
    height: 100%;
    text-align: center;
    font-size: 2.5vh;
}

.cafe_noise {
    width: 60%;
    height: 100%;
    display: flex;
    text-align: center;
    align-items: center;
}

.cafe_noise_graph {
    width: 100%;
    height: 60%;
}
.record-btn {
    margin-top: 10px;
    width: 100%;
    height: 80%;
}

.good-bad-area {
    width: 40%;
    height: 100%;
    display: flex;
    justify-content: space-around;
    align-items: center;
}

.count-area {
    width: 40px;
    height: 52px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.like {
    height: 40px;
}

.count-number {
    height: 12px;
    display: flex;
    justify-content: center;
    text-align: center;
}
</style>