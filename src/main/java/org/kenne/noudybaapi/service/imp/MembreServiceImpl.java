package org.kenne.noudybaapi.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.kenne.noudybaapi.domain.Membre;
import org.kenne.noudybaapi.dto.MembreRequestDTO;
import org.kenne.noudybaapi.dto.MembreResponseDTO;
import org.kenne.noudybaapi.exception.EntityNotFoundException;
import org.kenne.noudybaapi.exception.MemberDeleteException;
import org.kenne.noudybaapi.mapper.MembreMapper;
import org.kenne.noudybaapi.repository.ContributionRepository;
import org.kenne.noudybaapi.repository.MembreRepository;
import org.kenne.noudybaapi.repository.SouscriptionRepository;
import org.kenne.noudybaapi.repository.VilleRepository;
import org.kenne.noudybaapi.service.declaration.MembreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MembreServiceImpl implements MembreService {

    private final MembreRepository membreRepository;
    private final VilleRepository villeRepository;
    private final SouscriptionRepository souscriptionRepository;
    private final ContributionRepository contributionRepository;

    @Override
    public MembreResponseDTO save(MembreRequestDTO requestDTO) throws Exception {
        log.info("Save new membrer {}");
        String code = generateMemberCode(5);
        Membre membre = MembreMapper.INSTANCE.fromRequestToEntity(requestDTO);
        membre.setCode(code);
        membre.setPhoto(this.setMemberImage());
        membre.setVille(villeRepository.getById(requestDTO.getIdVille()));
        Membre savedMember = membreRepository.save(membre);
        return MembreMapper.INSTANCE.fromEntityToResponse(savedMember);
    }

    @Override
    public MembreResponseDTO edit(MembreRequestDTO requestDTO) throws Exception {
        log.info("Edit member with {id} ", requestDTO.getIdMembre());

        Membre membre = membreRepository.findById(requestDTO.getIdMembre())
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id : " + requestDTO.getIdMembre()));
        membre.setNom(requestDTO.getNom());
        membre.setPrenom(requestDTO.getPrenom());
        membre.setContact(requestDTO.getContact());
        membre.setState(requestDTO.isState());
        membre.setVille(villeRepository.getById(requestDTO.getIdVille()));
        return MembreMapper.INSTANCE.fromEntityToResponse(membreRepository.saveAndFlush(membre));
    }

    @Override
    public MembreResponseDTO findById(Long id) {
        log.info("Fetch member with {Id} ", id);
        Optional<Membre> membre = membreRepository.findById(id);
        return membre.map(MembreMapper.INSTANCE::fromEntityToResponse).orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        log.info("Delete member with {id} ", id);
        if (!souscriptionRepository.findSouscriptionByIdMembre(id).isEmpty())
            throw new MemberDeleteException("Member can not be deleted", "The member has many subscriptions");

        if (!contributionRepository.findContributionByIdMembre(id).isEmpty())
            throw new MemberDeleteException("Member can not be deleted", "The member has many contribtions");

        membreRepository.deleteById(id);
        return true;
    }

    @Override
    public List<MembreResponseDTO> getAllMembers() {
        log.info("Fetch all members");
        return membreRepository.findAll()
                .stream()
                .map(MembreMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void uplaoadImage(Long id) {

    }

    private String generateMemberCode(int nbreOfCarracter) {
        String code = "MEM" + RandomStringUtils.randomAlphanumeric(nbreOfCarracter).toUpperCase() + StringUtils.leftPad(nextId().toString(), 4, '0');
        Optional<Membre> membre = membreRepository.findByCode(code);
        return !membre.isPresent() ? code : generateMemberCode(nbreOfCarracter);
    }

    private String setMemberImage() {
        String[] imageNames = {"user_avatar_1.png", "user_avatar_2.png", "user_avatar_3.png", "user_avatar_4.jpg", "user_avatar_5.png", "user_avatar_5.png"};
        // return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
        return imageNames[new Random().nextInt(imageNames.length)];
    }

    private Long nextId() {
        try {
            return membreRepository.findMaxIdmembre() + 1;
        } catch (Exception e) {
            return 1l;
        }
    }
}
