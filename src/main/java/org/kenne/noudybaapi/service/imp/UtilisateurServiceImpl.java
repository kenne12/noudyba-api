package org.kenne.noudybaapi.service.imp;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kenne.noudybaapi.domain.Role;
import org.kenne.noudybaapi.domain.Utilisateur;
import org.kenne.noudybaapi.domain.VerificationToken;
import org.kenne.noudybaapi.dto.AuthenticationResponse;
import org.kenne.noudybaapi.dto.RefreshTokenRequest;
import org.kenne.noudybaapi.dto.UtilisateurRequestDTO;
import org.kenne.noudybaapi.dto.UtilisateurResponseDTO;
import org.kenne.noudybaapi.exception.EntityNotFoundException;
import org.kenne.noudybaapi.exception.UserRegistrationPasswordException;
import org.kenne.noudybaapi.mapper.UtilisateurMapper;
import org.kenne.noudybaapi.repository.RoleRepository;
import org.kenne.noudybaapi.repository.UtilisateurRepository;
import org.kenne.noudybaapi.repository.VerificationTokenRepository;
import org.kenne.noudybaapi.security.JwtProvider;
import org.kenne.noudybaapi.service.declaration.RefreshTokenService;
import org.kenne.noudybaapi.service.declaration.UtilisateurService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {


    private final UtilisateurRepository utilisateurRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final VerificationTokenRepository verificationTokenRepository;

    private final RefreshTokenService refreshTokenService;

    private JwtProvider jwtProvider;


    @Override
    public List<UtilisateurResponseDTO> findAll() {
        return utilisateurRepository
                .findAll()
                .stream()
                .map(UtilisateurMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UtilisateurResponseDTO findByUsername(String username) {
        Optional<Utilisateur> user = utilisateurRepository.findByUsername(username);
        if (!user.isPresent())
            return UtilisateurMapper.INSTANCE.fromEntityToResponse(user.get());
        return null;
    }

    @Override
    public UtilisateurResponseDTO save(UtilisateurRequestDTO utilisateurRequestDTO) {
        if (utilisateurRequestDTO.getPassword().equals(utilisateurRequestDTO.getRepeatPassword())) {
            Utilisateur utilisateur = UtilisateurMapper.INSTANCE.fromRequestToEntity(utilisateurRequestDTO);
            utilisateur.setIdUtilisateur(this.nextId());
            utilisateur.setDateCreation(Date.from(Instant.now()));
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            utilisateur.setPhoto(this.setMemberImage());
            UtilisateurResponseDTO u = UtilisateurMapper.INSTANCE.fromEntityToResponse(utilisateurRepository.save(utilisateur));
            this.generateVerificationToken(utilisateur);
            return u;
        }
        throw new UserRegistrationPasswordException("Echec d'enregistrement , les deux mots de passe ne sont pas identiques");
    }

    @Override
    public UtilisateurResponseDTO editData(UtilisateurRequestDTO utilisateurRequestDTO) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurRequestDTO.getIdUtilisateur())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id : " + utilisateurRequestDTO.getIdUtilisateur()));
        utilisateur.setNom(utilisateurRequestDTO.getNom());
        utilisateur.setPrenom(utilisateurRequestDTO.getPrenom());
        utilisateur.setUsername(utilisateurRequestDTO.getUsername());
        return UtilisateurMapper.INSTANCE.fromEntityToResponse(utilisateurRepository.save(utilisateur));
    }

    @Override
    public UtilisateurResponseDTO changeStatus(int idUser, boolean state) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found with found with id : " + idUser));
        utilisateur.setActif(state);
        return UtilisateurMapper.INSTANCE.fromEntityToResponse(utilisateur);
    }

    @Override
    public UtilisateurResponseDTO addRoleToUser(String username, String roleName) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with name : " + username));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with name : " + roleName));

        if (!utilisateur.getRoles().contains(role)) {
            utilisateur.getRoles().add(role);
        }
        return UtilisateurMapper.INSTANCE.fromEntityToResponse(utilisateur);
    }

    @Override
    public UtilisateurResponseDTO addRolesToUser(String username, String[] roles) {
        final Utilisateur utilisateur = utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with name : " + username));

        Arrays.stream(roles).forEach(item -> {
            Optional<Role> role = roleRepository.findByName(item);
            if (role.isPresent()) {
                if (!utilisateur.getRoles().contains(role.get())) {
                    utilisateur.getRoles().add(role.get());
                }
            }
        });

        return UtilisateurMapper.INSTANCE
                .fromEntityToResponse(utilisateurRepository.save(utilisateur));
    }

    @Override
    public UtilisateurResponseDTO removeRole(String username, String roleName) {
        Utilisateur utilisateur = utilisateurRepository
                .findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found with name : " + username));

        Role role = roleRepository
                .findByName(roleName).orElseThrow(() -> new EntityNotFoundException("Role not found with name : " + roleName));

        if (!utilisateur.getRoles().contains(role)) {
            utilisateur.getRoles().remove(role);
        }
        return UtilisateurMapper.INSTANCE.fromEntityToResponse(utilisateur);
    }

    @Override
    public UtilisateurResponseDTO removeRoles(String username, String[] roleName) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found with name : " + username));
        for (int i = 0; i < roleName.length; i++) {
            Optional<Role> role = roleRepository.findByName(roleName[i]);
            if (role.isPresent()) {
                if (utilisateur.getRoles().contains(role.get())) {
                    utilisateur.getRoles().remove(role.get());
                }
            }
        }
        utilisateur = utilisateurRepository.save(utilisateur);
        return UtilisateurMapper.INSTANCE.fromEntityToResponse(utilisateur);
    }

    @Override
    public void deleteUser(int idUser) {
        Utilisateur utilisateur = utilisateurRepository
                .findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found with id : " + idUser));
        utilisateurRepository.delete(utilisateur);
    }

    @Override
    public List<Role> findAbsentRolesByUserName(String username) {
        Utilisateur utilisateur = utilisateurRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username : " + username));
        List<Role> roles = roleRepository.findAll();
        if (!utilisateur.getRoles().isEmpty()) {
            roles.removeAll(utilisateur.getRoles());
        }
        return roles;
    }

    @Override
    public byte[] getUserImage(int id) throws Exception {
        Utilisateur u = utilisateurRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/epargne/images/users/" + u.getPhoto()));
    }


    @Override
    public void uploadUserImage(MultipartFile file, int id) throws Exception {
        Utilisateur utilisateur = utilisateurRepository.findById(id).get();
        utilisateur.setPhoto(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home") + "/epargne/images/users/" + utilisateur.getPhoto()), file.getBytes());
        utilisateurRepository.save(utilisateur);
    }

    private Integer nextId() {
        try {
            Integer value = utilisateurRepository.nextId();
            return value == null ? 1 : value + 1;
        } catch (Exception e) {
            return 1;
        }
    }


    private String generateVerificationToken(Utilisateur utilisateur) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(utilisateur);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Transactional(readOnly = true)
    public Utilisateur getCurrentUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                        getContext().getAuthentication().getPrincipal();

        return utilisateurRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name :  " + principal.getUsername()));
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    private String setMemberImage() {
        String[] imageNames = {"user_avatar_1.png", "user_avatar_2.png", "user_avatar_3.png", "user_avatar_4.jpg", "user_avatar_5.png", "user_avatar_5.png"};
        return imageNames[new Random().nextInt(imageNames.length)];
    }
}
