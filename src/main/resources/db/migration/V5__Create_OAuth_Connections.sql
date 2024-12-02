-- V5__Create_OAuth_Connections.sql
CREATE TABLE oauth_connections (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    -- Foreign key and relationships
    user_id UUID NOT NULL REFERENCES users(id),
    provider VARCHAR(50) NOT NULL,
    provider_user_id VARCHAR(255) NOT NULL,
    
    -- OAuth tokens and data
    access_token TEXT,
    refresh_token TEXT,
    token_expires_at TIMESTAMPTZ,
    provider_data JSON,  -- Using JSONB instead of JSON for better performance and indexing capabilities

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,

    -- Add unique constraints
    CONSTRAINT unique_provider_connection UNIQUE (user_id, provider),
    CONSTRAINT unique_provider_user UNIQUE (provider, provider_user_id)
);