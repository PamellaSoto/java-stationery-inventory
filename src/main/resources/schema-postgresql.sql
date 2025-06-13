CREATE TABLE IF NOT EXISTS anime (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    slug VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS product_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    slug VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_description TEXT,
    price NUMERIC(10, 2) NOT NULL CHECK (price >= 0),
    stock INTEGER NOT NULL CHECK (stock >= 0),
    image_url VARCHAR(500),
    anime_id INTEGER NOT NULL,
    product_type_id INTEGER NOT NULL,
    is_available BOOLEAN,
    FOREIGN KEY (anime_id) REFERENCES anime(id) ON DELETE RESTRICT,
    FOREIGN KEY (product_type_id) REFERENCES product_type(id) ON DELETE RESTRICT
);