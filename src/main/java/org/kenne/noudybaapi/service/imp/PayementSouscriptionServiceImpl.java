package org.kenne.noudybaapi.service.imp;

import lombok.AllArgsConstructor;
import org.kenne.noudybaapi.domain.Operation;
import org.kenne.noudybaapi.enumeration.OperationType;
import org.kenne.noudybaapi.domain.PayementSouscription;
import org.kenne.noudybaapi.domain.Souscription;
import org.kenne.noudybaapi.dto.OperationRequestDTO;
import org.kenne.noudybaapi.dto.PayementSouscriptionRequestDTO;
import org.kenne.noudybaapi.dto.PayementSouscriptionResponseDTO;
import org.kenne.noudybaapi.exception.EntityDeletionException;
import org.kenne.noudybaapi.exception.EntityNotFoundException;
import org.kenne.noudybaapi.mapper.PayementSouscriptionMapper;
import org.kenne.noudybaapi.repository.PayementSouscriptionRepository;
import org.kenne.noudybaapi.repository.SouscriptionRepository;
import org.kenne.noudybaapi.service.declaration.OperationService;
import org.kenne.noudybaapi.service.declaration.PayementSouscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PayementSouscriptionServiceImpl implements PayementSouscriptionService {

    private final PayementSouscriptionRepository payementSouscriptionRepository;
    private final SouscriptionRepository souscriptionRepository;
    private final OperationService operationService;

    @Override
    public PayementSouscriptionResponseDTO save(PayementSouscriptionRequestDTO requestDTO) {

        Souscription souscription = souscriptionRepository.findById(requestDTO.getIdSouscription())
                .orElseThrow(() -> new EntityNotFoundException("Souscription not found with id : " + requestDTO.getIdSouscription()));

        // if ((souscription.getMontant() != souscription.getMontantPaye()))
        // save operation

        OperationRequestDTO operationRequest = OperationRequestDTO.builder()
                .montant(requestDTO.getMontant())
                .dateOperation(requestDTO.getDatePayement())
                .idAnnee(souscription.getEvenement().getAnnee().getIdAnnee())
                .idMembre(souscription.getMembre().getIdMembre())
                .operationType(OperationType.PROJET)
                .idRubrique(souscription.getEvenement().getRubrique().getIdRubrique())
                .build();

        operationRequest.setLibelle("Payement Souscription ................. Rubrique ...................... " + souscription.getEvenement().getRubrique().getNom());
        Operation operation = operationService.save(operationRequest);

        // save payement souscription
        PayementSouscription payementSouscription = PayementSouscriptionMapper.INSTANCE.fromRequestToEntity(requestDTO);
        payementSouscription.setSouscription(souscription);
        payementSouscription.setDatePayement(Date.from(Instant.now()));
        payementSouscription.setOperation(operation);

        // update initial souscription
        souscription.setMontantPaye(souscription.getMontantPaye() + payementSouscription.getMontant());
        souscriptionRepository.save(souscription);
        return PayementSouscriptionMapper.INSTANCE.fromEntityToResponse(payementSouscriptionRepository.save(payementSouscription));
    }

    @Override
    public PayementSouscriptionResponseDTO edit(PayementSouscriptionRequestDTO requestDTO) {
        PayementSouscription payement = payementSouscriptionRepository.findById(requestDTO.getIdPayementSouscription())
                .orElseThrow(() -> new EntityNotFoundException("Payement not found with id : " + requestDTO.getIdPayementSouscription()));

        if (!Objects.equals(requestDTO.getMontant(), payement.getMontant())) {
            Operation operation = payement.getOperation();
            operation.setMontant(requestDTO.getMontant());
            payement.setOperation(  operation );
        }
        return PayementSouscriptionMapper.INSTANCE.fromEntityToResponse(payement);
    }

    @Override
    public boolean delete(Long id) {
        PayementSouscription ps = payementSouscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityDeletionException("Payement can not be deleted", "Payement not found with id : " + id));
        // update initial souscription
        Souscription s = ps.getSouscription(); //souscriptionRepository.getById(ps.getSouscription().getIdSouscription());
        s.setMontantPaye(s.getMontantPaye() - ps.getMontant());
        souscriptionRepository.save(s);
        // delete payement
        payementSouscriptionRepository.deleteById(ps.getIdPayementSouscription());

        // delete associated operation
        operationService.delete(id);
        return true;
    }

    @Override
    public PayementSouscriptionResponseDTO findById(Long id) {
        Optional<PayementSouscription> response = payementSouscriptionRepository.findById(id);
        return response.map(PayementSouscriptionMapper.INSTANCE::fromEntityToResponse).orElse(null);
    }

    @Override
    public List<PayementSouscriptionResponseDTO> getAll() {
        return payementSouscriptionRepository.findAll()
                .stream()
                .map(PayementSouscriptionMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }
}
