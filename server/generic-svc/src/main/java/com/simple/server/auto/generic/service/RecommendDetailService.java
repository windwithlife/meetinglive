package com.simple.server.auto.generic.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simple.server.auto.generic.dao.*;
import com.simple.server.auto.generic.entity.*;
import com.simple.server.auto.generic.dto.*;


@Service
public class RecommendDetailService {
	@Autowired
	RecommendDetailRepository dao;
	public RecommendDetailsResponse findAll(){
		return  transferEntity2ResponseListDto(dao.findAll());
		//return items;
	}
	public RecommendDetailsResponse findByName(String name){
		return transferEntity2ResponseListDto(dao.findByName(name));
	}
	public  RecommendDetailsResponse findByNameLike(String name){
    		return transferEntity2ResponseListDto(dao.findByNameLike(name));
    }

	public  RecommendDetailResponse findOneByName(String name){
    		return transferEntity2ResponseDto(dao.findOneByName(name));
    	}

	public RecommendDetailResponse findById(Long id){
		return transferEntity2ResponseDto(dao.findOneById(id));
	}
	public RecommendDetailResponse save(RecommendDetailRequest item){
		RecommendDetail entityObj = transferRequestDto2Entity(item);
		return transferEntity2ResponseDto(this.dao.save(entityObj));
	}

	public RecommendDetailResponse update(RecommendDetailRequest item){

		RecommendDetailResponse result= null;
		
        try{
            RecommendDetail oldEntity = dao.findOneById(item.getId());
          
                oldEntity.setId(item.getId());
          
                oldEntity.setName(item.getName());
          
                oldEntity.setImage(item.getImage());
          
                oldEntity.setDescription(item.getDescription());
          
                oldEntity.setPath(item.getPath());
          
                oldEntity.setPositionId(item.getPositionId());
          
		  RecommendDetail entityObj = dao.save(oldEntity);
		  return transferEntity2ResponseDto(entityObj);
        }catch (Exception e){
                System.out.println("***************failed to update item******  ***********");
                e.printStackTrace();
                return null;
        }
		
	}
	public void remove(Long id){
		//this.dao.delete(id);
		this.dao.deleteById(id);
	}
	
	
	
    public RecommendDetail transferRequestDto2Entity(RecommendDetailRequest inputDto){
		RecommendDetail newEntity = new RecommendDetail();
		
				newEntity.setId(inputDto.getId());
		
				newEntity.setName(inputDto.getName());
		
				newEntity.setImage(inputDto.getImage());
		
				newEntity.setDescription(inputDto.getDescription());
		
				newEntity.setPath(inputDto.getPath());
		
				newEntity.setPositionId(inputDto.getPositionId());
		
		return newEntity;
	}

	public RecommendDetailResponse transferEntity2ResponseDto(RecommendDetail entityObj){
		RecommendDetailResponse response = new RecommendDetailResponse();
		
				response.setId(entityObj.getId());
		
				response.setName(entityObj.getName());
		
				response.setImage(entityObj.getImage());
		
				response.setDescription(entityObj.getDescription());
		
				response.setPath(entityObj.getPath());
		
				response.setPositionId(entityObj.getPositionId());
		
		return response;
	}

	public RecommendDetailsResponse transferEntity2ResponseListDto(List<RecommendDetail> entityObjs){

		RecommendDetailsResponse responseList = new RecommendDetailsResponse();
        List<RecommendDetailResponse> items = new ArrayList<RecommendDetailResponse>();
	    for(int i=0; i< entityObjs.size(); i++){
			RecommendDetailResponse response = transferEntity2ResponseDto(entityObjs.get(i));
			items.add(response);
			
		}
		responseList.setItems(items);
		responseList.setItemsCount(entityObjs.size());
		return responseList;
		
	}

}
