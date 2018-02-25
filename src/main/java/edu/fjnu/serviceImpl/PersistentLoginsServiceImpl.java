package edu.fjnu.serviceImpl;

import edu.fjnu.entity.PersistentLogins;
import edu.fjnu.mapper.PersistentLoginsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author xiaoze
 * @date 2018/2/25
 */
@Service
@Transactional
public class PersistentLoginsServiceImpl implements PersistentTokenRepository {

    @Autowired
    PersistentLoginsMapper persistentLoginsMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogins persistentLogins =new PersistentLogins();
        persistentLogins.setUsername(token.getUsername());
        persistentLogins.setSeries(token.getSeries());
        persistentLogins.setToken(token.getTokenValue());
        persistentLogins.setLastUsed(token.getDate());
        persistentLoginsMapper.insertSelective(persistentLogins);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentLogins persistentLogins =new PersistentLogins();
        persistentLogins.setSeries(series);
        persistentLogins.setToken(tokenValue);
        persistentLogins.setLastUsed(lastUsed);
        persistentLoginsMapper.updateByPrimaryKeySelective(persistentLogins);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLogins persistentLogins =persistentLoginsMapper.selectByPrimaryKey(seriesId);


        return new PersistentRememberMeToken(persistentLogins.getUsername(),
                persistentLogins.getSeries(),
                persistentLogins.getToken(),
                persistentLogins.getLastUsed());
    }

    @Override
    public void removeUserTokens(String username) {
        persistentLoginsMapper.deleteByUsername(username);
    }
}
