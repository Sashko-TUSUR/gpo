package com.dreamteam.TestingSystemNew.payload;

import com.dreamteam.TestingSystemNew.model.Groups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {
    String titleSubject;
    List<Groups> groupList;
}
