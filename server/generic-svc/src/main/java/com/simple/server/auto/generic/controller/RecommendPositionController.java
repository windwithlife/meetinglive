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
@RequestMapping("/generic/recommendPosition")
public class RecommendPositionController {
	@Autowired
	RecommendPositionService service;

	// @Autowired
    // QueryDao queryDao;

    
   
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	@ResponseBody
	public RecommendPositionsResponse findAll() {
		return service.findAll();
	}
	@ResponseBody
    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public RecommendPositionResponse findByKeyId(@PathVariable Long id) {
       	System.out.println("input param Id:" + id);
       	RecommendPositionResponse result = service.findById(id);
    	return result;
    }
    @ResponseBody
    @RequestMapping(value = "/queryByNameLike/", method = RequestMethod.GET)
    public RecommendPositionsResponse findByNameLike(@RequestParam("name") String name ) {
           	System.out.println("input param Name:" + name);
            return service.findByNameLike(name);

    }


    @ResponseBody
    @RequestMapping(value = "/queryByName", method = RequestMethod.GET)
    public RecommendPositionsResponse findByName(@RequestParam("name") String name ) {
           	System.out.println("input param Name:" + name);
            return service.findByName(name);

    }

    @ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public  RecommendPositionResponse addSave(@RequestBody RecommendPositionRequest item) {

		System.out.println("input device params:" + item.toString());
		RecommendPositionResponse result = service.save(item);
		System.out.println("output device result data:" + result.toString());
		return result;
	}



 	@ResponseBody
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public RecommendPositionResponse  updateSave(@RequestBody RecommendPositionRequest item,@PathVariable Long id) {
     	System.out.println("input params id and name:" + item.toString());
     	RecommendPositionResponse result= null;
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