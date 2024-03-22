package ksu.bitirmeserver.controller;

import ksu.bitirmeserver.dtos.request.CreateCarRequest;
import ksu.bitirmeserver.dtos.request.UpdateCarRequest;
import ksu.bitirmeserver.dtos.response.CarListResponse;
import ksu.bitirmeserver.dtos.response.GetByIdCarResponse;
import ksu.bitirmeserver.model.Car;
import ksu.bitirmeserver.service.CarService;
import ksu.bitirmeserver.utilities.results.DataResult;
import ksu.bitirmeserver.utilities.results.Result;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/api/cars")
@CrossOrigin
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result addCar(
            @RequestPart("carRequest") CreateCarRequest carRequest,
            @RequestPart(name = "files") MultipartFile files) throws SQLException, IOException {
        Result result = carService.add(carRequest,files);
        if(result.isSuccess()) return result;
        return null;
    }
    @GetMapping
    public DataResult<List<CarListResponse>> getAllCars() throws SQLException {
        DataResult<List<CarListResponse>> result = carService.getAll();
        if(result.isSuccess()) return result;
        return null;
    }
    @PutMapping(value = "/update/{carID}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Result updateCar(@PathVariable("carID") Long id,
                            @RequestPart("carRequest")UpdateCarRequest updateCarRequest,
                            @RequestPart("file") MultipartFile file) throws IOException, SQLException {
        byte[] photoBytes = (file != null && !file.isEmpty()) ? file.getBytes() : carService.getCarPhotoByCarId(id);
        Result result = carService.update(id,updateCarRequest,photoBytes);
        if(result.isSuccess()) return result;
        return null;
    }
    @GetMapping("/{carID}")
    public DataResult<GetByIdCarResponse> getCarById(@PathVariable("carID") Long id) throws SQLException {
        DataResult<GetByIdCarResponse> result = carService.getById(id);
        if(result.isSuccess()) return result;
        return null;
    }

}
