package com.lms.controller;

import com.lms.entity.DocList;
import com.lms.entity.VideoList;
import com.lms.interception.RequiredToken;
import com.lms.service.VideoListService;
import com.lms.utils.Result;
import com.lms.vo.DocListVO;
import com.lms.vo.VideoLearnVO;
import com.lms.vo.VideoListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        List<VideoList> videoLists = videoListService.findAllOrderByCreatetimeDesc();
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
        List<VideoListVO> videoListVOMap = videoListService.findAllOrderAddFlag((long)request.getAttribute("uiid"));
        return new Result<List<VideoListVO>>().setData(videoListVOMap).setStatus(200).setMsg("获取视频成功");
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
            return new Result<String>().setMsg("获取视频路径失败").setStatus(500);
        }
        if (videoList!=null){
            return new Result<String>().setStatus(200).setData(videoList.getPath()).setMsg("获取视频路径成功");
        }
        return new Result<String>().setMsg("获取视频路径失败").setStatus(500);

    }

    @CrossOrigin
    @PostMapping("video/delete")
    @RequiredToken
    public void delete(HttpServletRequest request){
        Integer vlid = Integer.valueOf(request.getParameter("vlid"));
        videoListService.delete(vlid);
    }

    @CrossOrigin
    @PostMapping(value = "/admin/videolist/add")
    @RequiredToken
    public Result<VideoListVO> addDoc(@RequestBody VideoList videoList, HttpServletRequest request) {
        VideoListVO videoListVO = new VideoListVO();
        try {
            Long uiid = (long) request.getAttribute("uiid");
            videoList = videoListService.saveAndRefresh(videoList.setUiid(uiid));
            BeanUtils.copyProperties(videoList, videoListVO);
        } catch (Exception e) {
            return new Result<VideoListVO>().setStatus(500).setMsg("新建视频失败");
        }
        return new Result<VideoListVO>().setStatus(200).setMsg("新建视频成功").setData(videoListVO);
    }
}
