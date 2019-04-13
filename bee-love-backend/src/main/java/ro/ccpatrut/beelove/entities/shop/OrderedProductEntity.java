package ro.ccpatrut.beelove.entities.shop;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_orders", schema = "beelove")
@Data
public class OrderedProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "unit", nullable = false)
    private Long unit;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
}
