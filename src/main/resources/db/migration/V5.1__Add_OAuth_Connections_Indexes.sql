-- V5.1__Add_OAuth_Connections_Indexes
CREATE INDEX idx_oauth_connections_user_id ON oauth_connections(user_id);
CREATE INDEX idx_oauth_connections_provider ON oauth_connections(provider);
CREATE INDEX idx_oauth_connections_provider_user_id ON oauth_connections(provider_user_id);
CREATE INDEX idx_oauth_connections_deleted_at ON oauth_connections(deleted_at) WHERE deleted_at IS NULL;