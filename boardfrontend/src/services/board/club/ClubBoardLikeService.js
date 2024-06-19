import http from "@/utils/http-common.js";
import AuthHeader from "@/services/auth/AuthHeader";

class ClubBoardLikeService {
  // 좋아요 생성
  createClubBoardLike(clubBoardLike) {
    return http.post("/normal/board/club/likes/create", clubBoardLike, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 확인
  getLikeByUserIdAndClubBoardId(userId, clubBoardId) {
    return http.get(`/normal/board/club/likes/${userId}/${clubBoardId}`, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 삭제
  deleteClubBoardLike(likeId) {
    return http.delete(`/normal/board/club/likes/delete/${likeId}`, {
      headers: AuthHeader(),
    });
  }

  // 좋아요 ID 조회
  findLikeIdByUserIdAndClubBoardId(userId, clubBoardId) {
    return http.get(`/normal/board/club/likes/find/${userId}/${clubBoardId}`, {
      headers: AuthHeader(),
    });
  }
}

export default new ClubBoardLikeService();
