// 매칭 게시판 공통 CRUD 함수
import http from "@/utils/http-common.js";
import AuthHeader from "@/services/auth/AuthHeader";

class ClubBoardService {
    // TODO: 전체 조회
    getAllClub() {
        return http.get(`/user/board/club`, {headers: AuthHeader(),});
    }
    // TODO: 상세 조회
    getClubOnce(clubBoardId) {
        return http.get(`/user/board/club/${clubBoardId}`
        , {headers: AuthHeader(),})
    }
    // TODO: 수정
    updateClub(clubBoardId) {
        return http.post(`/user/board/club/update/${clubBoardId}`)
    }
    // TODO: 삭제
    deleteClub(clubBoardId) {
        return http.delete(`/user/board/club/deletion/${clubBoardId}`)
    }

}

export default new ClubBoardService();