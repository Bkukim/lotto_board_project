package org.example.boardbackend.model.entity.board.dept;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeptBoard is a Querydsl query type for DeptBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeptBoard extends EntityPathBase<DeptBoard> {

    private static final long serialVersionUID = -1773235884L;

    public static final QDeptBoard deptBoard = new QDeptBoard("deptBoard");

    public final org.example.boardbackend.model.common.QBaseTimeEntity3 _super = new org.example.boardbackend.model.common.QBaseTimeEntity3(this);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath deleteYn = _super.deleteYn;

    public final NumberPath<Long> deptBoardId = createNumber("deptBoardId", Long.class);

    public final StringPath deptId = createString("deptId");

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    public final StringPath title = createString("title");

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public QDeptBoard(String variable) {
        super(DeptBoard.class, forVariable(variable));
    }

    public QDeptBoard(Path<? extends DeptBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeptBoard(PathMetadata metadata) {
        super(DeptBoard.class, metadata);
    }

}

