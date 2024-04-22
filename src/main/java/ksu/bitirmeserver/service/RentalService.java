package ksu.bitirmeserver.service;

import ksu.bitirmeserver.dtos.request.CreateRentalCarRequest;
import ksu.bitirmeserver.dtos.response.GetByIdRentalResponse;
import ksu.bitirmeserver.dtos.response.RentalListResponse;
import ksu.bitirmeserver.utilities.results.DataResult;
import ksu.bitirmeserver.utilities.results.Result;

import java.util.List;

public interface RentalService {
    Result add(CreateRentalCarRequest createRentalCarRequest);
    Result deleteRental(Long rentalId);
    DataResult<GetByIdRentalResponse> getById(Long id);
    DataResult<List<RentalListResponse>> getAllByRental_CarId(int carId);
    DataResult<List<RentalListResponse>> getAll();

    void checkIsExistsByRentalCarId(Long id);
}
