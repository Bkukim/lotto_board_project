import http from "@/utils/http-common"; // spring 통신 정의 파일
import AuthHeader from "@/services/auth/AuthHeader";

class FreeBoardService {
  // TODO: 전체 조회
  getAllBoard(title, page, size) {
    return http.get(
      `/user/board/free?title=${title}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }
  //   TODO: 상세 조회
  getFreeBoardId(freeBoardId) {
    return http.get(`/user/board/free/${freeBoardId}`, {
      headers: AuthHeader(),
    });
  }

  // TODO: 삭제
  deleteFreeBoard(freeBoardId) {
    return http.delete(`/user/board/free/deletion/${freeBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: 저장
  createFreeBoard(data) {
    console.log(data);
    return http.post("/user/board/free/save", data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 수정함수 : qnaId
  updateFreeBoard(freeBoardId, data) {
    return http.put(`/user/board/free/update/${freeBoardId}`, data, {
      headers: AuthHeader(),
    });
  }

  

  // TODO: 댓글 조회 : freeBoardId
  getFreeBoardComment(freeBoardId, page, size) {
    return http.get(
      `/user/board/free/${freeBoardId}/comments?page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }
  // TODO: 댓글 저장 함수
  createFreeBoardComment(data) {
    return http.post(`/user/board/free/save/comment`, data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 대댓글 조회 : freeBoardCommentId
  getFreeBoardRecomment(freeBoardId) {
    return http.get(`/user/board/free/recomments/${freeBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: 대댓글 저장 함수
  createFreeBoardRecomment(data) {
    return http.post(`/user/board/free/save/recomment`, data, {
      headers: AuthHeader(),
    });
  }
  // TODO: userId로 전체조회 내글찾기
  getAllFreeBoardUserId(userId, page, size) {
    return http.get(
      `/user/board/free/userId/freeBoard?userId=${userId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }


  // TODO: 좋아요 관련 함수모음
  // todo: 좋ㅇ아요 테이블에 생성 함수
  saveLike(data) {
    return http.post(`/user/free/board-like/save-like/`, data, 
    //   {
    //   headers: AuthHeader(),
    // }
  );
  }
 
  // TODO: 삭제
  deleteLike(likeId) {
    return http.delete(`/user/free/board-like/deletion/${likeId}`,
    //    {
    //   headers: AuthHeader(),
    // }
  );
  }
    // TODO: 수정함수 : updateLike 공감해요 버튼에 적용될것
    updateLike(freeBoardId, data2) {
      console.log("서비스입니다"+freeBoardId)
      return http.put(
        `/user/free/board-like/update-like/${freeBoardId}`,
        data2)}
        //    {
        //   headers: AuthHeader(),
        // }
  reportFreeBoard(data){
    return http.post("user/board/free/report/save",data, {
      headers: AuthHeader(),
    });
  }

    // TODO: 신고 게시글 전체 조회
    getAllFreeBoardReport(userId, page, size) {
      return http.get(
        `/admin/report/free?userId=${userId}&page=${page}&size=${size}`,
        {
          headers: AuthHeader(),
        }
      );
    }
}

export default new FreeBoardService();
