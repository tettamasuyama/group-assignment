-- users テーブル
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(254) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    role VARCHAR(20) NOT NULL
);

ALTER TABLE users
ADD CONSTRAINT chk_users_role
CHECK (role IN ('ADMIN', 'EMPLOYEE'));

-- employees テーブル
CREATE TABLE employees (
    id BIGSERIAL PRIMARY KEY,
    employee_number VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(254),
    department VARCHAR(50),
    position VARCHAR(50),
    hire_date DATE,
    status VARCHAR(20) NOT NULL
);

ALTER TABLE employees
ADD CONSTRAINT chk_employees_status
CHECK (status IN ('ACTIVE', 'RETIRED'));

INSERT INTO employees (
    employee_number, name, email, department, position, hire_date, status
)

VALUES ('E001', '山田太郎', 'test@example.com', 'SALES', 'STAFF', '2024-01-01', 'ACTIVE')
ON CONFLICT (employee_number)
DO UPDATE SET
    name = EXCLUDED.name,
    email = EXCLUDED.email,
    department = EXCLUDED.department,
    position = EXCLUDED.position,
    hire_date = EXCLUDED.hire_date,
    status = EXCLUDED.status;

    SELECT * FROM employees
WHERE employee_number = 'E001';