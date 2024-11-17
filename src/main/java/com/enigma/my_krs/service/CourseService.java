package com.enigma.my_krs.service;

import com.enigma.my_krs.dto.request.CourseRequest;
import com.enigma.my_krs.dto.response.CourseResponse;
import com.enigma.my_krs.entity.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    CourseResponse createCourse(CourseRequest request);
    List<CourseResponse> getAllCourses();
    Course getOne(UUID id);
    CourseResponse getCourseById(UUID id);
    CourseResponse updateCourseById(UUID id, CourseRequest request);
    void deleteCourseById(UUID id);
}
