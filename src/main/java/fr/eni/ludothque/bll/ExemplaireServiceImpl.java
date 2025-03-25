package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Exemplaire;
import fr.eni.ludothque.dal.ExemplaireRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExemplaireServiceImpl implements ExemplaireService {

    @NonNull
    private ExemplaireRepository exemplaireRepository;

    @Override
    public void addExemplaire(Exemplaire exemplaire) {
        exemplaireRepository.save(exemplaire);
    }
}