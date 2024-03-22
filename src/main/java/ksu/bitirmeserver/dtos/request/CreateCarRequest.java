package ksu.bitirmeserver.dtos.request;

import ksu.bitirmeserver.model.car.CarBodyType;
import ksu.bitirmeserver.model.car.FuelType;
import ksu.bitirmeserver.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

    private String carName;
    private int modelYear;
    private CarBodyType carBodyType;
    private FuelType fuelType;
    private BigDecimal dailyPrice;
    private String description;
    private int kilometer;
    private Long colorId;
    private Long brandId;

}
