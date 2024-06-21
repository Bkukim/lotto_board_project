package org.example.boardbackend.model.entity.board.dept;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeptComment is a Querydsl query type for DeptComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeptComment extends EntityPathBase<DeptComment> {

    private static final long serialVersionUID = 1920773453L;

    public static final QDeptComment deptComment = new QDeptComment("deptComment");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> deptBoardCommentId = createNumber("deptBoardCommentId", Long.class);

    public final NumberPath<Long> deptBoardId = createNumber("deptBoardId", Long.class);

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final StringPath secretCommentYN = createString("secretCommentYN");

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public QDeptComment(String variable) {
        super(DeptComment.class, forVariable(variable));
    }

    public QDeptComment(Path<? extends DeptComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeptComment(PathMetadata metadata) {
        super(DeptComment.class, metadata);
    }

}

