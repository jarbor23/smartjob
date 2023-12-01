package co.com.smartjob.jpa.phone;

import co.com.smartjob.jpa.user.EntityUser;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "phones")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class EntityPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;
    private String cityCode;
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private EntityUser user;
}
