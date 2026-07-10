CREATE TABLE employees (

    id BIGSERIAL PRIMARY KEY,

    employee_code VARCHAR(20) NOT NULL UNIQUE,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(150) NOT NULL UNIQUE,

    phone VARCHAR(20),

    gender VARCHAR(20),

    date_of_birth DATE,

    joining_date DATE,

    designation VARCHAR(100),

    salary DECIMAL(12,2),

    status VARCHAR(20),

    department_id BIGINT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);