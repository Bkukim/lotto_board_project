package org.example.boardbackend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName : org.example.boardbackend.constant
 * fileName : DeptCode
 * author : PC
 * date : 2024-06-05
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-05         PC          최초 생성
 */
@Getter
@NoArgsConstructor
public enum DeptCode {
    A0001("회계부"),
    B0002("재정부"),
    C0003("기획부"),
    D0004("홍보부");

    private String code;
    DeptCode(String code) {
        this.code = code;
    }
}
