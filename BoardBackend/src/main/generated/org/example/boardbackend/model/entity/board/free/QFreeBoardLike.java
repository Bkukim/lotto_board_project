package org.example.boardbackend.model.entity.board.free;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFreeBoardLike is a Querydsl query type for FreeBoardLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoardLike extends EntityPathBase<FreeBoardLike> {

    private static final long serialVersionUID = -152021333L;

    public static final QFreeBoardLike freeBoardLike = new QFreeBoardLike("freeBoardLike");

    public final NumberPath<Long> freeBoardId = createNumber("freeBoardId", Long.class);

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    public final StringPath userId = createString("userId");

    public QFreeBoardLike(String variable) {
        super(FreeBoardLike.class, forVariable(variable));
    }

    public QFreeBoardLike(Path<? extends FreeBoardLike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreeBoardLike(PathMetadata metadata) {
        super(FreeBoardLike.class, metadata);
    }

}

