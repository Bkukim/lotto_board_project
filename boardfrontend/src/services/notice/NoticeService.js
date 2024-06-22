// noticeQna/FaqListService.js
// 목적 : 부서 CRUD 공통 함수들 정의
import http from "@/utils/http-common"; // spring 통신 정의 파일
import AuthHeader from "../auth/AuthHeader";

class NoticeService {
  // TODO: 노말서비스
  getAllMain() {
    return http.get(`/normal/notice/main-get`, {
      headers: AuthHeader(),
    });
  }

  getAll(title, page, size) {
    return http.get(
      `/normal/notice/all?title=${title}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }

  // todo: 상세조회
  getNotice(noticeId, eventYN) {
    return http.get(`/normal/notice/${noticeId}?eventYN=${eventYN}`, {
      headers: AuthHeader(),
    });
  }

  // TODO: 조회수update
  updateVeiws(noticeId, data) {
    console.log("서비스", data);
    return http.put(`/normal/notice/notice-update-views/${noticeId}`, data, {
      headers: AuthHeader(),
    });
  }

  //TODO: 관리자 서비스
  getAdminNotice(noticeId) {
    return http.get(`/admin/notice/${noticeId}`, {
      headers: AuthHeader(),
    });
  }

  //TODO: [전체]만 조회
  getAllNotice(size) {
    return http.get(`/admin/notice/master-all?size=${size}`, {
      headers: AuthHeader(),
    });
  }

  //TODO: 부서만 조회
  getDept(size) {
    return http.get(`/admin/notice/master-dept?size=${size}`, {
      headers: AuthHeader(),
    });
  }

  //TODO: 자유만 조회
  getFree(size) {
    return http.get(`/admin/notice/master-free?size=${size}`, {
      headers: AuthHeader(),
    });
  }

  //TODO: 건의만 조회
  getComplaint(size) {
    return http.get(`/admin/notice/master-complaint?size=${size}`, {
      headers: AuthHeader(),
    });
  }
  //TODO: 동호회만 조회
  getGroup(size) {
    return http.get(`/admin/notice/master-group?size=${size}`, {
      headers: AuthHeader(),
    });
  }

  // TODO: 저장
  create(data) {
    console.log(data);
    return http.post("/admin/notice/notice-add", data, {
      headers: AuthHeader(),
    });
  }

  // TODO: 수정함수
  update(noticeId, data) {
    return http.put(`/admin/notice/notice-update/${noticeId}`, data, {
      headers: AuthHeader(),
    });
  }

  // TODO: 삭제함수 : noticeId
  delete(noticeId) {
    return http.delete(`/admin/notice/notice-deletion/${noticeId}`, {
      headers: AuthHeader(),
    });
  }
  // 전체 삭제함수 : notice
  deleteAll() {
    return http.delete(`/admin/notice/delete-all`, {
      headers: AuthHeader(),
    });
  }
}

export default new NoticeService();
