package com.project.meetinglive.controller;

import java.awt.image.BufferedImage;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.meetinglive.common.util.FileUploadHelp;
import com.project.meetinglive.common.util.JsonUtil;
import com.project.meetinglive.core.data.message.ResponseMessage;
import com.project.meetinglive.core.data.request.JsonMessage;
import com.project.meetinglive.core.exception.CommonExceptionHandle;
import com.project.meetinglive.core.spring.interceptor.annotation.LoginRequired;
import com.project.meetinglive.core.token.ValidateLoginHelp;

/**
 * 图片上传
 * @author hejinguo
 * @version $Id: FileUploadController.java, v 0.1 2020年8月2日 下午5:16:07
 */
@RestController
@RequestMapping("/uploadService")
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    /**
     * 图片上传
     * @param request
     * @return
     */
    @PostMapping(value = { "/uploadImage" }, produces = { "application/json" })
    @LoginRequired
    public @ResponseBody ResponseMessage imageUpload(HttpServletRequest request) {
        //返回对象
        ResponseMessage resMessage = null;
        JsonMessage jsonMessage = null;
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            String json = multipartRequest.getParameter("json");
            jsonMessage = JsonUtil.jsonToObject(json, JsonMessage.class);
            resMessage = new ResponseMessage(jsonMessage);
            //登录校验
            ResponseMessage responseMessage = ValidateLoginHelp.validateUserLogin(jsonMessage);
            if (responseMessage.getStatus() != ResponseMessage.SUCCESS_CODE) {
                return responseMessage;
            }
            //step1:获取图片file
            MultipartFile files = multipartRequest.getFile("file");
            if (files == null || files.isEmpty()) {
                resMessage.setStatus(ResponseMessage.FAILURE_CODE);
                resMessage.setMessage("请上传图片!");
                return resMessage;
            }
            //检查文件大小
            if (files.getSize() > 3 * 1024 * 1024) {
                resMessage.setStatus(ResponseMessage.FAILURE_CODE);
                resMessage.setMessage("请上传3M以内的图片!");
                return resMessage;
            }
            //检查是否是图片
            BufferedImage bi = ImageIO.read(files.getInputStream());
            if (bi == null) {
                resMessage.setStatus(ResponseMessage.FAILURE_CODE);
                resMessage.setMessage("上传的文件不是图片!");
                return resMessage;
            }
            Map<String, Object> uploadMap = FileUploadHelp.imageUpload(files);
            int status = Integer.valueOf(String.valueOf(uploadMap.get("status")));
            if (status == 0) {
                String message = String.valueOf(uploadMap.get("message"));
                logger.error(message);
                resMessage.setMessage(message);
                resMessage.setStatus(ResponseMessage.FAILURE_CODE);
                return resMessage;
            }
            String url = String.valueOf(uploadMap.get("fileUrl"));
            resMessage.addKey$Value("picPath", url);
            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage("上传成功!");
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }
}
