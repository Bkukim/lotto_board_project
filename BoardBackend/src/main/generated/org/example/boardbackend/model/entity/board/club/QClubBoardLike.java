package org.example.boardbackend.model.entity.board.club;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClubBoardLike is a Querydsl query type for ClubBoardLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubBoardLike extends EntityPathBase<ClubBoardLike> {

    private static final long serialVersionUID = 1216807019L;

    public static final QClubBoardLike clubBoardLike = new QClubBoardLike("clubBoardLike");

    public final NumberPath<Long> clubBoardId = createNumber("clubBoardId", Long.class);

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    public final StringPath userId = createString("userId");

    public QClubBoardLike(String variable) {
        super(ClubBoardLike.class, forVariable(variable));
    }

    public QClubBoardLike(Path<? extends ClubBoardLike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClubBoardLike(PathMetadata metadata) {
        super(ClubBoardLike.class, metadata);
    }

}

