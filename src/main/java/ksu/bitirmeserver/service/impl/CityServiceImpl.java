package ksu.bitirmeserver.service.impl;

import ksu.bitirmeserver.dtos.request.CreateCityRequest;
import ksu.bitirmeserver.dtos.request.UpdateCityRequest;
import ksu.bitirmeserver.dtos.response.CityListResponse;
import ksu.bitirmeserver.dtos.response.GetByIdCityResponse;
import ksu.bitirmeserver.service.CityService;
import ksu.bitirmeserver.utilities.results.DataResult;
import ksu.bitirmeserver.utilities.results.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Override
    public Result add(CreateCityRequest createCityRequest) {
        return null;
    }

    @Override
    public DataResult<List<CityListResponse>> getAll() {
        return null;
    }

    @Override
    public DataResult<GetByIdCityResponse> getById() {
        return null;
    }

    @Override
    public Result update(Long id, UpdateCityRequest updateCityRequest) {
        return null;
    }

    @Override
    public Result delete(Long id) {
        return null;
    }
}
