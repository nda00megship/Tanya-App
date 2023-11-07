package esa.askerestful.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebLazyLoadResponse<T>{

    private T data;

    private String errors;

    private PagingResponse paging;

    private HttpStatus status;
}
