package ksu.bitirmeserver.service.impl;

import ksu.bitirmeserver.dtos.request.CreateRentalCarRequest;
import ksu.bitirmeserver.dtos.response.GetByIdRentalResponse;
import ksu.bitirmeserver.dtos.response.RentalListResponse;
import ksu.bitirmeserver.exception.InvalidRentalRequestException;
import ksu.bitirmeserver.exception.RentalNotFoundException;
import ksu.bitirmeserver.model.Rental;
import ksu.bitirmeserver.repository.CarRepository;
import ksu.bitirmeserver.repository.RentalRepository;
import ksu.bitirmeserver.service.RentalService;
import ksu.bitirmeserver.utilities.mappers.ModelMapperService;
import ksu.bitirmeserver.utilities.results.DataResult;
import ksu.bitirmeserver.utilities.results.Result;
import ksu.bitirmeserver.utilities.results.SuccessDataResult;
import ksu.bitirmeserver.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final ModelMapperService modelMapperService;
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    @Override
    public Result add(CreateRentalCarRequest rentalCarRequest) {
        if(rentalCarRequest.getFinishDate().isBefore(rentalCarRequest.getStartDate())){
            throw new InvalidRentalRequestException("Giriş tarihi çıkış tarihinden önce olmalıdır.");
        }
        Rental rentalCar = this.modelMapperService.forRequest().map(rentalCarRequest, Rental.class);
        rentalRepository.save(rentalCar);
        return new SuccessResult();
    }

    @Override
    public Result deleteRental(Long rentalId) {
        checkIsExistsByRentalCarId(rentalId);
        rentalRepository.deleteById(rentalId);
        return new SuccessResult();
    }

    @Override
    public DataResult<GetByIdRentalResponse> getById(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetByIdRentalResponse rentalResponse = modelMapperService.forResponse().map(rental, GetByIdRentalResponse.class);
        return new SuccessDataResult<>(rentalResponse);
    }

    @Override
    public DataResult<List<RentalListResponse>> getAllByRental_CarId(int carId) {
        return null;
    }

    @Override
    public DataResult<List<RentalListResponse>> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<RentalListResponse> rentalListResponses = rentals.stream()
                .map(r-> modelMapperService.forResponse()
                        .map(r, RentalListResponse.class)).toList();
        return new SuccessDataResult<>(rentalListResponses);
    }

    @Override
    public void checkIsExistsByRentalCarId(Long id) {
        if(!rentalRepository.existsById(id)){
            throw new RentalNotFoundException("Rental bulunamadı...");
        }
    }
}
