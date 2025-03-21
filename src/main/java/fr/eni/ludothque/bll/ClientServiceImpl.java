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
    public void removeClient(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public Client findClientById(int id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    @Override
    public List<Client> findAllClientByNom(String nom) {
        return clientRepository.findByNom(nom);
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateClientAddress(int id, Adresse address) {
        Optional<Client> client = clientRepository.findById(id);
        client.ifPresent((c) -> c.setAdresse(address));
    }
}