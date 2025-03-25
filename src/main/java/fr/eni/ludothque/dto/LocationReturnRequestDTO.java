package fr.eni.ludothque.dto;

import java.util.List;

import lombok.Data;

@Data
public class LocationReturnRequestDTO {
    private Integer clientId;
    private List<String> codeBarres;
}
