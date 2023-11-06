package esa.askerestful.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LazyLoadResponse {
    public int pages;

    public int size;

    public int totalElements;

    public int totalPage;
}
