package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Adresse;
import fr.eni.ludothque.bo.Client;

import java.util.List;

public interface ClientService {

    void addClient(Client client);
    void removeClient(Client client);
    Client findClientById(int id);
    List<Client> findAllClientByNom(String nom);
    void updateClient(Client client);
    void updateClientAddress(int id, Adresse address);
}