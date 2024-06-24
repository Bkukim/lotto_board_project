import http from "@/utils/http-common.js";
import AuthHeader from "@/services/auth/AuthHeader";

class DeptBoardLikeService {
  // 좋아요 생성
  createComplaintBoardLike(complaintBoardLike) {
    return http.post("/normal/complaint/board-like/create", complaintBoardLike, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 확인
  getLikeByUserIdAndComplaintBoardId(userId, complaintBoardId) {
    return http.get(`/normal/complaint/board-like/${userId}/${complaintBoardId}`, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 삭제
  deleteComplaintBoardLike(likeId) {
    return http.delete(`/normal/complaint/board-like/delete/${likeId}`, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 ID 조회
  getLikeId(userId, complaintBoardId) {
    return http.get(`/normal/complaint/board-like/get/${userId}/${complaintBoardId}`, {
      headers: AuthHeader(),
    });
  }
}

export default new DeptBoardLikeService();
