package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.domain.Role;
import org.kenne.noudybaapi.dto.AuthenticationResponse;
import org.kenne.noudybaapi.dto.RefreshTokenRequest;
import org.kenne.noudybaapi.dto.UtilisateurRequestDTO;
import org.kenne.noudybaapi.dto.UtilisateurResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UtilisateurService {

    List<UtilisateurResponseDTO> findAll();

    UtilisateurResponseDTO findByUsername(String username);

    UtilisateurResponseDTO save(UtilisateurRequestDTO utilisateurRequestDTO);

    UtilisateurResponseDTO editData(UtilisateurRequestDTO utilisateurRequestDTO);

    UtilisateurResponseDTO changeStatus(int idUser, boolean state);

    UtilisateurResponseDTO addRoleToUser(String username, String roleName);

    UtilisateurResponseDTO addRolesToUser(String username, String[] roleName);

    UtilisateurResponseDTO removeRole(String username, String roleName);

    UtilisateurResponseDTO removeRoles(String username, String[] roleName);

    void deleteUser(int idUser);

    List<Role> findAbsentRolesByUserName(String username);

    byte[] getUserImage(int id) throws Exception;

    void uploadUserImage(MultipartFile file, int id) throws Exception;

    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
