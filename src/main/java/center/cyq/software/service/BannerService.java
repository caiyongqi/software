package center.cyq.software.service;

import center.cyq.software.entity.Banner;

import java.util.List;

public interface BannerService {
    List<Banner> getBanners(Integer nums);
}
