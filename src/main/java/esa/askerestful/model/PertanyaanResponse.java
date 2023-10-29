package esa.askerestful.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PertanyaanResponse {

    private String id;

    private String header;

    private String deskripsi;

    private int suka;
}
