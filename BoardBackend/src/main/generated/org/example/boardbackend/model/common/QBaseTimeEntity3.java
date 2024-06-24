package org.example.boardbackend.model.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseTimeEntity3 is a Querydsl query type for BaseTimeEntity3
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseTimeEntity3 extends EntityPathBase<BaseTimeEntity3> {

    private static final long serialVersionUID = 1345372358L;

    public static final QBaseTimeEntity3 baseTimeEntity3 = new QBaseTimeEntity3("baseTimeEntity3");

    public final StringPath deleteYn = createString("deleteYn");

    public final StringPath insertTime = createString("insertTime");

    public final StringPath updateTime = createString("updateTime");

    public QBaseTimeEntity3(String variable) {
        super(BaseTimeEntity3.class, forVariable(variable));
    }

    public QBaseTimeEntity3(Path<? extends BaseTimeEntity3> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseTimeEntity3(PathMetadata metadata) {
        super(BaseTimeEntity3.class, metadata);
    }

}

