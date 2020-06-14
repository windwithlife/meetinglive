package com.simple.server.auto.generic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.simple.server.auto.generic.entity.*;

public interface RecommendDetailRepository extends JpaRepository<RecommendDetail, Long> {
    public  List<RecommendDetail> findByName(String name);
    public  List<RecommendDetail> findByNameLike(String name);

    public  RecommendDetail findOneByName(String name);
    public  RecommendDetail findOneById(Long id);

    
        
}
