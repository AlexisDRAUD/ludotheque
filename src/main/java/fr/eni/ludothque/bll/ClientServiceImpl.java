package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Adresse;
import fr.eni.ludothque.bo.Client;
import fr.eni.ludothque.dal.AdresseRepository;
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
    @NonNull
    private AdresseRepository adresseRepository;

    @Override
    public void addClient(Client client) {
        Adresse savedAdresse = adresseRepository.save(client.getAdresse());
        client.setAdresse(savedAdresse);
        clientRepository.save(client);
    }

    @Override
    public void removeClient(String id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client findClientById(String id) {
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
        Optional<Client> savedClient = clientRepository.findById(client.getId());
        if (savedClient.isPresent()) {
            Adresse savedAdresse = savedClient.get().getAdresse();
            Adresse newAdresse = client.getAdresse();
            newAdresse.setId(savedAdresse.getId());
            savedAdresse = adresseRepository.save(newAdresse);
            client.setAdresse(savedAdresse);
            clientRepository.save(client);
        } else {
            throw new RuntimeException("Client non trouvé avec l'ID : " + client.getId());
        }
    }

    @Override
    public void partialUpdateClient(String id, Client updates) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            if (updates.getNom() != null) client.setNom(updates.getNom());
            if (updates.getPrenom() != null) client.setPrenom(updates.getPrenom());
            if (updates.getEmail() != null) client.setEmail(updates.getEmail());
            if (updates.getNoTel() != null) client.setNoTel(updates.getNoTel());

            if (updates.getAdresse() != null) {
                Adresse existingAdresse = client.getAdresse();
                Adresse newAdresse = updates.getAdresse();

                if (existingAdresse != null) {
                    if (newAdresse.getRue() != null) existingAdresse.setRue(newAdresse.getRue());
                    if (newAdresse.getCodePostal() != null) existingAdresse.setCodePostal(newAdresse.getCodePostal());
                    if (newAdresse.getVille() != null) existingAdresse.setVille(newAdresse.getVille());
                    adresseRepository.save(existingAdresse);
                } else {
                    Adresse savedAdresse = adresseRepository.save(newAdresse);
                    client.setAdresse(savedAdresse);
                }
            }

            clientRepository.save(client);
        } else {
            throw new RuntimeException("Client non trouvé avec l'ID : " + id);
        }
    }

    @Override
    public void updateClientAddress(String id, Adresse address) {
        Optional<Client> client = clientRepository.findById(id);
        client.ifPresent((c) -> c.setAdresse(address));
    }
}