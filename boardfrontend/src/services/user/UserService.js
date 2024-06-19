
import http from "@/utils/http-common";
import AuthHeader from "../auth/AuthHeader";

class UserService{
    // 회원 상세 조회 : 마이 페이지 프로필에서 사용
   get(userId){
    return http.get(`/normal/${userId}`,{
      headers: AuthHeader()
    });
   }

    // 회원 정보 수정 : 관리자가 수정할 때
   updateUser(data){
      return http.put(`/admin/user/update/${data.userId}`,data,{
         headers: AuthHeader()
       });
   }

    // 회원 삭제 함수 : 회원 탈퇴
   deleteUser(userId){
      return http.delete(`/admin/user/delete/${userId}`,{
         headers: AuthHeader()
       });
   }

     // userName로 회원찾기
  getAllUsersbyUserName(userName, page, size) {
   return http.get(
     `/admin/user/manage?userName=${userName}&page=${page}&size=${size}`,
     {
       headers: AuthHeader(),
     }
   );
 }
}
export default new UserService;
