package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Client;
import fr.eni.ludothque.bo.Exemplaire;

import java.util.List;

public interface LocationService {

    void addLocation(Client client, Exemplaire exemplaire);
    void addLocationByBarcode(String clientId, String codeBarre);
    void returnLocations(List<String> codeBarres, String clientId);
}