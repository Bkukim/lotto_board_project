package org.example.boardbackend.model.entity.board.dept;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeptBoardReport is a Querydsl query type for DeptBoardReport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeptBoardReport extends EntityPathBase<DeptBoardReport> {

    private static final long serialVersionUID = 2023392168L;

    public static final QDeptBoardReport deptBoardReport = new QDeptBoardReport("deptBoardReport");

    public final StringPath comment = createString("comment");

    public final NumberPath<Long> deptBoardId = createNumber("deptBoardId", Long.class);

    public final NumberPath<Long> reportId = createNumber("reportId", Long.class);

    public final StringPath userId = createString("userId");

    public QDeptBoardReport(String variable) {
        super(DeptBoardReport.class, forVariable(variable));
    }

    public QDeptBoardReport(Path<? extends DeptBoardReport> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeptBoardReport(PathMetadata metadata) {
        super(DeptBoardReport.class, metadata);
    }

}

