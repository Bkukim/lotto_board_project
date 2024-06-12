import http from "@/utils/http-common.js";
import AuthHeader from "@/services/auth/AuthHeader";

class ClubBoardService {
  // TODO: 전체 조회
  getAllClub() {
    return http.get(`/user/board/club`, { headers: AuthHeader() });
  }
  // TODO: 상세 조회
  getClubOnce(clubBoardId) {
    return http.get(`/user/board/club/${clubBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: 수정
  updateClub(clubBoardId, data) {
    let formData = new FormData();
    Object.keys(data).forEach((key) => formData.append(key, data[key]));
    return http.put(`/user/board/club/update/${clubBoardId}`, formData, {
      headers: AuthHeader(),
    });
  }
  // TODO: 삭제
  deleteClub(clubBoardId) {
    return http.delete(`/user/board/club/deletion/${clubBoardId}`, {
      headers: AuthHeader(),
    });
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
  // TODO: clubBoardId로 uuid 조회하는 함수
  getImgUuid(clubBoardId) {
    return http.get(`/user/board/club/uuid/${clubBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: uuid로 이미지 조회하는 함수
  getStadiumImg(uuid) {
    return http.get(`/user/board/club/img/${uuid}`, {
      headers: AuthHeader(),
      responseType: "blob",
    });
  }
  // TODO: userId로 전체조회 내글찾기
  getAllClubBoardUserId(userId, page, size) {
    return http.get(
      `/user/board/club/userId/clubBoard?userId=${userId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }
}

export default new ClubBoardService();
