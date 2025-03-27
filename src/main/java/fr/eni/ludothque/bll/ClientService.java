package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Adresse;
import fr.eni.ludothque.bo.Client;

import java.util.List;

public interface ClientService {

    void addClient(Client client);
    void removeClient(String id);
    Client findClientById(String id);
    List<Client> findAllClients();
    List<Client> findAllClientByNom(String nom);
    void updateClient(Client client);
    void partialUpdateClient(String id, Client client);
    void updateClientAddress(String id, Adresse address);
}