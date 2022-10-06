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
        }
    },
};

export default mapStore;