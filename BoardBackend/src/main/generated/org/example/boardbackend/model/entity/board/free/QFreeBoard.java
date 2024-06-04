package org.example.boardbackend.model.entity.board.free;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFreeBoard is a Querydsl query type for FreeBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoard extends EntityPathBase<FreeBoard> {

    private static final long serialVersionUID = 2015612148L;

    public static final QFreeBoard freeBoard = new QFreeBoard("freeBoard");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> freeBoardId = createNumber("freeBoardId", Long.class);

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    public final StringPath title = createString("title");

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public QFreeBoard(String variable) {
        super(FreeBoard.class, forVariable(variable));
    }

    public QFreeBoard(Path<? extends FreeBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreeBoard(PathMetadata metadata) {
        super(FreeBoard.class, metadata);
    }

}

