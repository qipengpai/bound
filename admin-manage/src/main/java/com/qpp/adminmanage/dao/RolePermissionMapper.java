package com.qpp.adminmanage.dao;

import com.qpp.adminmanage.pojo.RolePermissionKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMapper {
    int deleteByPrimaryKey(RolePermissionKey key);

    int insert(RolePermissionKey record);

    int insertSelective(RolePermissionKey record);

	List<RolePermissionKey> findByRole(int roleId);
}