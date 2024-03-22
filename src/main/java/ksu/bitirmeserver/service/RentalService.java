package ksu.bitirmeserver.service;

import ksu.bitirmeserver.dtos.request.CreateRentalCarRequest;
import ksu.bitirmeserver.utilities.results.DataResult;
import ksu.bitirmeserver.utilities.results.Result;

public interface RentalService {
    Result add(CreateRentalCarRequest createRentalCarRequest);
}
