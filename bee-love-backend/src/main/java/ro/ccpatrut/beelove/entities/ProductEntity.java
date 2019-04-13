package ro.ccpatrut.beelove.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "products", schema = "beelove")
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "unit", nullable = false)
    private Double unit;

    @Column(name = "pathtoimage", nullable = false)
    private String pathToImage;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "available_units", nullable = false)
    private Double availableUnits;
}
