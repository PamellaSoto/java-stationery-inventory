CREATE TABLE IF NOT EXISTS anime (
    id SERIAL PRIMARY KEY,
<<<<<<< HEAD
    name VARCHAR(50) NOT NULL UNIQUE
=======
    name VARCHAR(100) NOT NULL UNIQUE,
    slug VARCHAR(255) NOT NULL UNIQUE
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
);

CREATE TABLE IF NOT EXISTS product_type (
    id SERIAL PRIMARY KEY,
<<<<<<< HEAD
    name VARCHAR(50) NOT NULL UNIQUE
=======
    name VARCHAR(100) NOT NULL UNIQUE,
    slug VARCHAR(255) NOT NULL UNIQUE
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
);

CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
<<<<<<< HEAD
    product_name VARCHAR(100) NOT NULL,
=======
    product_name VARCHAR(255) NOT NULL,
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
    product_description TEXT,
    price NUMERIC(10, 2) NOT NULL CHECK (price >= 0),
    stock INTEGER NOT NULL CHECK (stock >= 0),
    image_url VARCHAR(255),
    anime_id INTEGER NOT NULL,
    product_type_id INTEGER NOT NULL,
    is_available BOOLEAN,
    FOREIGN KEY (anime_id) REFERENCES anime(id) ON DELETE RESTRICT,
    FOREIGN KEY (product_type_id) REFERENCES product_type(id) ON DELETE RESTRICT
<<<<<<< HEAD
=======
);

CREATE TABLE IF NOT EXISTS product_order (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity >= 0),
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_group (
    id SERIAL PRIMARY KEY,
    order_id INTEGER NOT NULL,
    FOREIGN KEY (order_id) REFERENCES product_order(id) ON DELETE CASCADE
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
);