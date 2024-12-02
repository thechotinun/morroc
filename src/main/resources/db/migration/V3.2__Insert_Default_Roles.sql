-- V3.2__Insert_Default_Roles.sql
INSERT INTO roles (
    id,
    name,
    description,
    status,
    created_at
) VALUES 
(
    'db495d3c-c509-4b62-8b19-96743a4663d7',
    'SUPER_ADMIN',
    'Super Administrator with full system access',
    true,
    CURRENT_TIMESTAMP
),
(
    '12afbb7c-397c-489c-8e99-a9fa18840689',
    'ADMIN',
    'Administrator with limited system access',
    true,
    CURRENT_TIMESTAMP
),
(
    '74ebead4-05a1-436a-9c36-20b6e4f8621c',
    'USER',
    'Regular user with basic access',
    true,
    CURRENT_TIMESTAMP
);