package center.cyq.software.dao;

import center.cyq.software.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BannerDao {
    // nums -> 需要返回的轮播图的个数
    List<Banner> getBanners(Integer nums);
}
