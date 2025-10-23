package es.daw.productoapirest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {

    @NotBlank(message = "El valor tiene que  rellenarse")
    private String nombre;
    @DecimalMin(value = "100.00", message = "el minimo de precio es de 100 euros")
    private BigDecimal precio;

    @Size(min = 4, max = 4, message = "El codigo debe tener 4 caracteres")
    private String codigo;
    private Integer codigoFabricante;
}