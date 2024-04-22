package ksu.bitirmeserver.controller;

import jakarta.validation.Valid;
import ksu.bitirmeserver.dtos.request.CreateRentalCarRequest;
import ksu.bitirmeserver.dtos.response.RentalListResponse;
import ksu.bitirmeserver.service.RentalService;
import ksu.bitirmeserver.utilities.results.DataResult;
import ksu.bitirmeserver.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;

    @PostMapping
    public Result addRental(@RequestBody @Valid CreateRentalCarRequest createRentalCarRequest){
        Result result = rentalService.add(createRentalCarRequest);
        if(result.isSuccess()) return result;
        return null;
    }
    @GetMapping
    public DataResult<List<RentalListResponse>> getAllRentals(){
        DataResult<List<RentalListResponse>> result =  rentalService.getAll();
        if(result.isSuccess()) return result;
        return null;

    }
}
