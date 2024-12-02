-- V2.2__Insert_Default_Applications.sql
INSERT INTO applications (
    id,
    name,
    description,
    uri,
    status,
    created_at
) VALUES 
(
    '59e0dad0-53cf-4408-96cd-fa8a7c6cf94a',
    'Escrow',
    'Service for escrow',
    'http://localhost:3000/sign/in',
    true,
    CURRENT_TIMESTAMP
),
(
    'dbf09a96-bcff-4e24-9b26-a8fc28cce9f7',
    'Example',
    'Service for example',
    'https://example.com',
    true,
    CURRENT_TIMESTAMP
);