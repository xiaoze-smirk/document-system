package edu.fjnu.serviceImpl;

import edu.fjnu.entity.PersistentLogins;
import edu.fjnu.mapper.PersistentLoginsMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

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
    PersistentTokenRepository persistentTokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogins persistentLogins =new PersistentLogins();
        if(token!=null) {
            persistentLogins.setUsername(token.getUsername());
            persistentLogins.setSeries(token.getSeries());
            persistentLogins.setToken(token.getTokenValue());
            persistentLogins.setLastUsed(token.getDate());
            persistentLoginsMapper.insertSelective(persistentLogins);
        }
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentLogins persistentLogins =new PersistentLogins();
        if(StringUtils.isNotEmpty(series))
            persistentLogins.setSeries(series);

        if(StringUtils.isNotEmpty(tokenValue))
            persistentLogins.setToken(tokenValue);

        if(lastUsed!=null)
            persistentLogins.setLastUsed(lastUsed);
        if(persistentLogins!=null)
            persistentLoginsMapper.updateByPrimaryKeySelective(persistentLogins);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {

        if(persistentLoginsMapper.selectByPrimaryKey(seriesId)!=null) {
            PersistentLogins persistentLogins = persistentLoginsMapper.selectByPrimaryKey(seriesId);

            return new PersistentRememberMeToken(persistentLogins.getUsername(),
                    persistentLogins.getSeries(),
                    persistentLogins.getToken(),
                    persistentLogins.getLastUsed());
        }
        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        if(StringUtils.isNotEmpty(username))
            persistentLoginsMapper.deleteByUsername(username);
    }
}
