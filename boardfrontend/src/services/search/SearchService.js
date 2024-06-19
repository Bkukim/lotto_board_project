// noticeQna/FaqListService.js
// 목적 : 부서 CRUD 공통 함수들 정의
import http from "@/utils/http-common"; // spring 통신 정의 파일
import AuthHeader from "../auth/AuthHeader";
// import AuthHeader from "../auth/AuthHeader";

class SearchService {

  // TODO: userId로 알림 전체 조회
 
  searchAll(title,page,size) {
   
    return http.get(`/user/search-all?title=${title}&page=${page}&size=${size}`,
    {
      headers: AuthHeader(),
    }
    );
  }
  
}

export default new SearchService();
