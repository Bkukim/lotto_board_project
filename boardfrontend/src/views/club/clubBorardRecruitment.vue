<template>
  <div style="background-color: #f2f2f2; height: 5000px">
    <div class="container text-center">
      <div
        style="width: 100%; height: 500px; background-color: #4480fe"
        v-if="!mapView"
      >
        <!-- if문으로 지도와 사진을 띄우기 -->
        <img
          src="@/assets/img/i_a6003444220f.jpg"
          alt="clud_poto"
          width="100%"
          height="500"
          class="d-inline-block align-text-top"
        />
      </div>
      <div id="map" style="width: 100%; height: 500px" ref="map" v-else></div>
    </div>

    <div class="container text-center mt-1">
      <div class="container text-center">
        <div class="lotto_new row row-cols-lg-4 gap-5 justify-content-center">
          <!-- 매치 포인트 박스 -->
          <div
            class="col"
            style="
              height: 400px;
              width: 900px;
              background-color: #ffffff;
              margin-right: 24px;
            "
          >
            <p
              class="ontainer text-left"
              style="
                text-align: left;
                margin: 20px 0 0 10px;
                font-size: 20px;
                letter-spacing: -1px;
                font-weight: 600;
              "
            >
              매치 포인트
            </p>
          </div>

          <!-- 오른쪽 박스 -->
          <div class="col" style="background-color: #ffffff; height: 400px">
            news 2
            <button @click="popUpMap">장소 사진 보기</button>
          </div>
        </div>

        <!-- 매치데이터 박스 -->
        <div class="row mt-3" style="background-color: #ffffff; height: 700px">
          <p
            class="ontainer text-left"
            style="
              text-align: left;
              margin: 20px 0 0 10px;
              font-size: 20px;
              letter-spacing: -1px;
              font-weight: 600;
            "
          >
            매치 데이터
          </p>
        </div>

        <div
          class="row mt-3"
          style="background-color: #ffffff; height: 700px; text-align: left"
        >
          <p
            class="ontainer text-left"
            style="
              text-align: left;
              margin: 20px 0 0 10px;
              font-size: 20px;
              letter-spacing: -1px;
              font-weight: 600;
            "
          >
            매치 진행방식
          </p>
          <ul style="padding: 0 0 0 30px; font-size: 20px; padding-left: 50px">
            <b>매치 규칙</b>
            <li>모든 파울은 사이드라인에서 킥인</li>
            <li>골키퍼에게 백패스 가능 손으로는 잡으면 안 돼요</li>
            <li>사람을 향한 태클 금지</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      options: {
        //지도를 생성할 때 필요한 기본 옵션
        center: {
          lat: 33.450701,
          lng: 126.570667,
        }, //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      },
      address : "부산 부산진구 중앙대로 749 4층",
      mapView: true,
    };
  },
  methods: {
    popUpMap() {
      this.mapView = !this.mapView;
      console.log(this.mapView);
      if (this.mapView) {
        this.$nextTick(() => {
          setTimeout(() => {
            this.retrieveMap(this.address);
          }, 0);
        });
      }
    },
    retrieveMap(address){
      let kakao = window.kakao;
    var container = this.$refs.map;
    const { center, level } = this.options;

    var map = new kakao.maps.Map(container, {
      center: new kakao.maps.LatLng(center.lat, center.lng),
      level,
    }); //지도 생성 및 객체 리턴

    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(
      address,
      function (result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
            map: map,
            position: coords,
          });

          // 인포윈도우로 장소에 대한 설명을 표시합니다
          var infowindow = new kakao.maps.InfoWindow({
            content:
              '<div style="width:150px;text-align:center;padding:6px 0;">모임장소</div>',
          });
          infowindow.open(map, marker);

          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          map.setCenter(coords);
        }
      }
    );
    }
  },
  mounted() {
    this.retrieveMap(this.address);
    //   let kakao = window.kakao;
    //   var container = this.$refs.map;
    //   const { center, level } = this.options;

    //   this.mapInstance = new kakao.maps.Map(container, {
    //     center: new kakao.maps.LatLng(center.lat, center.lng),
    //     level,
    //   }); //지도 생성 및 객체 리턴

    //   // 마커가 표시될 위치입니다
    //   var markerPosition = new kakao.maps.LatLng(center.lat, center.lng);

    //   // 마커를 생성합니다
    //   var marker = new kakao.maps.Marker({
    //     position: markerPosition,
    //   });

    //   // 마커가 지도 위에 표시되도록 설정합니다
    //   marker.setMap(this.mapInstance);

    //   var iwContent =
    //       '<div style="padding:5px;">모임 장소</div> <br><a href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    //     iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다

    //   // 인포윈도우를 생성합니다
    //   var infowindow = new kakao.maps.InfoWindow({
    //     position: iwPosition,
    //     content: iwContent,
    //   });

    //   // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
    //   infowindow.open(this.mapInstance, marker);
  },
};
</script>
<style>
#clud_left {
  width: 750px;
  background-color: #ffffff;
}
</style>
