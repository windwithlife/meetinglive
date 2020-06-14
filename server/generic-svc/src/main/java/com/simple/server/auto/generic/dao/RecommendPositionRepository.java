package com.simple.server.auto.generic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.simple.server.auto.generic.entity.*;

public interface RecommendPositionRepository extends JpaRepository<RecommendPosition, Long> {
    public  List<RecommendPosition> findByName(String name);
    public  List<RecommendPosition> findByNameLike(String name);

    public  RecommendPosition findOneByName(String name);
    public  RecommendPosition findOneById(Long id);

    
        
}
