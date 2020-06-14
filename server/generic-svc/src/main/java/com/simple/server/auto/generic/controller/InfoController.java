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
@RequestMapping("/generic/info")
public class InfoController {
	@Autowired
	InfoService service;

	// @Autowired
    // QueryDao queryDao;

    
   
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	@ResponseBody
	public InfosResponse findAll() {
		return service.findAll();
	}
	@ResponseBody
    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public InfoResponse findByKeyId(@PathVariable Long id) {
       	System.out.println("input param Id:" + id);
       	InfoResponse result = service.findById(id);
    	return result;
    }
    @ResponseBody
    @RequestMapping(value = "/queryByNameLike/", method = RequestMethod.GET)
    public InfosResponse findByNameLike(@RequestParam("name") String name ) {
           	System.out.println("input param Name:" + name);
            return service.findByNameLike(name);

    }


    @ResponseBody
    @RequestMapping(value = "/queryByName", method = RequestMethod.GET)
    public InfosResponse findByName(@RequestParam("name") String name ) {
           	System.out.println("input param Name:" + name);
            return service.findByName(name);

    }

    @ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public  InfoResponse addSave(@RequestBody InfoRequest item) {

		System.out.println("input device params:" + item.toString());
		InfoResponse result = service.save(item);
		System.out.println("output device result data:" + result.toString());
		return result;
	}



 	@ResponseBody
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public InfoResponse  updateSave(@RequestBody InfoRequest item,@PathVariable Long id) {
     	System.out.println("input params id and name:" + item.toString());
     	InfoResponse result= null;
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