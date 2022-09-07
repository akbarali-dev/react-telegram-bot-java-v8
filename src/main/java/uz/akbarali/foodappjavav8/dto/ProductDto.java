package uz.akbarali.foodappjavav8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    @NotNull(message = "empty product id")
    private UUID id;
    @NotNull(message = "empty product count")
    private Integer count;
}
