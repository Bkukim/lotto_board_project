package org.example.boardbackend.model.entity.board.complaint;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComplaintBoard is a Querydsl query type for ComplaintBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComplaintBoard extends EntityPathBase<ComplaintBoard> {

    private static final long serialVersionUID = 1353090042L;

    public static final QComplaintBoard complaintBoard = new QComplaintBoard("complaintBoard");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final NumberPath<Long> complaintBoardId = createNumber("complaintBoardId", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public QComplaintBoard(String variable) {
        super(ComplaintBoard.class, forVariable(variable));
    }

    public QComplaintBoard(Path<? extends ComplaintBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComplaintBoard(PathMetadata metadata) {
        super(ComplaintBoard.class, metadata);
    }

}

