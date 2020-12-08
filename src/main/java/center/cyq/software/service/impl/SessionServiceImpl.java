package center.cyq.software.service.impl;

import center.cyq.software.dao.SessionDao;
import center.cyq.software.entity.Session;
import center.cyq.software.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
    private SessionDao sessionDao;
    @Autowired
    public SessionServiceImpl(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @Override
    public Session getSession(String mail) {
        return sessionDao.getSession(mail);
    }

    @Override
    public Integer updateCode(Session session) {
        return sessionDao.updateCode(session);
    }

    @Override
    public Integer addMail(String mail) {
        return sessionDao.addMail(mail);
    }
}
