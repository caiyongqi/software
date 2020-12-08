package center.cyq.software.service;

import center.cyq.software.entity.Session;

public interface SessionService {
    Session getSession(String mail);
    Integer updateCode(Session session);
    Integer addMail(String mail);
}
