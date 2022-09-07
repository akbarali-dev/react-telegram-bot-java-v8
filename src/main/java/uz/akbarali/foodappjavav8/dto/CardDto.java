package uz.akbarali.foodappjavav8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardDto {
    @NotNull(message = "order ID empty")
    private UUID orderId;
    @NotNull(message = "empty product list")
    private List<ProductDto> products;
}
