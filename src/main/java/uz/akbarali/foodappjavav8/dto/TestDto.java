package uz.akbarali.foodappjavav8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestDto {

    private UUID product_id;

    private Integer quantity;
}
