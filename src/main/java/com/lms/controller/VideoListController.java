package com.lms.controller;

import com.lms.entity.DocList;
import com.lms.entity.VideoList;
import com.lms.interception.RequiredAdmin;
import com.lms.interception.RequiredToken;
import com.lms.service.VideoListService;
import com.lms.utils.Result;
import com.lms.utils.UpdateUtil;
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
    @GetMapping(value = "/admin/video/deleteone")
    @RequiredAdmin

    public Result<String> deleteOne(HttpServletRequest request) {
        try {
            Integer vlid = Integer.parseInt(request.getParameter("vlid"));
            if (videoListService.deleteOneForAdmin(vlid)){
                return new Result<String>().setStatus(200).setMsg("删除课程成功");
            }
        } catch (Exception e) {
            return new Result<String>().setStatus(500).setMsg("删除课程失败");
        }
        return new Result<String>().setStatus(500).setMsg("删除课程失败");
    }

    @CrossOrigin
    @PostMapping(value = "/admin/video/add")
    @RequiredAdmin
    public Result<VideoListVO> addDoc(@RequestBody VideoList videoList, HttpServletRequest request) {
        VideoListVO videoListVO = new VideoListVO();
        try {
            Long uiid = (long) request.getAttribute("uiid");
            videoList = videoListService.saveAndRefresh(videoList.setUiid(uiid));
            BeanUtils.copyProperties(videoList, videoListVO);
            videoListVO.setUsername(videoList.getUserEntity().getUsername());
        } catch (Exception e) {
            return new Result<VideoListVO>().setStatus(500).setMsg("新建视频失败");
        }
        return new Result<VideoListVO>().setStatus(200).setMsg("新建视频成功").setData(videoListVO);
    }

    @CrossOrigin
    @PostMapping(value = "/admin/video/updateone")
    @RequiredAdmin

    public Result<VideoListVO> updateOne(@RequestBody VideoList videoList,HttpServletRequest request) {
        VideoListVO videoListVO;
        try {
//            VideoList temp = videoListService.findByVlid(videoList.getVlid());
//            videoList.setUserEntity(temp.getUserEntity());
//            videoList = videoListService.saveAndRefresh(videoList);
//            videoList.setUserEntity(temp.getUserEntity());
//            temp.setName(videoList.getName()).setCover(videoList.getCover())
//                    .setDuration(videoList.getDuration())
//                    .setPath(videoList.getPath());
//            temp.setName("dasdqd");
//            UpdateUtil.copyNullProperties(temp,videoList);
//            videoList.setUiid(temp.getUserEntity().getUiid());
//            BeanUtils.copyProperties(videoList,temp);
            videoList = videoListService.updateOne(videoList);
//            videoList = videoListService.saveAndFlush(temp);
//            videoList = videoListService.findByVlid(videoList.getVlid());
//            temp = videoListService.findByVlid(videoList.getVlid());
//            videoList = videoListService.findByVlid(videoList.getVlid());

            videoListVO = new VideoListVO();
            BeanUtils.copyProperties(videoList,videoListVO);
            videoListVO.setUsername(videoList.getUserEntity().getUsername());
        } catch (Exception e) {
            return new Result<VideoListVO>().setStatus(500).setMsg("更新课程失败");
        }
        return new Result<VideoListVO>().setStatus(200).setMsg("更新课程成功").setData(videoListVO);
    }

    @CrossOrigin
    @GetMapping(value = "/admin/video/getone")
    @RequiredAdmin

    public Result<VideoListVO> getOne(HttpServletRequest request) {
        VideoListVO videoListVO;
        try {
            Integer vlid = Integer.parseInt(request.getParameter("vlid"));
            videoListVO = videoListService.findOneForAdmin(vlid);
        } catch (Exception e) {
            return new Result<VideoListVO>().setStatus(500).setMsg("获取文档信息失败");
        }
        return new Result<VideoListVO>().setStatus(200).setMsg("获取文档信息成功").setData(videoListVO);
    }

    @CrossOrigin
    @GetMapping(value = "/admin/video/all")
    @RequiredAdmin

    public Result<List<VideoListVO>> getAll(HttpServletRequest request) {
        List<VideoListVO> docListVOList;
        try {
            docListVOList = videoListService.findAllForAdmin();
        } catch (Exception e) {
            return new Result<List<VideoListVO>>().setStatus(200).setMsg("获取文档列表失败");
        }
        return new Result<List<VideoListVO>>().setStatus(200).setMsg("获取文档列表成功").setData(docListVOList);
    }



}
