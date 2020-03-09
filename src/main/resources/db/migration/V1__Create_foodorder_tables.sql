create table basket
(
    basket_id bigint auto_increment
        primary key
)
    engine = MyISAM;

create table category
(
    category_id   bigint auto_increment
        primary key,
    category_name varchar(255) null
)
    engine = MyISAM;

create table food
(
    food_id      bigint auto_increment
        primary key,
    cooking_time int          null,
    delivery     bit          null,
    discount     double       null,
    ingredients  varchar(255) null,
    name         varchar(255) null,
    price        double       null,
    weight       double       null,
    category_id  bigint       null
)
    engine = MyISAM;

create index FKkomdx99dhk2cveaxugl2lws2u
    on food (category_id);

create table food_basket
(
    food_id   bigint not null,
    basket_id bigint not null
)
    engine = MyISAM;

create index FK7g5ffsfhibunqvcx668prw8s7
    on food_basket (basket_id);

create index FKhciq0dq44eue7vr8mdu7wogpl
    on food_basket (food_id);

create table role
(
    role_id   bigint auto_increment
        primary key,
    role_name varchar(255) null
)
    engine = MyISAM;

create table user
(
    user_id  bigint auto_increment
        primary key,
    password varchar(255) null,
    username varchar(255) null
)
    engine = MyISAM;

create table user_roles
(
    users_user_id bigint not null,
    roles_role_id bigint not null,
    primary key (users_user_id, roles_role_id)
)
    engine = MyISAM;

create index FKhxmmg8j4h4qpwbvf39cnujlkf
    on user_roles (roles_role_id);

/*Create table Category(
                    category_id     BIGINT        PRIMARY KEY NOT NULL,
                    category_name   VARCHAR(100)  NOT NULL UNIQUE
);

Create table Food(
                     food_id         BIGINT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     name            VARCHAR(255)    NOT NULL,
                     price           double          NOT NULL,
                     cooking_time    int             NOT NULL,
                     delivery        bit             NOT NULL,
                     discount        double          NOT NULL,
                     weight          double          NOT NULL,
                     ingredients     VARCHAR(255)    NOT NULL,
                     category_id     BIGINT          NOT NULL,
                     FOREIGN KEY (category_id)       REFERENCES category(category_id)
);

Create table Basket(
                    basket_id    BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
                    food_id      BIGINT         NOT NULL,
                    FOREIGN KEY (food_id)       REFERENCES food(food_id)
);

Create table food_basket(
                    food_id     BIGINT          NOT NULL,
                    basket_id   BIGINT          NOT NULL,
                    FOREIGN KEY(food_id) references food(food_id) ON DELETE CASCADE ,
                    FOREIGN KEY(basket_id) references basket(basket_id) ON DELETE CASCADE
);

Create table Role(
                    role_id     BIGINT            PRIMARY KEY NOT NULL AUTO_INCREMENT,
                    role_name   VARCHAR(30)       NOT NULL
);

Create table User(
                     user_id     BIGINT           PRIMARY KEY NOT NULL AUTO_INCREMENT,
                     username    VARCHAR(100)     NOT NULL unique,
                     password    VARCHAR(255)     NOT NULL,
                     basket_id   BIGINT           DEFAULT NULL,
                     role_id     BIGINT           NOT NULL,
                     FOREIGN KEY (basket_id)      REFERENCES basket(basket_id)  ON DELETE CASCADE,
                     FOREIGN KEY (user_id)        REFERENCES Role(role_id)
);

Create table user_role(
                    user_id      BIGINT            NOT NULL,
                    role_id      BIGINT            NOT NULL,
                    FOREIGN KEY(user_id) references User(user_id),
                    FOREIGN KEY(role_id) references Role(role_id)
);*/