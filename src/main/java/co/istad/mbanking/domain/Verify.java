package co.istad.mbanking.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="verifies")
public class Verify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "digit",length = 6)
    private String digit;

    @Column(name="expired_time")
    private LocalTime  expiredTime;

    @OneToOne
    private User user;
    
}
