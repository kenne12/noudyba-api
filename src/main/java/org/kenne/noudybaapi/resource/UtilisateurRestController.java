package org.kenne.noudybaapi.resource;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.domain.Role;
import org.kenne.noudybaapi.dto.*;
import org.kenne.noudybaapi.security.SecurityConstant;
import org.kenne.noudybaapi.service.declaration.RefreshTokenService;
import org.kenne.noudybaapi.service.declaration.UtilisateurService;
import org.kenne.noudybaapi.util.UtilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(path = "/api/v1/user")
@Tag(name = "UtilisateurController")
public class UtilisateurRestController {

    private final UtilisateurService utilisateurService;
    private final RefreshTokenService refreshTokenService;

    public UtilisateurRestController(UtilisateurService utilisateurService, RefreshTokenService refreshTokenService) {
        this.utilisateurService = utilisateurService;
        this.refreshTokenService = refreshTokenService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Response<List<UtilisateurResponseDTO>>> getAll() {

        Response<List<UtilisateurResponseDTO>> response = Response.<List<UtilisateurResponseDTO>>builder()
                .datas(UtilService.getUsers("users", utilisateurService.findAll()))
                .message("User list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Response<UtilisateurResponseDTO>> save(@Valid @RequestBody UtilisateurRequestDTO utilisateurRequestDTO) {

        Response<UtilisateurResponseDTO> response = Response.<UtilisateurResponseDTO>builder()
                .datas(UtilService.getData("user", utilisateurService.save(utilisateurRequestDTO)))
                .message("User saved successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<Response<UtilisateurResponseDTO>> edit(@PathVariable(name = "id") Integer id, @Valid @RequestBody UtilisateurRequestDTO utilisateurRequestDTO) {
        Response<UtilisateurResponseDTO> response = Response.<UtilisateurResponseDTO>builder()
                .datas(UtilService.getData("user", utilisateurService.editData(utilisateurRequestDTO)))
                .message("User edited successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/change_status/{id}")
    public ResponseEntity<Response<UtilisateurResponseDTO>> changeState(@PathVariable(name = "id") int id, boolean status) {

        Response<UtilisateurResponseDTO> response = Response.<UtilisateurResponseDTO>builder()
                .datas(UtilService.getData("user", utilisateurService.changeStatus(id, status)))
                .message("User status edited successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/add_role")
    public ResponseEntity<Response<UtilisateurResponseDTO>> addRole(@RequestBody UserRoleForm userRoleForm) {
        Response<UtilisateurResponseDTO> response = Response.<UtilisateurResponseDTO>builder()
                .datas(UtilService.getData("user", utilisateurService.addRoleToUser(userRoleForm.getUsername(), userRoleForm.getRoleName())))
                .message("User Role added successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/add_roles")
    public ResponseEntity<Response<UtilisateurResponseDTO>> addRoles(@RequestBody UserRoleForm userRoleForm) {
        Response<UtilisateurResponseDTO> response = Response.<UtilisateurResponseDTO>builder()
                .datas(UtilService.getData("user", utilisateurService.addRolesToUser(userRoleForm.getUsername(), userRoleForm.getRoleNames())))
                .message("User Roles granted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/remove_role")
    public UtilisateurResponseDTO removeRole(@PathParam(value = "username") String username, @PathParam(value = "rolename") String roleName) {
        return utilisateurService.removeRole(username, roleName);
    }

    @PostMapping(path = "/remove_roles")
    public ResponseEntity<Response<UtilisateurResponseDTO>> removeRole(@RequestBody UserRoleForm userRoleForm) {
        Response<UtilisateurResponseDTO> response = Response.<UtilisateurResponseDTO>builder()
                .datas(UtilService.getData("user", utilisateurService.removeRoles(userRoleForm.getUsername(), userRoleForm.getRoleNames())))
                .message("User Roles revoked successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable(name = "id") int id) {

        utilisateurService.deleteUser(id);
        Response<?> response = Response.builder()
                .message("User removed successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{username}/absent_roles")
    public ResponseEntity<Response<List<Role>>> findAbsentRolesByUsername(@PathVariable(name = "username") String username) {
        Response<List<Role>> response = Response.<List<Role>>builder()
                .datas(UtilService.getRoles("roles", utilisateurService.findAbsentRolesByUserName(username)))
                .message("Roles fetched successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}/get_user_image", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getPhoto(@PathVariable(name = "id") Integer id) throws Exception {
        return this.utilisateurService.getUserImage(id);
    }

    @PostMapping(path = "/{id}/upload_user_image")
    public ResponseEntity<Response<?>> uploadPhoto(MultipartFile file, @PathVariable(name = "id") Integer id) throws Exception {

        this.utilisateurService.uploadUserImage(file, id);
        Response<?> response = Response.builder()
                .message("User image uploaded successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return utilisateurService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!");
    }

    @GetMapping("/token/refresh")
    private void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());


                Algorithm algorithm = Algorithm.HMAC256(SecurityConstant.SECRET.getBytes());

                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UtilisateurResponseDTO user = utilisateurService.findByUsername(username);

                if (user == null) {
                    throw new UsernameNotFoundException("Invalid token");
                }

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> token = new HashMap<>();
                token.put("access_token", "Bearer " + access_token);
                token.put("refresh_token", "Bearer " + refresh_token);

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), token);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setHeader("error", e.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

}
