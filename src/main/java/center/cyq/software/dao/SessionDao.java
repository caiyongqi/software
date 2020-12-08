package center.cyq.software.dao;

import center.cyq.software.entity.Session;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SessionDao {
    Session getSession(String mail);
    Integer updateCode(Session session);
    Integer addMail(String mail);
}
