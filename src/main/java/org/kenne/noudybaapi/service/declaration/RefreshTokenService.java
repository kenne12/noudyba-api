package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.domain.RefreshToken;


public interface RefreshTokenService {

    RefreshToken generateRefreshToken();

    void validateRefreshToken(String token);

    void deleteRefreshToken(String token);
}
