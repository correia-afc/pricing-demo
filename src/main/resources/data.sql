DROP TABLE IF EXISTS count_based_discount_configuration_entity CASCADE;
DROP TABLE IF EXISTS percentage_based_discount_configuration_entity CASCADE;

CREATE TABLE count_based_discount_configuration_entity (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    product_id UUID NOT NULL,
    lower_quantity_limit INTEGER NOT NULL,
    percentage NUMERIC(38, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE INDEX idx_product_id ON count_based_discount_configuration_entity (product_id);

CREATE TABLE percentage_based_discount_configuration_entity (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    product_id UUID UNIQUE NOT NULL,
    percentage NUMERIC(38, 2) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO count_based_discount_configuration_entity (lower_quantity_limit, percentage, product_id) 
VALUES (5, 10.0, 'bf39d6eb-d3ca-4d53-9a47-03b440ef24d0');

INSERT INTO count_based_discount_configuration_entity (lower_quantity_limit, percentage, product_id) 
VALUES (10, 15.0, 'bf39d6eb-d3ca-4d53-9a47-03b440ef24d0');

INSERT INTO count_based_discount_configuration_entity (lower_quantity_limit, percentage, product_id) 
VALUES (20, 20.0, 'bf39d6eb-d3ca-4d53-9a47-03b440ef24d0');

INSERT INTO percentage_based_discount_configuration_entity (percentage, product_id) 
VALUES (10.0, 'ed1de060-5ead-475f-b57e-d9971dace701');

COMMIT;