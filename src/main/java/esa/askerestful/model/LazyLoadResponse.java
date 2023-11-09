package esa.askerestful.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LazyLoadResponse<T>{

    private T data;

    private String errors;


}
