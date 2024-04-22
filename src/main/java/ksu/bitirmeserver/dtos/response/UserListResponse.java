package ksu.bitirmeserver.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponse {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
