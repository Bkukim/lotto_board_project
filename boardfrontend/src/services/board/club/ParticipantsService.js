import http from "@/utils/http-common.js";
import AuthHeader from "@/services/auth/AuthHeader";

class ClubBoardService {
  // TODO: 신청하기 함수
  createParticipant(clubBoardId, userId) {
    return http.post("/user/club-board/participants", null, {
      params: { clubBoardId, userId },
      headers: AuthHeader(),
    });
  }
  // TODO: 신청취소 함수
  deleteParticipant(clubBoardId, userId) {
    return http.delete("/user/club-board/participants/deletion", {
      params: { clubBoardId, userId },
      headers: AuthHeader(),
    });
  }
  // TODO: 신청 상태 확인 함수
  checkParticipant(clubBoardId, userId) {
    return http.get("/user/club-board/participants/check", {
      params: { clubBoardId, userId },
      headers: AuthHeader(),
    });
  }
  // TODO: 게시글 작성자 확인 함수
  getParticipants(clubBoardId) {
    return http.get(`/user/club-board/participants/${clubBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: 승인 함수
  approveParticipant(clubBoardId, userId) {
    return http.put("/user/club-board/participants/approve", null, {
      params: { clubBoardId, userId },
      headers: AuthHeader(),
    });
  }
   // TODO: 승인하기 :: 승인취소 
   toggleApproval(clubBoardId, userId) {
    return http.put("/user/club-board/participants/toggle-approval", null, {
      params: { clubBoardId, userId },
      headers: AuthHeader(),
    });
  }
}

export default new ClubBoardService();
