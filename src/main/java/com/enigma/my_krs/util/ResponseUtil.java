package com.enigma.my_krs.util;

import com.enigma.my_krs.dto.response.CommonPagingResponse;
import com.enigma.my_krs.dto.response.CommonResponse;
import com.enigma.my_krs.dto.response.PagingResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtil {
    public static <T> ResponseEntity<CommonResponse<?>> buildResponse(HttpStatus httpStatus, String message, T data) {
        CommonResponse<T> response = new CommonResponse<>(httpStatus.value(), message, data);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <T> ResponseEntity<CommonPagingResponse<?>> buildResponsePage(HttpStatus httpStatus, String message, Page<T> page) {
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .page(page.getPageable().getPageNumber() + 1)
                .size(page.getSize())
                .build();

        CommonPagingResponse<List<T>> response = new CommonPagingResponse<>(httpStatus.value(), message, page.getContent(), pagingResponse);

        return ResponseEntity.status(httpStatus).body(response);
    }

}
