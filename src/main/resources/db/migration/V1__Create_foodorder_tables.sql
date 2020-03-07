Create table Category(
                         categoryId      INT           NOT NULL PRIMARY KEY,
                         category_name   VARCHAR(100)  NOT NULL
);

Create table Food(
                     foodId          BIGINT         NOT NULL PRIMARY KEY,
                     name            VARCHAR(255)   NOT NULL,
                     price           decimal        NOT NULL,
                     cooking_time    int            NOT NULL,
                     delivery        bit            NOT NULL,
                     discount        decimal        NOT NULL,
                     weight          decimal        NOT NULL,
                     ingredients     VARCHAR(255)   NOT NULL,
                     categoryId      INT            NOT NULL,
                     FOREIGN KEY (categoryId)       REFERENCES category(categoryId)
);

Create table Basket(
                       basketId    BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
                       foodId      BIGINT         NOT NULL,
                       FOREIGN KEY (foodId)       REFERENCES food(foodId)
);

Create table User(
                     userId      BIGINT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     role        VARCHAR(20)     NOT NULL,
                     name        VARCHAR(100)    NOT NULL unique,
                     password    VARCHAR(255)    NOT NULL,
                     basketId    BIGINT             NOT NULL,
                     FOREIGN KEY (basketId)      REFERENCES basket(basketId)  ON DELETE CASCADE
);

INSERT INTO category (categoryId, category_name) VALUES
(1, 'Pizza'),
(2, 'Hot Meals'),
(3, 'Fast Food'),
(4, 'Salads'),
(5, 'Soups'),
(6, 'Deserts');


