package com.morroc.v1.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.morroc.v1.common.enums.Provider;
import com.morroc.v1.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailAndProviderAndApplicationId(
        String email,
        Provider provider,
        UUID applicationId
    );

    List<User> findByApplicationId(UUID applicationId);
    
    List<User> findByRoleId(UUID roleId);
}
