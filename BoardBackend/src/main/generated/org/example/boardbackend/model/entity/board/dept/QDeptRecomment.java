package org.example.boardbackend.model.entity.board.dept;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeptRecomment is a Querydsl query type for DeptRecomment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeptRecomment extends EntityPathBase<DeptRecomment> {

    private static final long serialVersionUID = 1511574586L;

    public static final QDeptRecomment deptRecomment = new QDeptRecomment("deptRecomment");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final StringPath comment = createString("comment");

    public final StringPath content = createString("content");

    public final NumberPath<Long> deptBoardCommentId = createNumber("deptBoardCommentId", Long.class);

    public final NumberPath<Long> deptBoardRecommentId = createNumber("deptBoardRecommentId", Long.class);

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final StringPath secretCommentYN = createString("secretCommentYN");

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public QDeptRecomment(String variable) {
        super(DeptRecomment.class, forVariable(variable));
    }

    public QDeptRecomment(Path<? extends DeptRecomment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeptRecomment(PathMetadata metadata) {
        super(DeptRecomment.class, metadata);
    }

}

