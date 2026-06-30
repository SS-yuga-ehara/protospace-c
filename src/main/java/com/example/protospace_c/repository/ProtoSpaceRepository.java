package com.example.protospace_c.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import com.example.protospace_c.entity.ProtoTypeEntity;

@Mapper
public interface ProtoSpaceRepository {
    @Select("""
        SELECT
            p.id,
            p.name,
            p.image,
            p.catchcopy,
            p.concept,
            u.name AS user_name
        FROM prototypes p
        JOIN users u ON p.user_id = u.id
        ORDER BY p.created_at DESC
    """)
    List<ProtoTypeEntity> findAll();
}
