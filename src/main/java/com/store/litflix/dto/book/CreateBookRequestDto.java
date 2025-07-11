package com.store.litflix.dto.book;

import com.store.litflix.validation.ValidIsbn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @ValidIsbn
    private String isbn;
    @Min(0)
    private BigDecimal price;
    @Size(max = 1000)
    private String description;
    @URL
    private String coverImage;
    @NotEmpty
    private List<Long> categoryIds;
}
