package org.example.boardbackend.model.entity.board.club;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFieldPic is a Querydsl query type for FieldPic
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFieldPic extends EntityPathBase<FieldPic> {

    private static final long serialVersionUID = -1057106228L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFieldPic fieldPic = new QFieldPic("fieldPic");

    public final QClubBoard clubBoard;

    public final ArrayPath<byte[], Byte> imgFile = createArray("imgFile", byte[].class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath uuid = createString("uuid");

    public QFieldPic(String variable) {
        this(FieldPic.class, forVariable(variable), INITS);
    }

    public QFieldPic(Path<? extends FieldPic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFieldPic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFieldPic(PathMetadata metadata, PathInits inits) {
        this(FieldPic.class, metadata, inits);
    }

    public QFieldPic(Class<? extends FieldPic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clubBoard = inits.isInitialized("clubBoard") ? new QClubBoard(forProperty("clubBoard")) : null;
    }

}

