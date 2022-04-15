package com.songblog.service.impl;

import com.songblog.entity.AdType;
import com.songblog.mapper.AdTypeMapper;
import com.songblog.service.IAdTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 广告类型 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Service
public class AdTypeServiceImpl extends ServiceImpl<AdTypeMapper, AdType> implements IAdTypeService {

}
