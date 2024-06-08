package org.example.boardbackend.service.dept;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.dept.Department;
import org.example.boardbackend.repository.dept.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * packageName : org.example.boardbackend.service.dept
 * fileName : DepartmentService
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
@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    // todo 전체조회
    public List<Department> findAllDepartment(){
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    // todo 상세조회
    public Department findDepartmentByDeptId(String deptId){
        Department department = departmentRepository.findById(deptId).get();
        return department;
    }
}
