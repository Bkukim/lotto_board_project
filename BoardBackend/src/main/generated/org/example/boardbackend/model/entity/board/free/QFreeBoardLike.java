package org.example.boardbackend.model.entity.board.free;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFreeBoardLike is a Querydsl query type for FreeBoardLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoardLike extends EntityPathBase<FreeBoardLike> {

    private static final long serialVersionUID = -152021333L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFreeBoardLike freeBoardLike = new QFreeBoardLike("freeBoardLike");

    public final QFreeBoard freeBoard;

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    public final org.example.boardbackend.model.entity.auth.QUser user;

    public QFreeBoardLike(String variable) {
        this(FreeBoardLike.class, forVariable(variable), INITS);
    }

    public QFreeBoardLike(Path<? extends FreeBoardLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFreeBoardLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFreeBoardLike(PathMetadata metadata, PathInits inits) {
        this(FreeBoardLike.class, metadata, inits);
    }

    public QFreeBoardLike(Class<? extends FreeBoardLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.freeBoard = inits.isInitialized("freeBoard") ? new QFreeBoard(forProperty("freeBoard")) : null;
        this.user = inits.isInitialized("user") ? new org.example.boardbackend.model.entity.auth.QUser(forProperty("user")) : null;
    }

}

