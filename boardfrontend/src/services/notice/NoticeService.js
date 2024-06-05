// noticeQna/FaqListService.js
// 목적 : 부서 CRUD 공통 함수들 정의
import http from "@/utils/http-common"; // spring 통신 정의 파일
import AuthHeader from "../auth/AuthHeader";

class NoticeService {
  // TODO: 전체 조회
  getAll(eventYn, title, page, size) {
    return http.get(
      `/normal/notice/all/${eventYn}?title=${title}&page=${page}&size=${size}`
    );
  }

  // todo: 상세조회
  getNotice(noticeId) {
    return http.get(`/normal/notice/${noticeId}`);
  }
  
  // todo: 관리자 권한 상세조회
  getAdminNotice(noticeId) {
    return http.get(`/admin/notice/${noticeId}`);
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
      return http.put(`/admin/notice/notice-update/${noticeId}`, data,
  // {
  //   headers: AuthHeader()
  // }
      );
    }  
    
    // TODO: 조회수update
    updateVeiws(noticeId, data) {
      return http.put(`/normal/notice/notice-update-views/${noticeId}`, data,
  // {
  //   headers: AuthHeader()
  // }
      );
    }

  //   // TODO: 관리자 :: 답변으로 수정함수 : qnaId,
  //   updateAnswer(faqId, data) {
  //     // TODO: 수정(update) -> put 방식 -> @PutMapping
  //     // TODO: 사용법 : http.put(`/컨트롤러함수url/${dno}`, 수정할객체)
  //     return http.put(`/admin/faq/updateanswer/${faqId}`, data,
  //     {
  //       headers: AuthHeader()
  //     }
  //     );
  //   }

  //   // 유저아이디로 전체조회 내글찾기
  //   getAllUserId(userId, page, size) {
  //     console.log("d유저아이디",userId);
  //     return http.get(`/user/member/userid/faq?userId=${userId}&page=${page}&size=${size}`,
  //     {
  //       headers: AuthHeader()
  //     }
  //     );
  //   }
  // TODO: 삭제함수 : noticeID
  delete(noticeId) {
    console.log("여긴서비스노티스아이디" + noticeId);
    return http.delete(`/admin/notice/notice-deletion/${noticeId}`);
  }
}

export default new NoticeService();
