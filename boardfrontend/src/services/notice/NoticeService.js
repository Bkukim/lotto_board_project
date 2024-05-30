// noticeQna/FaqListService.js
// 목적 : 부서 CRUD 공통 함수들 정의
import http from "@/utils/http-common"; // spring 통신 정의 파일
// import AuthHeader from "../auth/AuthHeader";

class NoticeService {
  // TODO: 전체 조회
    getAll(eventYn, title, page, size) {
      return http.get(`/normal/notice/all/${eventYn}?title=${title}&page=${page}&size=${size}`
      );
    }
    
    //TODO: 부서만 조회
    getDept(size) {
      return http.get(`/admin/notice/master-dept?size=${size}`);
    }



  // TODO: 저장
  create(data) {
    console.log(data);
    return http.post("/admin/notice/notice-add", data);
  }

  // todo: 상세조회
  getNotice(noticeId) {
    return http.get(`/normal/notice/${noticeId}`);
  }

  //   // TODO: 수정함수 : qnaId,
  //   update(faqId, data) {
  //     // TODO: 수정(update) -> put 방식 -> @PutMapping
  //     // TODO: 사용법 : http.put(`/컨트롤러함수url/${dno}`, 수정할객체)
  //     return http.put(`/user/mdember/faq/update/${faqId}`, data,
  //     {
  //       headers: AuthHeader()
  //     }
  //     );
  //   }

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
      console.log(noticeId)
      return http.delete(`/admin/notice/notice-deletion/${noticeId}`,
      );
    }
}

export default new NoticeService();
