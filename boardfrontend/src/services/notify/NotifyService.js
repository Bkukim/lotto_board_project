// noticeQna/FaqListService.js
// 목적 : 부서 CRUD 공통 함수들 정의
import http from "@/utils/http-common"; // spring 통신 정의 파일
import AuthHeader from "../auth/AuthHeader";
// import AuthHeader from "../auth/AuthHeader";

class NotifyService {
  // TODO: userId로 알림 전체 조회

  getUnreadNotify(userId) {
    return http.get(`/v1/notify/unread-messages/${userId}`, {
      headers: AuthHeader(),
    });
  }
  markAllAsRead(userId) {
    return http.put(`/v1/notify/mark/all/read/${userId}`, {
      headers: AuthHeader(),
    });
  }
  markAsRead(notifyId) {
    let data={}
    return http.put(`/v1/notify/mark/read/${notifyId}`, data,{
      headers: AuthHeader(),
    });
  }
  countNotify(userId) {
    return http.get(`/v1/notify/count-unread/${userId}`, {
      headers: AuthHeader(),
    });
  }
}

export default new NotifyService();
