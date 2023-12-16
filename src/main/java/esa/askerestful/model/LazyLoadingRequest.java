package esa.askerestful.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LazyLoadingRequest {

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}
