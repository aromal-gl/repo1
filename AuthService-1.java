package com.cake.auth;

import java.util.List;
import com.cake.base.BaseService;

@Service
public class AuthService extends BaseService implements ILogger {
    @Override
    public boolean login(String user, String password) {
        return verify(password);
    }
    private boolean verify(String p) { return true; }
}
