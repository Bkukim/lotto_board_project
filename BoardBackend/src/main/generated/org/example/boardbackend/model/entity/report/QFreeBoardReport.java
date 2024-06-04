package org.example.boardbackend.model.entity.report;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFreeBoardReport is a Querydsl query type for FreeBoardReport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoardReport extends EntityPathBase<FreeBoardReport> {

    private static final long serialVersionUID = -792963576L;

    public static final QFreeBoardReport freeBoardReport = new QFreeBoardReport("freeBoardReport");

    public final org.example.boardbackend.model.common.QBaseTimeEntity _super = new org.example.boardbackend.model.common.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> freeBoardId = createNumber("freeBoardId", Long.class);

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final NumberPath<Long> reportId = createNumber("reportId", Long.class);

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public QFreeBoardReport(String variable) {
        super(FreeBoardReport.class, forVariable(variable));
    }

    public QFreeBoardReport(Path<? extends FreeBoardReport> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreeBoardReport(PathMetadata metadata) {
        super(FreeBoardReport.class, metadata);
    }

}

