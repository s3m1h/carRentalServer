package ksu.bitirmeserver.service;

import ksu.bitirmeserver.dtos.request.CreateUserRequest;
import ksu.bitirmeserver.dtos.response.GetByIdUserResponse;
import ksu.bitirmeserver.dtos.response.UserListResponse;
import ksu.bitirmeserver.exception.UserAlreadyExistsException;
import ksu.bitirmeserver.utilities.results.DataResult;
import ksu.bitirmeserver.utilities.results.Result;

import java.util.List;

public interface UserService {
    Result register(CreateUserRequest createUserRequest) throws UserAlreadyExistsException;
    DataResult<List<UserListResponse>> getAll();
    Result delete(String email);
    DataResult<GetByIdUserResponse> getById(int userId);


}
