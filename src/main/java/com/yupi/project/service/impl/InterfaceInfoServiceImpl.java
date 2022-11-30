package com.yupi.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.mapper.InterfaceInfoMapper;
import com.yupi.project.model.entity.InterfaceInfo;
import com.yupi.project.service.InterfaceInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author li140
 * @description 针对表【interface_info(接口信息)】的数据库操作Service实现
 * @createDate 2022-11-19 19:21:01
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
        implements InterfaceInfoService {
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {

        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long id = interfaceInfo.getId();
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        Integer status = interfaceInfo.getStatus();
        String method = interfaceInfo.getMethod();
        Long userId = interfaceInfo.getUserId();
        Date createTime = interfaceInfo.getCreateTime();
        Date updateTime = interfaceInfo.getUpdateTime();
        Integer isDelete = interfaceInfo.getIsDelete();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name, description, url, requestHeader, responseHeader, method) || ObjectUtils.anyNull(status)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(requestHeader) && requestHeader.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求头过长");
        }
        if (StringUtils.isNotBlank(responseHeader) && responseHeader.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "响应头过长");
        }

    }
}




