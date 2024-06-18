import http from "@/utils/http-common"; // spring 통신 정의 파일
import AuthHeader from "@/services/auth/AuthHeader";

class DeptBoardService {
  // TODO: 게시판 전체 조회
  getAllDeptBoard(title, deptId, page, size) {
    return http.get(
      `/user/dept/board?title=${title}&deptId=${deptId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }
  //   TODO: 게시판 상세 조회
  getDeptBoardId(deptBoardId) {
    return http.get(`/user/dept/board/${deptBoardId}`, {
      headers: AuthHeader(),
    });
  }

  // TODO:게시판 삭제
  deleteDeptBoard(deptBoardId) {
    return http.delete(`/user/dept/board/deletion/${deptBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: 게시판 저장
  createDeptBoard(data) {
    console.log(data);
    return http.post("/user/dept/board/save", data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 게시판 수정함수
  updateDeptBoard(deptBoardId, data) {
    return http.put(`/user/dept/board/update/${deptBoardId}`, data, {
      headers: AuthHeader(),
    });
  }

  // TODO: 좋아요 수정 함수
  updateLike(deptBoardId, data) {
    return http.put(`/user/dept/like/update/${deptBoardId}`, data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 댓글 조회
  getDeptBoardComment(deptBoardId, page, size) {
    return http.get(
      `/user/dept/comment?deptBoardId=${deptBoardId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }
  // TODO: 댓글 저장 함수
  createDeptBoardComment(data) {
    return http.post(`/user/dept/comment/save`, data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 대댓글 조회
  getDeptBoardRecomment(deptBoardId) {
    return http.get(`/user/dept/recomment?deptBoardId=${deptBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: 대댓글 저장 함수
  createDeptBoardRecomment(data) {
    return http.post(`/user/dept/recomment/save`, data, {
      headers: AuthHeader(),
    });
  }
  // todo : 부서 전체 조회 함수
  findAllDepartment() {
    return http.get("/user/dept/department", {
      headers: AuthHeader(),
    });
  }
  // todo : 부서 상세 조회 함수
  findDepartmentByDeptId(deptId) {
    return http.get(`/user/dept/department/${deptId}`, {
      headers: AuthHeader(),
    });
  }

  // TODO: userId로 전체조회 내글찾기
  getAllDeptBoardUserId(userId, page, size) {
    return http.get(
      `/user/dept/userId/deptBoard?userId=${userId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
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
}

export default new DeptBoardService();