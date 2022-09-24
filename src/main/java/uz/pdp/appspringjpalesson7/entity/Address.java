package uz.pdp.appspringjpalesson7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 1

    @Column(nullable = false) // Tashkent
    private String city;

    @Column(nullable = false) // Mirobod
    private String district;

    @Column(nullable = false) // U. Nosir ko'chasi
    private String street;
}
