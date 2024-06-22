package org.example.boardbackend.model.entity.board.club;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClubBoard is a Querydsl query type for ClubBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubBoard extends EntityPathBase<ClubBoard> {

    private static final long serialVersionUID = 353719988L;

    public static final QClubBoard clubBoard = new QClubBoard("clubBoard");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final StringPath address = createString("address");

    public final NumberPath<Long> clubBoardId = createNumber("clubBoardId", Long.class);

    public final StringPath content = createString("content");

    public final StringPath endTime = createString("endTime");

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    public final StringPath location = createString("location");

    public final StringPath matchForm = createString("matchForm");

    public final StringPath material = createString("material");

    public final NumberPath<Long> maxQuota = createNumber("maxQuota", Long.class);

    public final NumberPath<Long> minQuota = createNumber("minQuota", Long.class);

    public final NumberPath<Long> participationFee = createNumber("participationFee", Long.class);

    public final StringPath peoplesMatch = createString("peoplesMatch");

    public final StringPath recruitmentDeadline = createString("recruitmentDeadline");

    public final StringPath sex = createString("sex");

    public final StringPath startTime = createString("startTime");

    public final StringPath title = createString("title");

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public QClubBoard(String variable) {
        super(ClubBoard.class, forVariable(variable));
    }

    public QClubBoard(Path<? extends ClubBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClubBoard(PathMetadata metadata) {
        super(ClubBoard.class, metadata);
    }

}

