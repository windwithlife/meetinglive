package com.simple.server.auto.generic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;



import com.simple.server.auto.generic.entity.*;
import com.simple.server.auto.generic.service.*;
import com.simple.server.auto.generic.dao.*;
import com.simple.server.auto.generic.dto.*;


//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/generic")
public class GenericModuleController {
	
	// @Autowired
    // QueryDao queryDao;

    
    @Autowired
    private  InfoService infoService;
    
    @Autowired
    private  RecommendPositionService recommendPositionService;
    
    @Autowired
    private  RecommendDetailService recommendDetailService;
    
    @Autowired
    private  MenuService menuService;
    
  
 	


    
   
   
}