package ro.fasttarckit.treatment.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "medicaments")
@NoArgsConstructor
@Getter
@Setter
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "expiryDate")
    private Date expiryDate;

    @Column(name = "usage")
    @Enumerated(EnumType.ORDINAL)
    private Usage usage;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @Column(name = "frequency")
    private Integer frequency;

    @Column(name = "timeOfDay")
    @Enumerated(EnumType.ORDINAL)
    private TimeOfDay timeOfDay;

    @Column(name = "beforeEating")
    private Boolean beforeEating;

}

