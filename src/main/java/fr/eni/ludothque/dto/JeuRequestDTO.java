package fr.eni.ludothque.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class JeuRequestDTO {
    private String titre;
    private String reference;
    private int ageMin;
    private String description;
    private int duree;
    private float tarifJour;
    private List<Integer> genreIds;
}
