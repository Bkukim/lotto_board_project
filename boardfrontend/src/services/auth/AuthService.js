import http from "@/utils/http-common.js";

class AuthService {
  // 로그인 공통 서비스 함수 정의
  login(user) {
    // 임시 객체 만들기,보안을 위해 post로 이것을 백엔드로전달한다.
    let data = {
      userId: user.userId, // 로그인id
      password: user.password, // 암호
    };
    return http.post("/auth/login", data);
  }

  // 카카오 로그인
  socialLogin(code) {
    // 임시 객체 만들기,보안을 위해 post로 이것을 백엔드로전달한다.
    return http.post(`/auth/kakao-login/${code}`);
  }

  naverLogin(code) {
    return http.post(`/auth/naver-login/${code}`);
  }

  logout() {
    // 로컬 스토리지의 값을 삭제
    // 사용법 : localStorage.removeIItem("키이름")
    localStorage.removeItem("user");
  }

  register(data) {
    return http.post("/auth/register", data);
  }

  // id 찾기 함수
  findId(role, userName, phoneNum) {
    return http.get(`/auth/findId/${role}/${userName}/${phoneNum}`);
  }

  // // pw새로 만들기 위해서 사용자 확인함수
  // getForPw(role, userId, pwQuestion, pwAnswer) {
  //   return http.get(
  //     `/auth/getForPw/${role}/${userId}/${pwQuestion}/${pwAnswer}`
  //   );
  // }

  // // pw 변경 : 이메일로 코드 보내기
  // getForPw(role, userId, email) {
  //   return http.get(
  //     `/auth/getForPw/${role}/${userId}/${email}`
  //   );
  // }

  // 새로운 비밀번호 변경함수
  updatePw(userId, newPw) {
    return http.put(`/auth/new-pw/${userId}`, newPw);
  }

  // 아이디 중복확인함수
  existUserById(userId){
    return http.get(`/auth/exist-id/${userId}`);
 }
}

export default new AuthService();
