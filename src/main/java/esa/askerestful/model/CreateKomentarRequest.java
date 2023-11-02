package esa.askerestful.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateKomentarRequest {

    @NotBlank
    @Size(max = 500)
    private String deskripsi;

    @NotBlank
    private String idPertanyaan;

}
