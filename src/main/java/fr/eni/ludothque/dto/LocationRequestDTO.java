package fr.eni.ludothque.dto;

import lombok.Data;

@Data
public class LocationRequestDTO {
    private Integer clientId;
    private String codeBarre;
}