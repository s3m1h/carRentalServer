package ksu.bitirmeserver.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rental {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name="start_date", nullable = false)
    private LocalDate startDate;


    @Column(name="finish_date", nullable = false)
    private LocalDate finishDate;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne()
    @JoinColumn(name ="rented_city", nullable = false)
    private City rentedCity;

    @ManyToOne()
    @JoinColumn(name = "delivered_city", nullable = false)
    private City deliveredCity;

    @Column(name = "rented_kilometer")
    private Integer rentedKilometer;

    @Column(name = "delivered_kilometer")
    private Integer deliveredKilometer;

//    @Column(name = "total_cost")
//    private BigDecimal total_cost;
//
//    @Column(name = "confirmation_code")
//    private String rentalConfirmationCode;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

//    public void setRentalConfirmationCode(String rentalConfirmationCode) {
//        this.rentalConfirmationCode = rentalConfirmationCode;
//    }
}