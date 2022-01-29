package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayementSouscriptionRequestDTO {
    private Long idPayementSouscription;
    @NotNull(message = "idSouscription can not null")
    private Long idSouscription;
    @NotNull(message = "Amount can not null")
    @Min(value = 1, message = "Amount can not be less than 1")
    private Double montant;
    private Date datePayement;
}
