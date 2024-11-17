package com.enigma.my_krs.service;

import com.enigma.my_krs.dto.request.CourseOfferingRequest;
import com.enigma.my_krs.dto.request.SearchRequest;
import com.enigma.my_krs.dto.response.CourseOfferingResponse;
import com.enigma.my_krs.entity.CourseOffering;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CourseOfferingService {
    CourseOfferingResponse createCourseOffering(CourseOfferingRequest request);
    Page<CourseOfferingResponse> getAllCourseOfferings(SearchRequest request);
    CourseOffering getOne(UUID id);
    CourseOfferingResponse getCourseOfferingById(UUID id);
    CourseOfferingResponse updateCourseOfferingById(UUID id, CourseOfferingRequest request);
    void deleteCourseOfferingById(UUID id);
}
