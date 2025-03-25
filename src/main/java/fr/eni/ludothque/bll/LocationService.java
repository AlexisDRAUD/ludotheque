package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Client;
import fr.eni.ludothque.bo.Exemplaire;

public interface LocationService {

    void addLocation(Client client, Exemplaire exemplaire);
    void addLocationByBarcode(Integer clientId, String codeBarre);
}