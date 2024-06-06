package org.example.boardbackend.model.entity.notice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = -860818662L;

    public static final QNotice notice = new QNotice("notice");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> eventWinnerQuota = createNumber("eventWinnerQuota", Long.class);

    public final StringPath eventYn = createString("eventYn");

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final NumberPath<Long> noticeId = createNumber("noticeId", Long.class);

    public final StringPath noticeType = createString("noticeType");

    public final StringPath title = createString("title");

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final NumberPath<Long> views = createNumber("views", Long.class);

    public QNotice(String variable) {
        super(Notice.class, forVariable(variable));
    }

    public QNotice(Path<? extends Notice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotice(PathMetadata metadata) {
        super(Notice.class, metadata);
    }

}

