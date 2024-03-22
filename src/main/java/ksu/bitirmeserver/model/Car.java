package ksu.bitirmeserver.model;

import jakarta.persistence.*;
import ksu.bitirmeserver.model.car.CarBodyType;
import ksu.bitirmeserver.model.car.FuelType;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;
import java.util.Set;

@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name; // aslında model ismi

    @Column(name = "modelYear",length = 4, nullable = false)
    private int modelYear;

    @Column(name = "dailyPrice",nullable = false)
    private BigDecimal dailyPrice;

    @Column(name="description")
    private String description;

    @Column(name = "carBodyType")
    @Enumerated(EnumType.STRING)
    private CarBodyType carBodyType; // kasa tipi

    @Column(name = "fuelType")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType; // yakıt tipi

    @Column(name = "isRented")
    private boolean isRented = false;

    @Column(name = "kilometer",nullable = false)
    private int kilometer;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "car_images",
//        joinColumns = {@JoinColumn(name ="car_id")},
//        inverseJoinColumns = {@JoinColumn(name = "image_id")}
//    )
    //private Set<Image> carImages;
    @Lob
    private Blob photo;

    @ManyToOne
    @JoinColumn(name = "color_id",nullable = false)
    private Color color;

    @ManyToOne
    @JoinColumn(name="brand_id",nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "car",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rental> rentals;

}

