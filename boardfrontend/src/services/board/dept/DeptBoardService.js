import http from "@/utils/http-common"; // spring 통신 정의 파일
import AuthHeader from "@/services/auth/AuthHeader";

class DeptBoardService {
  // TODO: 게시판 전체 조회
  getAllDeptBoard(title, deptId, page, size) {
    return http.get(
      `/normal/dept/board?title=${title}&deptId=${deptId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }
  //   TODO: 게시판 상세 조회
  getDeptBoardId(deptBoardId) {
    return http.get(`/normal/dept/board/${deptBoardId}`, {
      headers: AuthHeader(),
    });
  }

  // TODO:게시판 삭제
  deleteDeptBoard(deptBoardId) {
    return http.delete(`/normal/dept/board/deletion/${deptBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: 게시판 저장
  createDeptBoard(data) {
    console.log(data);
    return http.post("/normal/dept/board/save", data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 게시판 수정함수
  updateDeptBoard(deptBoardId, data) {
    return http.put(`/normal/dept/board/update/${deptBoardId}`, data, {
      headers: AuthHeader(),
    });
  }

  // TODO: 좋아요 수정 함수
  updateLike(deptBoardId, data) {
    return http.put(`/normal/dept/like/update/${deptBoardId}`, data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 댓글 조회
  getDeptBoardComment(deptBoardId, page, size) {
    return http.get(
      `/normal/dept/comment?deptBoardId=${deptBoardId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }
  // TODO: 댓글 저장 함수
  createDeptBoardComment(data) {
    return http.post(`/normal/dept/comment/save`, data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 대댓글 조회
  getDeptBoardRecomment(deptBoardId) {
    return http.get(`/normal/dept/recomment?deptBoardId=${deptBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: 대댓글 저장 함수
  createDeptBoardRecomment(data) {
    return http.post(`/normal/dept/recomment/save`, data, {
      headers: AuthHeader(),
    });
  }
  // todo : 부서 전체 조회 함수
  findAllDepartment() {
    return http.get("/normal/dept/department", {
      headers: AuthHeader(),
    });
  }
  // todo : 부서 상세 조회 함수
  findDepartmentByDeptId(deptId) {
    return http.get(`/normal/dept/department/${deptId}`, {
      headers: AuthHeader(),
    });
  }

  // TODO: userId로 전체조회 내글찾기
  getAllDeptBoardUserId(userId, page, size) {
    return http.get(
      `/normal/dept/userId/deptBoard?userId=${userId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }

  //todo 부서 신고
  reportDeptBoard(data) {
    return http.post("normal/dept/report/save", data, {
      headers: AuthHeader(),
    });
  }

  // TODO: 신고 게시글 전체 조회
  getAllDeptBoardReport(userId, page, size) {
    return http.get(
      `/admin/report/dept?userId=${userId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }

  // TODO: 신고 게시글 처리완료 전체 조회
  getAllDeptBoardReportProcessed(userId, page, size) {
    return http.get(
      `/admin/report/dept/processed?userId=${userId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }

  // TODO: 신고 게시글 삭제 : 자유게시판 삭제, 신고테이블 상태변경
  deleteDeptBoardReport(reportId,deptBoardId) {
    return http.delete(`/admin/report/dept/deletion/${reportId}?deptBoardId=${deptBoardId}`, {
      headers: AuthHeader(),
    });
  }

  // TODO: 신고 게시글 취소 : 신고테이블 상태변경
  updateDeptBoardReport(reportId,deptBoardReport) {
    return http.put(`/admin/report/dept/update/${reportId}`,deptBoardReport, {
      headers: AuthHeader(),
    });
  }

  // TODO: 공지사항 부서만 조회
  getDeptNotice(size) {
    return http.get(`/normal/notice/notice-dept?size=${size}`, {
      headers: AuthHeader(),
    });
  }
}

export default new DeptBoardService();
