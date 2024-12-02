-- V4__Create_Users_Table.sql
CREATE TABLE users (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    provider VARCHAR(20) NOT NULL CHECK (provider IN ('LOCAL', 'GOOGLE', 'FACEBOOK')),
    user_status BOOLEAN NOT NULL DEFAULT TRUE,
    role_id UUID NOT NULL,
    application_id UUID NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT fk_users_application FOREIGN KEY (application_id) REFERENCES applications(id),
    CONSTRAINT uk_users_email_provider_application UNIQUE (email, provider, application_id)
);