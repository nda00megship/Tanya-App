package esa.askerestful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdatePertanyaanReq {

    @JsonIgnore
    @NotBlank
    private String idPertanyaan;

    @NotBlank
    @Size(max = 255)
    private String header;

    @NotBlank
    @Size(max = 1000)
    private String deskripsi;
}
