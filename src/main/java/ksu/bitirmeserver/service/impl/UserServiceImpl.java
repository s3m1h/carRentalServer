package ksu.bitirmeserver.service.impl;

import ksu.bitirmeserver.dtos.request.CreateUserRequest;
import ksu.bitirmeserver.dtos.response.GetByIdUserResponse;
import ksu.bitirmeserver.dtos.response.UserListResponse;
import ksu.bitirmeserver.exception.UserAlreadyExistsException;
import ksu.bitirmeserver.repository.UserRepository;
import ksu.bitirmeserver.service.UserService;
import ksu.bitirmeserver.utilities.results.DataResult;
import ksu.bitirmeserver.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Result register(CreateUserRequest createUserRequest) throws UserAlreadyExistsException {
        if(userRepository.existsByEmail(createUserRequest.getEmail())){
            throw new UserAlreadyExistsException(createUserRequest.getEmail() +" already exists");
        }
        return null;
    }

    @Override
    public DataResult<List<UserListResponse>> getAll() {
        return null;
    }

    @Override
    public Result delete(String email) {
        return null;
    }

    @Override
    public DataResult<GetByIdUserResponse> getById(int userId) {
        return null;
    }
}
