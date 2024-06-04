import http from "@/utils/http-common.js";
import AuthHeader from "@/services/auth/AuthHeader";

class ClubBoardService {
    // TODO: 전체 조회
    getAllClub() {
        return http.get(`/user/board/club`, { headers: AuthHeader() });
    }
    // TODO: 상세 조회
    getClubOnce(clubBoardId) {
        return http.get(`/user/board/club/${clubBoardId}`, { headers: AuthHeader() });
    }
    // TODO: 수정
    updateClub(clubBoardId, data) {
        let formData = new FormData();
        Object.keys(data).forEach(key => formData.append(key, data[key]));
        return http.put(`/user/board/club/update/${clubBoardId}`, formData, { headers: AuthHeader() });
    }
    // TODO: 삭제
    deleteClub(clubBoardId) {
        return http.delete(`/user/board/club/deletion/${clubBoardId}`, { headers: AuthHeader() });
    }
    // TODO: createClub 함수 수정
    createClub(formData) {
        return http.post("/user/board/club/create", formData, {
            headers: {
                ...AuthHeader(),
                "Content-Type": "multipart/form-data",
            },
        });
    }
    // createClub(data) {
    //     let formData = new FormData();
        
    //     // JSON 데이터를 FormData에 추가
    //     Object.keys(data).forEach(key => {
    //         if (Array.isArray(data[key])) {
    //             data[key].forEach((item, index) => {
    //                 formData.append(`${key}[${index}]`, JSON.stringify(item));
    //             });
    //         } else {
    //             formData.append(key, data[key]);
    //         }
    //     });

    //     // Axios를 사용하여 POST 요청 전송
    //     return http.post("/user/board/club/create", formData, { headers: AuthHeader() });
    // }
    // createClub(data) {
    //     let formData = new FormData();
    //     // 파일 데이터 처리
    //     if (data.imgFile) {
    //         formData.append("imgFile", data.imgFile);
    //     }
    //     // 파일을 제외한 나머지 데이터를 JSON 문자열로 변환하여 전송
    //     const jsonData = { ...data };
    //     delete jsonData.imgFile; // 파일 데이터를 제외
    //     formData.append("jsonData", JSON.stringify(jsonData));
    
    //     return http.post("/user/board/club/create", formData, {
    //         headers: {
    //             ...AuthHeader(),
    //             // FormData를 사용할 때는 'Content-Type': 'multipart/form-data'를 설정하면 안 됩니다.
    //             // Axios가 자동으로 적절한 'Content-Type'을 설정합니다.
    //             // 하지만 이 경우 직접 설정해야 하는 다른 헤더가 있을 수 있으므로, AuthHeader()를 통해 받은 값을 사용합니다.
    //         },
    //     });
    // }
    // createClub(data) {
    //     let formData = new FormData();
    //     Object.keys(data).forEach(key => formData.append(key, data[key]));
    //     return http.post("/user/board/club/create", formData, { headers: AuthHeader() });
    // }
        // createClub 함수 수정
        // createClub(data) {
        //     // FormData 객체 생성
        //     let formData = new FormData();
        //     // 데이터를 FormData에 추가
        //     Object.keys(data).forEach(key => formData.append(key, data[key]));
    
        //     // Axios를 사용하여 POST 요청 전송
        //     // 두 번째 인자로 FormData 객체를 전달하고, 세 번째 인자로 헤더를 설정
        //     return http.post("/user/board/club/create", formData, { headers: AuthHeader() });
        // }
        // createClub(data) {
        //     // FormData 객체 생성
        //     let formData = new FormData();
        //     // 데이터를 FormData에 추가
        //     Object.keys(data).forEach(key => formData.append(key, data[key]));
    
        //     // Axios를 사용하여 POST 요청 전송
        //     // 두 번째 인자로 FormData 객체를 전달하고, 세 번째 인자로 헤더를 설정
        //     return http.post(`/user/board/club/create`, formData, { headers: AuthHeader() });
        // }
}

export default new ClubBoardService();