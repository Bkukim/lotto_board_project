import http from "@/utils/http-common"; // spring 통신 정의 파일

class FreeBoardService {
  // TODO: 전체 조회
    getAllBoard(title, page, size) {
      return http.get(`/user/board/free?title=${title}&page=${page}&size=${size}`
      );
    }
//   // TODO: 저장
//   create(data) {
//     console.log(data);
//     return http.post("/admin/notice/notice-add", data);
//   }
}

export default new FreeBoardService();
