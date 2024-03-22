package ksu.bitirmeserver.controller;

import jakarta.validation.Valid;
import ksu.bitirmeserver.dtos.request.CreateRentalCarRequest;
import ksu.bitirmeserver.service.RentalService;
import ksu.bitirmeserver.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping
    public Result addRental(@RequestBody @Valid CreateRentalCarRequest createRentalCarRequest){
        Result result = rentalService.add(createRentalCarRequest);
        if(result.isSuccess()) return result;
        return null;
    }
}
