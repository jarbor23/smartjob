package co.com.smartjob.jpa.user;

import co.com.smartjob.jpa.phone.EntityPhone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EntityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String name;
    private String email;
    private String password;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created;
    @CreationTimestamp
    @Column(name = "modified", nullable = false)
    private LocalDateTime modified;
    @CreationTimestamp
    @Column(name = "last_login", nullable = false)
    private LocalDateTime lastLogin;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EntityPhone> phones;
}
