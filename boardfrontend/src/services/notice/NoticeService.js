// noticeQna/FaqListService.js
// 목적 : 부서 CRUD 공통 함수들 정의
import http from "@/utils/http-common"; // spring 통신 정의 파일
// import AuthHeader from "../auth/AuthHeader";


class NoticeService {
  // TODO: 전체 조회
//   getAll(title, page, size) {
//     return http.get(`/user/member/faq?title=${title}&page=${page}&size=${size}`,
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

  
  // TODO: 저장, 이미지는 포기하라...
  create(data) {
    console.log(data);
    return http.post("/admin/notice/notice-add", data
    // {
    //   headers: AuthHeader()
    // }
    );
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

//   // TODO: 삭제함수 : 부서번호(dno)
//   // TODO: 삭제(delete) -> delete 방식 -> @DeleteMapping
//   // TODO: 사용법 : http.delete(`/컨트롤러함수url/${dno}`)
//   delete(faqId) {
//     console.log(faqId)
//     return http.delete(`/user/member/faq/deletion/${faqId}`,
//     {
//       headers: AuthHeader()
//     }
//     );
//   }

  
}

export default new NoticeService();
