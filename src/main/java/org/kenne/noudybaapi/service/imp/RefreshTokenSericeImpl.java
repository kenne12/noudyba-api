package org.kenne.noudybaapi.service.imp;

import lombok.AllArgsConstructor;
import org.kenne.noudybaapi.domain.RefreshToken;
import org.kenne.noudybaapi.exception.EntityNotFoundException;
import org.kenne.noudybaapi.repository.RefreshTokenRepository;
import org.kenne.noudybaapi.service.declaration.RefreshTokenService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class RefreshTokenSericeImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("Invalid refresh Token"));
    }

    @Override
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
