package com.morroc.v1.models;

import com.morroc.v1.common.enums.Provider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_users_email_provider_application",
            columnNames = {"email", "provider", "application_id"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
public class User extends Base {
    
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private Provider provider;

    @Column(name = "user_status", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean userStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;
}
