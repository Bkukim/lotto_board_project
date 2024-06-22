package org.example.boardbackend.model.entity.board.dept;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeptBoardLike is a Querydsl query type for DeptBoardLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeptBoardLike extends EntityPathBase<DeptBoardLike> {

    private static final long serialVersionUID = -1084101877L;

    public static final QDeptBoardLike deptBoardLike = new QDeptBoardLike("deptBoardLike");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final NumberPath<Long> deptBoardId = createNumber("deptBoardId", Long.class);

    public final StringPath insertTime = createString("insertTime");

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public QDeptBoardLike(String variable) {
        super(DeptBoardLike.class, forVariable(variable));
    }

    public QDeptBoardLike(Path<? extends DeptBoardLike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeptBoardLike(PathMetadata metadata) {
        super(DeptBoardLike.class, metadata);
    }

}

