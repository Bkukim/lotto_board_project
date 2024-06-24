import http from "@/utils/http-common"; // spring 통신 정의 파일
import AuthHeader from "@/services/auth/AuthHeader";

class FreeBoardService {

    // TODO: 메인에서 최신순 조회
    getRecentFreeboard() {
      console.log("자유게시판 최신순")
      return http.get(`/normal/board/main/free/recent`,
        {
          headers: AuthHeader(),
        }
      );
    }
    
  // TODO: 메인에서 좋아요순대로 조회
  getFreeboardLike() {
    console.log("여기는 서비스")
    return http.get(
      `/normal/board/main-get/free`,
      {
        headers: AuthHeader(),
      }
    );
  }
     //TODO: hot 게시판 조회
     getHotBoard() {
      return http.get(`/normal/board/hot-board`, {
        headers: AuthHeader(),
      });
    }



  // TODO: 전체 조회
  getAllBoard(title, page, size) {
    return http.get(
      `/normal/board/free?title=${title}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }

  //   TODO: 상세 조회
  getFreeBoardId(freeBoardId) {
    return http.get(`/normal/board/free/${freeBoardId}`, {
      headers: AuthHeader(),
    });
  }

  // TODO: 삭제
  deleteFreeBoard(freeBoardId) {
    return http.delete(`/normal/board/free/deletion/${freeBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: 저장
  createFreeBoard(data) {
    console.log(data);
    return http.post("/normal/board/free/save", data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 수정함수
  updateFreeBoard(freeBoardId, data) {
    return http.put(`/normal/board/free/update/${freeBoardId}`, data, {
      headers: AuthHeader(),
    });
  }


  // TODO: 댓글 조회 : freeBoardId
  getFreeBoardComment(freeBoardId, page, size) {
    return http.get(
      `/normal/board/free/${freeBoardId}/comments?page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }
  // TODO: 댓글 저장 함수
  createFreeBoardComment(data) {
    return http.post(`/normal/board/free/save/comment`, data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 대댓글 조회 : freeBoardCommentId
  getFreeBoardRecomment(freeBoardId) {
    return http.get(`/normal/board/free/recomments/${freeBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: 대댓글 저장 함수
  createFreeBoardRecomment(data) {
    return http.post(`/normal/board/free/save/recomment`, data, {
      headers: AuthHeader(),
    });
  }
  // TODO: userId로 전체조회 내글찾기
  getAllFreeBoardUserId(userId, page, size) {
    return http.get(
      `/normal/board/free/userId/freeBoard?userId=${userId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }

  reportFreeBoard(data) {
    return http.post("normal/board/free/report/save", data, {
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
  deleteFreeBoardReport(reportId,freeBoardId) {
    return http.delete(`/admin/report/free/deletion/${reportId}?freeBoardId=${freeBoardId}`, {
      headers: AuthHeader(),
    });
  }


  // TODO: 신고 게시글 취소 : 신고테이블 상태변경
  updateFreeBoardReport(freeBoardId,freeBoardReport) {
  
    console.log(freeBoardId);
    return http.put(`/admin/report/free/update/${freeBoardId}`, freeBoardReport, {
      headers: AuthHeader(),
    });
  }

  // TODO: 공지사항 조회
    //TODO: 자유만 조회
    getFreeNotice(size) {
      return http.get(`/normal/notice/notice-free?size=${size}`, {
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
