package org.example.boardbackend.repository.dept;

import org.example.boardbackend.model.entity.dept.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.boardbackend.repository.dept
 * fileName : DepartmentRepository
 * author : PC
 * date : 2024-06-07
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-07         PC          최초 생성
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department,String> {

}
