Create table Category(
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

Create table User(
                     user_id     BIGINT           PRIMARY KEY NOT NULL AUTO_INCREMENT,
                     username    VARCHAR(100)     NOT NULL unique,
                     role        VARCHAR(20)      NOT NULL,
                     password    VARCHAR(255)     NOT NULL,
                     basket_id    BIGINT          DEFAULT NULL,
                     FOREIGN KEY (basket_id)      REFERENCES basket(basket_id)  ON DELETE CASCADE
);





