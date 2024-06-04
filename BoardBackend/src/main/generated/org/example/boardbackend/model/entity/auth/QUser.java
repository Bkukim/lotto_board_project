package org.example.boardbackend.model.entity.auth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 64835261L;

    public static final QUser user = new QUser("user");

    public final org.example.boardbackend.model.common.QBaseTimeEntity2 _super = new org.example.boardbackend.model.common.QBaseTimeEntity2(this);

    public final NumberPath<Long> birthday = createNumber("birthday", Long.class);

    public final StringPath dept = createString("dept");

    public final StringPath detailAddress = createString("detailAddress");

    public final StringPath email = createString("email");

    //inherited
    public final StringPath insertTime = _super.insertTime;

    public final StringPath normalAddress = createString("normalAddress");

    public final StringPath password = createString("password");

    public final StringPath phoneNum = createString("phoneNum");

    public final StringPath pwAnswer = createString("pwAnswer");

    public final StringPath pwQuestion = createString("pwQuestion");

    public final StringPath role = createString("role");

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    //inherited
    public final StringPath withdrawTime = _super.withdrawTime;

    //inherited
    public final StringPath withdrawYn = _super.withdrawYn;

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

