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
  // TODO: 수정함수
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

  reportFreeBoard(data) {
    return http.post("user/board/free/report/save", data, {
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

  // TODO: 신고 게시글 처리완료 전체 조회
  getAllFreeBoardReportProcessed(userId, page, size) {
    return http.get(
      `/admin/report/free/processed?userId=${userId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }

  // TODO: 신고 게시글 삭제 : 자유게시판 삭제, 신고테이블 상태변경
  deleteFreeBoardReport(freeBoardId) {
    return http.delete(`/admin/report/free/deletion/${freeBoardId}`, {
      headers: AuthHeader(),
    });
  }


  // TODO: 신고 게시글 취소 : 신고테이블 상태변경
  updateFreeBoardReport(freeBoardId) {
    console.log(freeBoardId);
    return http.put(`/admin/report/free/update/${freeBoardId}`, {


      headers: AuthHeader(),
    });
  }


  // // TODO: 신고 게시글 삭제 : isProcessed Y로 수정함수
  // updateFreeBoardReport(reportId) {
  //   let data={}
  //   return http.put(`/user/board/free/update/${reportId}`, data, {
  //     headers: AuthHeader(),
  //   });
  // }

}

export default new FreeBoardService();
