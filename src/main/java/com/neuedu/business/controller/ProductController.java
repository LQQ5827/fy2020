package com.neuedu.business.controller;

import com.neuedu.business.common.Consts;
import com.neuedu.business.common.RoleEnum;
import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.common.StatusEnum;
import com.neuedu.business.pojo.Product;
import com.neuedu.business.pojo.User;
import com.neuedu.business.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @类 名： ProductController <br/>
 * @描 述： <br/>
 * @日 期： 2020/2/26 9:29<br/>
 * @作 者： 鼠小倩<br/>
 * @版 本： 1.0.0
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/manage/product/")
public class ProductController {
    @Autowired
    IProductService productService;

    //加载变量
    @Value("${upload.path}")
    private String uploadPath;

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){
        return "upload";
    }

    /**
     * 图片上传
     * @param file 待上传的文件
     */
    @RequestMapping(value = "/upload ",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(@RequestParam("picTest") MultipartFile file){
        if(file==null){
            return null;
        }
        //1.获取初始文件的名称 xxx.png
        String fileName=file.getOriginalFilename();
        if (fileName==null){
            return ServerResponse.serverResponseByFail(StatusEnum.UPLOAD_FILENAME_NOT_EMPTY.getStatus(),StatusEnum.UPLOAD_FILENAME_NOT_EMPTY.getDesc());
        }
        //2.获取原文件的扩展名
        String ext=fileName.substring(fileName.lastIndexOf("."));
        //3.重新命名，为文件生成一个唯一的名称
        String newName= UUID.randomUUID().toString();
        //4.新的文件名
        String newFileName=newName+ext;

        //5.创建保存文件的目录
        File target=new File(uploadPath);
        if(!target.exists()){
            target.mkdirs();
        }
        //6.创建文件
        File newFile=new File(uploadPath,newFileName);
        try {
            //7.将文件写入到磁盘，保存文件
            file.transferTo(newFile);//把文件保存到某个目录下
            //往前端返回
            return ServerResponse.serverResponseBySuccess(null,newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 新增OR更新商品
     */
    @RequestMapping("/save.do")
    @ResponseBody
    public ServerResponse addOrUpdate(Product product, HttpSession session){
        //1.先判断用户是否登陆
        User user=(User) session.getAttribute(Consts.USER);
        if(user==null){
            //未登录
            return ServerResponse.serverResponseByFail(StatusEnum.NO_LOGIN.getStatus(),StatusEnum.NO_LOGIN.getDesc());
        }

        //2.已经登陆，判断是否有管理员管理权限
        if(user.getRole()!= RoleEnum.ADMIN.getRole()){
            //是普通用户
            return ServerResponse.serverResponseByFail(StatusEnum.NO_AUTHORUTY.getStatus(),StatusEnum.NO_AUTHORUTY.getDesc());
        }
        ServerResponse serverResponse=productService.addOrProduct(product);
        return serverResponse;
    }
}



