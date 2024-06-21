package org.example.boardbackend.model.entity.board.free;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFreeBoardComment is a Querydsl query type for FreeBoardComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoardComment extends EntityPathBase<FreeBoardComment> {

    private static final long serialVersionUID = -1195639093L;

    public static final QFreeBoardComment freeBoardComment = new QFreeBoardComment("freeBoardComment");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> freeBoardCommentId = createNumber("freeBoardCommentId", Long.class);

    public final NumberPath<Long> freeBoardId = createNumber("freeBoardId", Long.class);

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final StringPath secretCommentYn = createString("secretCommentYn");

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public QFreeBoardComment(String variable) {
        super(FreeBoardComment.class, forVariable(variable));
    }

    public QFreeBoardComment(Path<? extends FreeBoardComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreeBoardComment(PathMetadata metadata) {
        super(FreeBoardComment.class, metadata);
    }

}

