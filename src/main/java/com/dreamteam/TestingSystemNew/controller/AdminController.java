package com.dreamteam.TestingSystemNew.controller;

import com.dreamteam.TestingSystemNew.model.Groups;
import com.dreamteam.TestingSystemNew.payload.ApiResponse;
import com.dreamteam.TestingSystemNew.payload.TeacherRequest;
import com.dreamteam.TestingSystemNew.payload.СonstructorParam;
import com.dreamteam.TestingSystemNew.service.UserService;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/api/admin")

public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    СonstructorParam сonstructorParam;

    @PostMapping("/group/add")
    public ResponseEntity<?> saveGroup(@RequestParam(value = "number") String number){
        userService.saveGroup(number);
        return ResponseEntity.ok(new ApiResponse(true, "Success add group!"));
    }

    @GetMapping ("/get/constructor")
    public Object randNumber()
    {
        return сonstructorParam.something();
    }

    @PostMapping("/get/constructor")
    public  ResponseEntity<?> checkAnswer(@RequestParam(value = "sing") String sign, @RequestParam int numerator_degree,
                              @RequestParam int denominator, @RequestParam int numerator)
    {
         сonstructorParam.result(sign,numerator_degree,denominator,numerator);
        return ResponseEntity.ok(new ApiResponse(true, "Задание выполнено верно"));
    }



    @PostMapping("/subject/add")
    public ResponseEntity<?> saveSubject(@RequestParam(value = "title") String title){
        userService.saveSubject(title);
        return ResponseEntity.ok(new ApiResponse(true, "Success add subject!"));
    }

    @PostMapping("/teacher")
    public ResponseEntity<?> setTeacherForUser(@RequestBody TeacherRequest teacherRequest){
        userService.setUserTeacher(teacherRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Success set teacher!"));
    }

    @GetMapping("/group/get")
    public List<Groups> getGroups(){
        return userService.getGroupForAdmin();
    }
}
