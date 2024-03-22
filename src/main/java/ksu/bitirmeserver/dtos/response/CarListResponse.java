package ksu.bitirmeserver.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListResponse {
    private Long carId;
    private String carName;
    private int modelYear;
    private String carBodyType;
    private String fuelType;
    private BigDecimal dailyPrice;
    private String description;
    private int kilometer;
    private String photo;
    private String colorName;
    private String brandName;

    public CarListResponse(Long carId,
                           String carName,
                           int modelYear,
                           String carBodyType,
                           String fuelType,
                           BigDecimal dailyPrice,
                           String description,
                           int kilometer,
                           byte[] photoBytes,
                           String colorName,
                           String brandName) {
        this.carId = carId;
        this.carName = carName;
        this.modelYear = modelYear;
        this.carBodyType = carBodyType;
        this.fuelType = fuelType;
        this.dailyPrice = dailyPrice;
        this.description = description;
        this.kilometer = kilometer;
        this.photo =  photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;;
        this.colorName = colorName;
        this.brandName = brandName;
    }
}
