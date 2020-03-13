package com.lms.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.lms.entity.DocList;
import com.lms.entity.VideoList;
import com.lms.interception.RequiredToken;
import com.lms.service.DocListService;
import com.lms.service.VideoListService;
import com.lms.utils.Result;
import com.lms.vo.DocListVO;
import com.lms.vo.VideoListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class VideoListController {

    @Autowired
    private VideoListService videoListService;

    /***
     * 查询所有视频，未针对用户进行个性化处理
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/video/all")
    public Result<List<VideoList>> all(){
        List<VideoList> videoLists = videoListService.findAllOrder();
        return new Result<List<VideoList>>().setData(videoLists).setStatus(200).setMsg("获取视频成功");
    }

    /***
     * 针对用户进行个性化数据处理，添加学习标志
     * @param request
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/video/all/person")
    @RequiredToken
    public Result<List<VideoListVO>> allPeron(HttpServletRequest request){
        //无论查询到与否  docList对象都会生成，如果未查询到，其size为0
        List<VideoListVO> videoListVOList = videoListService.findAllOrderAddFlag((long)request.getAttribute("uiid"));
        return new Result<List<VideoListVO>>().setData(videoListVOList).setStatus(200).setMsg("获取视频成功");
    }

    @CrossOrigin
    @GetMapping(value = "/video/getpath")
    @RequiredToken
    public Result<String> getPath(HttpServletRequest request){
        VideoList videoList;
        try{
            Integer dlid = Integer.parseInt(request.getParameter("vlid"));
            videoList = videoListService.findByVlid(dlid);
        }catch (Exception e){
            return new Result<String>().setMsg("获取文档路径失败").setStatus(500);
        }
        if (videoList!=null){
            return new Result<String>().setStatus(200).setData(videoList.getPath()).setMsg("获取视频路径成功");
        }
        return new Result<String>().setMsg("获取视频路径失败").setStatus(500);

    }

    @CrossOrigin
    @GetMapping("video/search")
    @RequiredToken
    public Result<VideoList> search(HttpServletRequest request){
        String name = request.getParameter("name");
        VideoList videoList = videoListService.findByName(name);
        return new Result<VideoList>().setData(videoList).setStatus(200).setMsg("查询视频");
    }

    @CrossOrigin
    @PostMapping("doc/add")
    @RequiredToken
    public Result<VideoList> add(HttpServletRequest request){
        String cover = request.getParameter("cover");
        String path = request.getParameter("path");
        String name = request.getParameter("name");
        Integer duration = Integer.valueOf(request.getParameter("duration"));
        VideoList videoList = new VideoList();
        videoList.setCover(cover).setPath(path).setName(name).setDuration(duration);
        videoList = videoListService.save(videoList);
        return new Result<VideoList>().setData(videoList).setStatus(200).setMsg("新增视频");
    }


}
