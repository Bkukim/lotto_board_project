package org.example.boardbackend.model.entity.notice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWinner is a Querydsl query type for Winner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWinner extends EntityPathBase<Winner> {

    private static final long serialVersionUID = -608871295L;

    public static final QWinner winner = new QWinner("winner");

    public final EnumPath<org.example.boardbackend.constant.Event> event_type = createEnum("event_type", org.example.boardbackend.constant.Event.class);

    public final NumberPath<Long> notice_id = createNumber("notice_id", Long.class);

    public final StringPath user_id = createString("user_id");

    public final NumberPath<Long> winner_id = createNumber("winner_id", Long.class);

    public QWinner(String variable) {
        super(Winner.class, forVariable(variable));
    }

    public QWinner(Path<? extends Winner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWinner(PathMetadata metadata) {
        super(Winner.class, metadata);
    }

}

