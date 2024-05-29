import http from "@/utils/http-common";
import AuthHeader from "../auth/AuthHeader";

class PasswordResetService{
    // 비밀번호 변경 : 이메일로 코드 보내기
    sendCode(email, userId){
    return http.put(`/sendEmail?email=${email}&userId=${userId}`,{
      headers: AuthHeader()
    });
   }
}
export default new PasswordResetService;
