package org.example.boardbackend.model.entity.board.club;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFieldPic is a Querydsl query type for FieldPic
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFieldPic extends EntityPathBase<FieldPic> {

    private static final long serialVersionUID = -1057106228L;

    public static final QFieldPic fieldPic = new QFieldPic("fieldPic");

    public final NumberPath<Long> clubBoardId = createNumber("clubBoardId", Long.class);

    public final ArrayPath<byte[], Byte> imgFile = createArray("imgFile", byte[].class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath uuid = createString("uuid");

    public QFieldPic(String variable) {
        super(FieldPic.class, forVariable(variable));
    }

    public QFieldPic(Path<? extends FieldPic> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFieldPic(PathMetadata metadata) {
        super(FieldPic.class, metadata);
    }

}

