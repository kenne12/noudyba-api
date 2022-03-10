package org.kenne.noudybaapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.kenne.noudybaapi.domain.Role;
import org.kenne.noudybaapi.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtProvider {

    private final UtilisateurRepository utilisateurRepository;
    private static Long jwtExpirationInMillis = SecurityConstant.EXPIRATION_TIME;

    public String generateTokenWithUserName(String username) {

        Algorithm algorithm = Algorithm.HMAC256(SecurityConstant.SECRET.getBytes());
        String accessToken = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationInMillis))
                .withClaim("roles", utilisateurRepository.findByUsername(username)
                        .get().getRoles()
                        .stream().map(Role::getName)
                        .collect(Collectors.toList()))
                .sign(algorithm);

        return accessToken;
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
}
