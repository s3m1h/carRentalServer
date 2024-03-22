package ksu.bitirmeserver.dtos.request;

import ksu.bitirmeserver.model.car.CarBodyType;
import ksu.bitirmeserver.model.car.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

    private String carName;
    private int modelYear;
    private CarBodyType carBodyType;
    private FuelType fuelType;
    private BigDecimal dailyPrice;
    private String description;
    private int kilometer;
    private String colorName;
    private String brandName;
}
