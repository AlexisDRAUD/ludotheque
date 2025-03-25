package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Adresse;
import fr.eni.ludothque.bo.Client;

import java.util.List;

public interface ClientService {

    void addClient(Client client);
    void removeClient(int id);
    Client findClientById(int id);
    List<Client> findAllClients();
    List<Client> findAllClientByNom(String nom);
    void updateClient(Client client);
    void partialUpdateClient(Integer id, Client client);
    void updateClientAddress(int id, Adresse address);
}