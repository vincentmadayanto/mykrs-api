package com.enigma.my_krs.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommonResponse<T> {
    private Integer status;
    private String message;
    private T data;
}
