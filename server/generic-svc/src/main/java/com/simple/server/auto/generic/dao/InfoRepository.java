package com.simple.server.auto.generic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.simple.server.auto.generic.entity.*;

public interface InfoRepository extends JpaRepository<Info, Long> {
    public  List<Info> findByName(String name);
    public  List<Info> findByNameLike(String name);

    public  Info findOneByName(String name);
    public  Info findOneById(Long id);

    
        
}
