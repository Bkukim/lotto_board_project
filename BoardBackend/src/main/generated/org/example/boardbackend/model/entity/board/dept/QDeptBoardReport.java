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

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> deptBoardId = createNumber("deptBoardId", Long.class);

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final NumberPath<Long> reportId = createNumber("reportId", Long.class);

    //inherited
    public final StringPath updateTime = _super.updateTime;

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

