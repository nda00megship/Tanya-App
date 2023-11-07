package esa.askerestful.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LazyLoadResponse {
    public int totalSize;

    public int size;

}
