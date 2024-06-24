package org.example.boardbackend.model.entity.board.club;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QParticipants is a Querydsl query type for Participants
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParticipants extends EntityPathBase<Participants> {

    private static final long serialVersionUID = 1919410748L;

    public static final QParticipants participants = new QParticipants("participants");

    public final StringPath approval = createString("approval");

    public final NumberPath<Long> clubBoardId = createNumber("clubBoardId", Long.class);

    public final NumberPath<Long> participantsId = createNumber("participantsId", Long.class);

    public final StringPath userId = createString("userId");

    public QParticipants(String variable) {
        super(Participants.class, forVariable(variable));
    }

    public QParticipants(Path<? extends Participants> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParticipants(PathMetadata metadata) {
        super(Participants.class, metadata);
    }

}

