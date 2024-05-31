import http from "@/utils/http-common"; // spring 통신 정의 파일
import AuthHeader from "@/services/auth/AuthHeader";

class ComplaintService {
  // TODO: 전체 조회
  getAllComplaintBoard(title, page, size) {
    return http.get(
      `/user/board/complaint?title=${title}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }
// //   TODO: 상세 조회
//   getComplaintBoardId(freeBoardId) {
//     return http.get(`/user/board/free/${freeBoardId}`, {
//       headers: AuthHeader(),
//     });
//   }

//   // TODO: 삭제
//   deleteComplaintBoard(freeBoardId){
//     return http.delete(`/user/board/free/deletion/${freeBoardId}`, {
//       headers: AuthHeader(),
//     });
//   }
  //   // TODO: 저장
  //   create(data) {
  //     console.log(data);
  //     return http.post("/admin/notice/notice-add", data);
  //   }
}

export default new ComplaintService();
