package com.enigma.my_krs.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SearchRequest extends PagingAndSortingRequest {
    private String query;
}