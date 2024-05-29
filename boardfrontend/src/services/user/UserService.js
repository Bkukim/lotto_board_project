
import http from "@/utils/http-common";
import AuthHeader from "../auth/AuthHeader";

class UserService{
    // 회원 상세 조회 : 수정 페이지에서 사용
   get(userId){
    return http.get(`/user/${userId}`,{
      headers: AuthHeader()
    });
   }

    // 회원 정보 수정
   updateUser(data){
      return http.put(`/user/update/${data.userId}`,data,{
         headers: AuthHeader()
       });
   }

    // 회원 탈퇴 : 삭제 함수
   deleteUser(userId){
      return http.delete(`/user/delete/${userId}`,{
         headers: AuthHeader()
       });
   }
}
export default new UserService;