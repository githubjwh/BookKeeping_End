package com.example.demo.Controller;

import com.example.demo.Service.FileService;
import com.example.demo.Utils.ReturnUtil;
import com.example.demo.Utils.UuidUtil;
import com.example.demo.component.UpLoadConfig;
import com.example.demo.entity.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

@RequestMapping("/console/upload")
public class FileUploadController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UpLoadConfig upConfig;

    private FileService fileService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(Model model, Upload upload){
        upload.setFileExt(fileService.getFileExt(upload.getFileType()));
        upload.setMaxSize(upConfig.getMaxFileSize());
        model.addAttribute("upload", upload);
        return "console/upload/upload";
    }

    @RequestMapping(value = "/uploader", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap postUploader(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        if (!file.isEmpty()) {
            if(StringUtils.isEmpty(upConfig.getHardDisk()) && "local".equals(upConfig.getUpType())){
                return ReturnUtil.error("请配置上传目录");
            }
            String diskPath = upConfig.getHardDisk();
            //扩展名格式
            String originalFilename = file.getOriginalFilename();
            String extName = "";
            if(originalFilename != null) {
            	extName= originalFilename.substring(originalFilename.lastIndexOf("."));
            }
             
            //验证文件类型
            if(!fileService.checkExt(extName)){
                return ReturnUtil.error("上传文件格式不支持");
            }
            //根据文件类型获取上传目录
            String uploadPath = fileService.getUploadPath(extName);
            uploadPath = uploadPath.replace(File.separator,"/");
            if(StringUtils.isEmpty(uploadPath)){
                return ReturnUtil.error("上传文件路径错误");
            }
            String fileName = UuidUtil.getUUID()+extName;
            String retPath = "";
            if("local".equals(upConfig.getUpType()) && StringUtils.isEmpty(upConfig.getUpType())){
                retPath= fileService.fileSave(file, diskPath, uploadPath, fileName);
            }
            if("null".equals(retPath)){
                return ReturnUtil.error("上传文件异常");
            }
            Map<String, String> upMap = fileService.getReturnMap(retPath, fileName);

            return ReturnUtil.success("上传成功",upMap);
        } else {
            return ReturnUtil.error("上传文件为空,");
        }
    }
}