package esa.askerestful.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SearchPertanyaanRequest {

    private String header;

    private String deskripsi;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}
