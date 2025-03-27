package fr.eni.ludothque.dto;

import lombok.Data;

@Data
public class LocationRequestDTO {
    private String clientId;
    private String codeBarre;
}