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
    user_id      bigint auto_increment
        primary key,
    password     varchar(255) null,
    phone_number varchar(255) null,
    username     varchar(255) null
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