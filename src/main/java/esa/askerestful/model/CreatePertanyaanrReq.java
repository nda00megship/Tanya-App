package esa.askerestful.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreatePertanyaanrReq {

    @NotBlank
    @Size(max = 255)
    private String header;

    @NotBlank
    @Size(max = 1000)
    private String deskripsi;

}
