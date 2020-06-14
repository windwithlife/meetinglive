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
@RequestMapping("/generic/recommendDetail")
public class RecommendDetailController {
	@Autowired
	RecommendDetailService service;

	// @Autowired
    // QueryDao queryDao;

    
    @Autowired
    private  RecommendPositionService recommendPositionService;
    
    
   
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	@ResponseBody
	public RecommendDetailsResponse findAll() {
		return service.findAll();
	}
	@ResponseBody
    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public RecommendDetailResponse findByKeyId(@PathVariable Long id) {
       	System.out.println("input param Id:" + id);
       	RecommendDetailResponse result = service.findById(id);
    	return result;
    }
    @ResponseBody
    @RequestMapping(value = "/queryByNameLike/", method = RequestMethod.GET)
    public RecommendDetailsResponse findByNameLike(@RequestParam("name") String name ) {
           	System.out.println("input param Name:" + name);
            return service.findByNameLike(name);

    }


    @ResponseBody
    @RequestMapping(value = "/queryByName", method = RequestMethod.GET)
    public RecommendDetailsResponse findByName(@RequestParam("name") String name ) {
           	System.out.println("input param Name:" + name);
            return service.findByName(name);

    }

    @ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public  RecommendDetailResponse addSave(@RequestBody RecommendDetailRequest item) {

		System.out.println("input device params:" + item.toString());
		RecommendDetailResponse result = service.save(item);
		System.out.println("output device result data:" + result.toString());
		return result;
	}



 	@ResponseBody
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public RecommendDetailResponse  updateSave(@RequestBody RecommendDetailRequest item,@PathVariable Long id) {
     	System.out.println("input params id and name:" + item.toString());
     	RecommendDetailResponse result= null;
        try{
            result = service.update(item);
        }catch (Exception e){
                System.out.println("***************failed to update item******  ***********");
                e.printStackTrace();
                return null;
        }
        return result;
    }

    
    @ResponseBody
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public Long removeById(@PathVariable Long id) {
    	service.remove(id);
    	return id;
    }

    
   
   
}