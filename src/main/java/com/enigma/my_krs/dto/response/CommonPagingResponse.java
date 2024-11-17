package com.enigma.my_krs.dto.response;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommonPagingResponse<T> {
    private Integer status;
    private String message;
    private T data;
    private PagingResponse paging;
}
