package esa.askerestful.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingResponse {

    private Integer totalPage;

    private Integer size;
}
