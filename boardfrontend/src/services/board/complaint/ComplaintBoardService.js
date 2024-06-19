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
  //   TODO: 상세 조회
  getComplaintBoardId(complaintBoardId) {
    return http.get(`/user/board/complaint/${complaintBoardId}`, {
      headers: AuthHeader(),
    });
  }

  // TODO: 삭제
  deleteComplaintBoard(complaintBoardId) {
    return http.delete(`/user/board/complaint/deletion/${complaintBoardId}`, {
      headers: AuthHeader(),
    });
  }
  // TODO: 저장
  createComplaintBoard(data) {
    console.log(data);
    return http.post("/user/board/complaint/save", data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 수정함수
  updateComplaintBoard(complaintBoardId, data) {
    return http.put(`/user/board/complaint/update/${complaintBoardId}`, data, {
      headers: AuthHeader(),
    });
  }
  // TODO: 댓글 조회 : complaintBoardId
  getComplaintBoardComment(complaintBoardId, page, size) {
    return http.get(
      `/user/board/complaint/${complaintBoardId}/comments?page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }
  // TODO: 댓글 저장 함수
  createComplaintBoardComment(data) {
    return http.post(`/admin/board/complaint/save/comment`, data, {
      headers: AuthHeader(),
    });
  }
  // TODO: userId로 전체조회 내글찾기
  getAllComplaintBoardUserId(userId, page, size) {
    return http.get(
      `/user/board/complaint/userId/complaintBoard?userId=${userId}&page=${page}&size=${size}`,
      {
        headers: AuthHeader(),
      }
    );
  }
  // TODO: 댓글 삭제
  deleteComplaintBoardComment(commentId) {
    return http.delete(`/admin/board/complaint/delete/comment/${commentId}`, {
      headers: AuthHeader(),
    });
  }
}

export default new ComplaintService();
