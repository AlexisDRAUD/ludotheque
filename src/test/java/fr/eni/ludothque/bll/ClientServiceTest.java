package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Adresse;
import fr.eni.ludothque.bo.Client;
import fr.eni.ludothque.dal.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @MockitoBean
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Ajout d'un client cas positif")
    public void ajoutClientCasPositif() {
        //Arrange
        Adresse adresse = new Adresse("7 rue Colette Magny", "44100", "Nantes");
        Client client = new Client("n1", "p1", "e1", "tel1", adresse);
        doAnswer(invocation -> {
                client.setId(9);
                return client;
        }).when(clientRepository).save(client);

        //Act
        clientService.addClient(client);

        //Assert
        assertNotNull(client.getId());
        assertEquals(9, client.getId());
    }
}