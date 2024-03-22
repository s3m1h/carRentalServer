package ksu.bitirmeserver.service.impl;

import ksu.bitirmeserver.constants.Messages;
import ksu.bitirmeserver.dtos.request.CreateCarRequest;
import ksu.bitirmeserver.dtos.request.UpdateCarRequest;
import ksu.bitirmeserver.dtos.response.CarListResponse;
import ksu.bitirmeserver.dtos.response.GetByIdBrandResponse;
import ksu.bitirmeserver.dtos.response.GetByIdCarResponse;
import ksu.bitirmeserver.exception.*;
import ksu.bitirmeserver.model.*;
import ksu.bitirmeserver.repository.BrandRepository;
import ksu.bitirmeserver.repository.CarRepository;
import ksu.bitirmeserver.repository.ColorRepository;
import ksu.bitirmeserver.service.CarService;
import ksu.bitirmeserver.utilities.results.DataResult;
import ksu.bitirmeserver.utilities.results.Result;
import ksu.bitirmeserver.utilities.results.SuccessDataResult;
import ksu.bitirmeserver.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
//    private final BrandService brandService;
//    private final ColorService colorService;
    private final BrandRepository brandRepository;
    private final ColorRepository colorRepository;
    @Override
    public Result add(CreateCarRequest createCarRequest,MultipartFile file) throws IOException, SQLException {
//        brandService.checkIsExistsByBrandId(createCarRequest.getBrandId());
//        colorService.checkIsExistsByColorId(createCarRequest.getColorId());
        Car car = new Car();
        car.setName(createCarRequest.getCarName());
        car.setModelYear(createCarRequest.getModelYear());
        car.setDailyPrice(createCarRequest.getDailyPrice());
        car.setDescription(createCarRequest.getDescription());
        car.setKilometer(createCarRequest.getKilometer());

        car.setCarBodyType(createCarRequest.getCarBodyType());
        car.setFuelType(createCarRequest.getFuelType());
        car.setBrand(brandRepository.findById(createCarRequest.getBrandId()).orElseThrow());
        car.setColor(colorRepository.findById(createCarRequest.getColorId()).orElseThrow());


        if (!file.isEmpty()) {
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            car.setPhoto(photoBlob);
        }

//        try{
//            Set<Image> images = uploadImage(file);
//            car.setCarImages(images);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        carRepository.save(car);
        return new SuccessResult();
    }

//    private Set<Image> uploadImage(MultipartFile[] multipartFiles) throws IOException {
//        Set<Image> images = new HashSet<>();
//        for(MultipartFile file: multipartFiles){
//            System.out.println(file.getOriginalFilename());
//            Image image = new Image();
//            image.setName(file.getOriginalFilename());
//            image.setType(file.getContentType());
//            image.setPhotoByte(file.getBytes());
//            images.add(image);
//
//        }
//        return images;
//    }

    @Override
    public DataResult<List<CarListResponse>> getAll() throws SQLException {
        List<Car> cars = carRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<CarListResponse> carListResponses = new ArrayList<>();
        for(Car car: cars){
            byte[] photoBytes = getCarPhotoByCarId(car.getId());
            if (photoBytes != null && photoBytes.length > 0) {
                String base64Photo = Base64.encodeBase64String(photoBytes);
                CarListResponse carListResponse = getCarListResponse(car);
                carListResponse.setPhoto(base64Photo);
                carListResponses.add(carListResponse);
            }

        }
        return new SuccessDataResult<>(carListResponses);
    }

    @Override
    public Result update(Long id, UpdateCarRequest updateCarRequest, byte[] photoBytes) {
        Car car = carRepository.findById(id).get();
        if(updateCarRequest != null){
            car.setName(updateCarRequest.getCarName());
            car.setModelYear(updateCarRequest.getModelYear());
            car.setDailyPrice(updateCarRequest.getDailyPrice());
            car.setDescription(updateCarRequest.getDescription());
            car.setKilometer(updateCarRequest.getKilometer());
            car.setCarBodyType(updateCarRequest.getCarBodyType());
            car.setFuelType(updateCarRequest.getFuelType());
            car.setBrand(brandRepository.findByName(updateCarRequest.getBrandName()));
            car.setColor(colorRepository.findByName(updateCarRequest.getColorName()));
        }
        if(photoBytes != null && photoBytes.length > 0)
        {
            try{
                car.setPhoto(new SerialBlob(photoBytes));
            }
            catch (SQLException exception){
                throw new InternalServerException("Yükleme başarısız.." + exception.getMessage());
            }
        }
        carRepository.save(car);
        return new SuccessResult(Messages.CarMessages.DATA_UPDATED_SUCCESSFULLY);
    }

    public byte[] getCarPhotoByCarId(Long carId) throws SQLException {
        Optional<Car> theCar = carRepository.findById(carId);
        if(theCar.isEmpty()){
            throw new ResourceNotFoundException("Sorry, Car not found!");
        }
        Blob photoBlob = theCar.get().getPhoto();
        if(photoBlob != null){
            return photoBlob.getBytes(1, (int) photoBlob.length());
        }
        return null;
    }

    @Override
    public DataResult<GetByIdCarResponse> getById(Long id) throws SQLException {
        Car car =  carRepository.findById(id).orElseThrow(()-> new CarNotFoundException(Messages.CarMessages.CAR_ID_NOT_FOUND));
        Blob photoBlob = car.getPhoto();
        byte[] photoBytes = photoBlob.getBytes(1, (int)photoBlob.length());


        return new SuccessDataResult<>(new GetByIdCarResponse(
                car.getName(),
                car.getModelYear(),
                car.getCarBodyType().name(),
                car.getFuelType().name(),
                car.getDailyPrice(),
                car.getDescription(),
                car.getKilometer(),
                photoBytes,
                car.getColor().getName(),
                car.getBrand().getName()
        ));
    }

    private CarListResponse getCarListResponse(Car car) {
        byte[] photoBytes = null;
        Blob photoBlob = car.getPhoto();
        if (photoBlob != null) {
            try {
                photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
            } catch (SQLException e) {
                throw new PhotoRetrievalException("Error retrieving photo");
            }
        }
        return new CarListResponse(
                car.getId(),
                car.getName(),
                car.getModelYear(),
                car.getCarBodyType().name(),
                car.getFuelType().name(),
                car.getDailyPrice(),
                car.getDescription(),
                car.getKilometer(),
                photoBytes,
                car.getColor().getName(),
                car.getBrand().getName()
        );
    }
}
