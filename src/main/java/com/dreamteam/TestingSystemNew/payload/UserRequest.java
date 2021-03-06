package com.dreamteam.TestingSystemNew.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
        private String userName;
        private String password;
        private String name;
        private String lastName;
        private String middleName;
        private boolean isStudent;
        private String numberGroup;
}
