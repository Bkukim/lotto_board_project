import http from "@/utils/http-common.js";
import AuthHeader from "@/services/auth/AuthHeader";

class FreeBoardLikeService {
  // 좋아요 생성
  createFreeBoardLike(freeBoardLike) {
    return http.post("/normal/free/board-like/create", freeBoardLike, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 확인
  getLikeByUserIdAndFreeBoardId(userId, freeBoardId) {
    return http.get(`/normal/free/board-like/${userId}/${freeBoardId}`, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 삭제
  deleteFreeBoardLike(likeId) {
    return http.delete(`/normal/free/board-like/delete/${likeId}`, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 ID 조회
  getLikeId(userId, freeBoardId) {
    return http.get(`/normal/free/board-like/get/${userId}/${freeBoardId}`, {
      headers: AuthHeader(),
    });
  }
}

export default new FreeBoardLikeService();
