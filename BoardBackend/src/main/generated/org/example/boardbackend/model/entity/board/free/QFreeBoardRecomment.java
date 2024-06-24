package org.example.boardbackend.model.entity.board.free;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFreeBoardRecomment is a Querydsl query type for FreeBoardRecomment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoardRecomment extends EntityPathBase<FreeBoardRecomment> {

    private static final long serialVersionUID = 231323192L;

    public static final QFreeBoardRecomment freeBoardRecomment = new QFreeBoardRecomment("freeBoardRecomment");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> freeBoardCommentId = createNumber("freeBoardCommentId", Long.class);

    public final NumberPath<Long> freeBoardRecommentId = createNumber("freeBoardRecommentId", Long.class);

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final StringPath secretCommentYn = createString("secretCommentYn");

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public QFreeBoardRecomment(String variable) {
        super(FreeBoardRecomment.class, forVariable(variable));
    }

    public QFreeBoardRecomment(Path<? extends FreeBoardRecomment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreeBoardRecomment(PathMetadata metadata) {
        super(FreeBoardRecomment.class, metadata);
    }

}

