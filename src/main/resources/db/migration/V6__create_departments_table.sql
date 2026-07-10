CREATE TABLE departments (

    id BIGSERIAL PRIMARY KEY,

    department_code VARCHAR(50) UNIQUE NOT NULL,

    department_name VARCHAR(100) UNIQUE NOT NULL,

    location VARCHAR(100),

    description VARCHAR(255),

    status VARCHAR(20),

    created_at TIMESTAMP,

    updated_at TIMESTAMP
);