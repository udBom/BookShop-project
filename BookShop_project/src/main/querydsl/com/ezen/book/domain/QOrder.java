package com.ezen.book.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 624764326L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final NumberPath<Integer> delivery_fee = createNumber("delivery_fee", Integer.class);

    public final DateTimePath<java.util.Date> indate = createDateTime("indate", java.util.Date.class);

    public final ListPath<OrderDetail, QOrderDetail> odList = this.<OrderDetail, QOrderDetail>createList("odList", OrderDetail.class, QOrderDetail.class, PathInits.DIRECT2);

    public final NumberPath<Long> oseq = createNumber("oseq", Long.class);

    public final NumberPath<Integer> result = createNumber("result", Integer.class);

    public final NumberPath<Integer> total_price = createNumber("total_price", Integer.class);

    public final NumberPath<Integer> used_point = createNumber("used_point", Integer.class);

    public final QUser user;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

