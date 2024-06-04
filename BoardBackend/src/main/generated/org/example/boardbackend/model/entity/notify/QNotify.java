package org.example.boardbackend.model.entity.notify;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotify is a Querydsl query type for Notify
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotify extends EntityPathBase<Notify> {

    private static final long serialVersionUID = -1491746310L;

    public static final QNotify notify = new QNotify("notify");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final StringPath isRead = createString("isRead");

    public final EnumPath<Notify.NotificationType> notificationType = createEnum("notificationType", Notify.NotificationType.class);

    public final NumberPath<Long> notifyId = createNumber("notifyId", Long.class);

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath url = createString("url");

    public final StringPath userId = createString("userId");

    public QNotify(String variable) {
        super(Notify.class, forVariable(variable));
    }

    public QNotify(Path<? extends Notify> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotify(PathMetadata metadata) {
        super(Notify.class, metadata);
    }

}

