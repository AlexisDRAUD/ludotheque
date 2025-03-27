package fr.eni.ludothque.api;

import fr.eni.ludothque.bll.ClientService;
import fr.eni.ludothque.bo.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.removeClient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
        return ResponseEntity.ok(client);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Client> partialUpdateClient(@PathVariable String id, @RequestBody Client client) {
        clientService.partialUpdateClient(id, client);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.findAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id) {
        Client client = clientService.findClientById(id);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }
}
