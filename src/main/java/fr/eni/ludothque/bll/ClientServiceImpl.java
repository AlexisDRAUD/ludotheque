package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Adresse;
import fr.eni.ludothque.bo.Client;
import fr.eni.ludothque.dal.ClientRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @NonNull
    private ClientRepository clientRepository;

    @Override
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void removeClient(int id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client findClientById(int id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> findAllClientByNom(String nom) {
        return clientRepository.findByNom(nom);
    }

    @Override
    public void updateClient(Client client) {
        if (clientRepository.findById(client.getId()).isPresent()) {
            clientRepository.save(client);
        } else {
            throw new RuntimeException("Client non trouvé avec l'ID : " + client.getId());
        }
    }

    @Override
    public void partialUpdateClient(Integer id, Client updates) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            if (updates.getNom() != null) client.setNom(updates.getNom());
            if (updates.getPrenom() != null) client.setPrenom(updates.getPrenom());
            if (updates.getEmail() != null) client.setEmail(updates.getEmail());
            if (updates.getAdresse() != null) client.setAdresse(updates.getAdresse());

            clientRepository.save(client);
        } else {
            throw new RuntimeException("Client non trouvé avec l'ID : " + id);
        }
    }

    @Override
    public void updateClientAddress(int id, Adresse address) {
        Optional<Client> client = clientRepository.findById(id);
        client.ifPresent((c) -> c.setAdresse(address));
    }
}