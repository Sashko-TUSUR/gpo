package com.dreamteam.TestingSystemNew.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {
    private Long userId;
    private Long subjectId;
    private int seminarNumber;
    private List<Long> groupsId;
}
