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
  deleteFreeBoard(freeBoardId){
    return http.delete(`/user/board/free/deletion/${freeBoardId}`, {
      headers: AuthHeader(),
    });
  }
  //   // TODO: 저장
  //   create(data) {
  //     console.log(data);
  //     return http.post("/admin/notice/notice-add", data);
  //   }
}

export default new FreeBoardService();
