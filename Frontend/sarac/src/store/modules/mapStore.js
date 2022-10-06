import axios from "axios";

const mapStore = {
    namespaced: true,

    state: {

    },
    getters: {

    },
    mutations: {

    },
    actions: {
        async getCafeList(commit, latLngInfo) {
            const response = await axios({
                url: "api/v1/cafe/cafeList",
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                data: {
                    "minLatitude": latLngInfo[0],
                    "maxLatitude": latLngInfo[1],
                    "minLongitude": latLngInfo[2],
                    "maxLongitude": latLngInfo[3] 
                },
            }).then((res) => {
                return res.data;
            });
            return response;
        },
        async getCafeInfo(commit, cafeNo) {
            const response = await axios({
                url: `api/v1/cafe/cafeInfo/${cafeNo}`,
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                }
            }).then((res) => {
                return res.data;
            });
            return response;
        },
        async getLastNoiseRecordInfo(commit, cafeNo) {
            const response = await axios({
                url: `api/v1/cafeNoise/lastRecord/${cafeNo}`,
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                }
            }).then((res) => {
                return res.data;
            }).catch(() => {
                return [];
            });
            return response;
        },
        async getAverageNoiseInfo(commit, cafeNo) {
            const response = await axios({
                url: `api/v1/cafeNoise/averageRecord/${cafeNo}`,
                method: "GET",
                headers: {

                }
            }).then((res) => {
                return res.data;
            });
            return response;
        },
        recordCafeNoise(commit, noiseInfo) {
            axios({
                url: "api/v1/cafeNoise/record",
                method: "PUT",
                headers: {
                // Token
                },
                data: {
                    "cafe": {
                        "id": noiseInfo[0]
                    },
                    "user": {
                        "id": noiseInfo[1]
                    },
                    "noise": noiseInfo[2]
                    },
            }).then((res) => {
                return res;
            });
        },
        async getCafeGoodBadInfo(commit, cafeNo) {
            const response = await axios({
                url: `api/v1/cafeLike/cafeGoodBadCounts/${cafeNo}`,
                method: "GET",
                headers: {
                    //Token
                }
            }).then((res) => {
                return res.data;
            });
            return response;
        },
    },
};

export default mapStore;