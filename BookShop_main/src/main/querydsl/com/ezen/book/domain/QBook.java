package com.ezen.book.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = -257330799L;

    public static final QBook book = new QBook("book");

    public final StringPath author = createString("author");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath image = createString("image");

    public final NumberPath<Integer> per_point = createNumber("per_point", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath publisher = createString("publisher");

    public final DateTimePath<java.util.Date> publishing_date = createDateTime("publishing_date", java.util.Date.class);

    public final NumberPath<Integer> purchase = createNumber("purchase", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath title = createString("title");

    public final StringPath type = createString("type");

    public QBook(String variable) {
        super(Book.class, forVariable(variable));
    }

    public QBook(Path<? extends Book> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBook(PathMetadata metadata) {
        super(Book.class, metadata);
    }

}

