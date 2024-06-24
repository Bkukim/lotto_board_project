package org.example.boardbackend.model.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseTimeEntity2 is a Querydsl query type for BaseTimeEntity2
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseTimeEntity2 extends EntityPathBase<BaseTimeEntity2> {

    private static final long serialVersionUID = 1345372357L;

    public static final QBaseTimeEntity2 baseTimeEntity2 = new QBaseTimeEntity2("baseTimeEntity2");

    public final StringPath insertTime = createString("insertTime");

    public final StringPath updateTime = createString("updateTime");

    public final StringPath withdrawTime = createString("withdrawTime");

    public final StringPath withdrawYn = createString("withdrawYn");

    public QBaseTimeEntity2(String variable) {
        super(BaseTimeEntity2.class, forVariable(variable));
    }

    public QBaseTimeEntity2(Path<? extends BaseTimeEntity2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseTimeEntity2(PathMetadata metadata) {
        super(BaseTimeEntity2.class, metadata);
    }

}

