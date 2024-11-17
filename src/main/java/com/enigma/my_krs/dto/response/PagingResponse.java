package com.enigma.my_krs.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PagingResponse {
    private Long totalItems;
    private Integer totalPages;
    private Integer page;
    private Integer size;
}

