package org.example.boardbackend.model.entity.board.complaint;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComplaintBoardLike is a Querydsl query type for ComplaintBoardLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComplaintBoardLike extends EntityPathBase<ComplaintBoardLike> {

    private static final long serialVersionUID = -778822991L;

    public static final QComplaintBoardLike complaintBoardLike = new QComplaintBoardLike("complaintBoardLike");

    public final NumberPath<Long> complaintBoardId = createNumber("complaintBoardId", Long.class);

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    public final StringPath userId = createString("userId");

    public QComplaintBoardLike(String variable) {
        super(ComplaintBoardLike.class, forVariable(variable));
    }

    public QComplaintBoardLike(Path<? extends ComplaintBoardLike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComplaintBoardLike(PathMetadata metadata) {
        super(ComplaintBoardLike.class, metadata);
    }

}

