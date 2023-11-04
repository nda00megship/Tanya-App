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
    private String idPertanyaan;

    @Size(max = 255)
    private String header;

    @Size(max = 1000)
    private String deskripsi;
}
