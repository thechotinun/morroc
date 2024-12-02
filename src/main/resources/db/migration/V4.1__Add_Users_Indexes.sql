-- V4.1__Add_Users_Indexes.sql
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_provider ON users(provider);
CREATE INDEX idx_users_status ON users(user_status);
CREATE INDEX idx_users_role ON users(role_id);
CREATE INDEX idx_users_application ON users(application_id);