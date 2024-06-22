import http from "@/utils/http-common.js";
import AuthHeader from "@/services/auth/AuthHeader";

class DeptBoardLikeService {
  // 좋아요 생성
  createDeptBoardLike(deptBoardLike) {
    return http.post("/normal/dept/board-like/create", deptBoardLike, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 확인
  getLikeByUserIdAndDeptBoardId(userId, deptBoardId) {
    return http.get(`/normal/dept/board-like/${userId}/${deptBoardId}`, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 삭제
  deleteDeptBoardLike(likeId) {
    return http.delete(`/normal/dept/board-like/delete/${likeId}`, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 ID 조회
  getLikeId(userId, deptBoardId) {
    return http.get(`/normal/dept/board-like/get/${userId}/${deptBoardId}`, {
      headers: AuthHeader(),
    });
  }
}

export default new DeptBoardLikeService();
