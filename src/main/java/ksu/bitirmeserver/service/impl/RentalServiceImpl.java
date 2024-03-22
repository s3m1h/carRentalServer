package ksu.bitirmeserver.service.impl;

import ksu.bitirmeserver.dtos.request.CreateRentalCarRequest;
import ksu.bitirmeserver.model.Rental;
import ksu.bitirmeserver.repository.RentalRepository;
import ksu.bitirmeserver.service.RentalService;
import ksu.bitirmeserver.utilities.mappers.ModelMapperService;
import ksu.bitirmeserver.utilities.results.Result;
import ksu.bitirmeserver.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final ModelMapperService modelMapperService;
    private final RentalRepository rentalRepository;
    @Override
    public Result add(CreateRentalCarRequest createRentalCarRequest) {
        Rental rentalCar = this.modelMapperService.forRequest().map(createRentalCarRequest, Rental.class);
        rentalRepository.save(rentalCar).getId();
        return new SuccessResult();
    }
}
