package center.cyq.software.service.impl;

import center.cyq.software.dao.BannerDao;
import center.cyq.software.entity.Banner;
import center.cyq.software.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    private BannerDao bannerDao;

    @Autowired
    public BannerServiceImpl(BannerDao bannerDao) {
        this.bannerDao = bannerDao;
    }

    @Override
    public List<Banner> getBanners(Integer nums) {
        return bannerDao.getBanners(nums);
    }
}
