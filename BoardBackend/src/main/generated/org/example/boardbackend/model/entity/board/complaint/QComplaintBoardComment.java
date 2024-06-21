package org.example.boardbackend.model.entity.board.complaint;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComplaintBoardComment is a Querydsl query type for ComplaintBoardComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComplaintBoardComment extends EntityPathBase<ComplaintBoardComment> {

    private static final long serialVersionUID = 273970437L;

    public static final QComplaintBoardComment complaintBoardComment = new QComplaintBoardComment("complaintBoardComment");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final NumberPath<Long> complaintBoardCommentId = createNumber("complaintBoardCommentId", Long.class);

    public final NumberPath<Long> complaintBoardId = createNumber("complaintBoardId", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath insertTime = _super.insertTime;

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public QComplaintBoardComment(String variable) {
        super(ComplaintBoardComment.class, forVariable(variable));
    }

    public QComplaintBoardComment(Path<? extends ComplaintBoardComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComplaintBoardComment(PathMetadata metadata) {
        super(ComplaintBoardComment.class, metadata);
    }

}

