package com.lms.controller;


import com.alibaba.fastjson.JSONObject;
import com.lms.entity.LearnRecord;
import com.lms.entity.VideoLearnRecord;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.service.UserService;
import com.lms.service.VideoLearnRecordService;
import com.lms.service.VideoListService;
import com.lms.utils.Result;
import com.lms.vo.DocListVO;
import com.lms.vo.LearnVO;
import com.lms.vo.VideoLearnVO;
import com.lms.vo.VideoListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class VideoLearnRecordController {

    @Autowired
    private VideoLearnRecordService videoLearnRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoListService videoListService;

    /**
     * 查询文档学习的记录列表
     *
     * @param request 用于解析拦截器传来的已验证的token中的用户账号
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/user/video/record")
    @RequiredToken
    public Result<List<VideoLearnVO>> getLearnRecord(HttpServletRequest request) {
        Long uiid = (long) request.getAttribute(AuthInterceptor.RESULT_KEY);
        List<VideoLearnVO> learnVOMap = videoLearnRecordService.findLearnRecordByUiid(uiid);//无论是否查询到都会生成对象，无需判错
        return new Result<List<VideoLearnVO>>().setStatus(200).setMsg("获取学习记录成功").setData(learnVOMap);
    }

    /**
     * 增加学习文档到个人学习记录
     *
     * @param request 用于解析拦截器传来的已验证的token中的用户账号
     */
    @CrossOrigin
    @PostMapping(value = "/user/video/addRecord")
    @RequiredToken
    public Result<VideoLearnVO> addRecord(@RequestBody JSONObject jsonParam,
                                               HttpServletRequest request) {
        VideoLearnVO videoLearnVO;
        try {
            Integer vlid = jsonParam.getInteger("vlid");
            VideoLearnRecord videoLearnRecord = new VideoLearnRecord().setVlid(vlid)
                    .setUiid((long) request.getAttribute(AuthInterceptor.RESULT_KEY)).setDuration(0);
            videoLearnRecord = videoLearnRecordService.saveAndRefresh(videoLearnRecord);
            videoLearnVO = new VideoLearnVO(videoLearnRecord);
        } catch (Exception e) {
            if (e.getMessage().contains("vlid")) {//捕获到重复键值
                return new Result<VideoLearnVO>().setStatus(500).setMsg("已经添加该视频");
            }
            return new Result<VideoLearnVO>().setStatus(500).setMsg("添加视频失败");
        }
        return new Result<VideoLearnVO>().setStatus(200).setMsg("添加视频成功").setData(videoLearnVO);
    }

    /**
     * 更新用户文档的学习时间
     * @param request 用于解析拦截器传来的已验证的token中的用户账号，以及相关参数
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/user/video/update")
    @RequiredToken
    public Result<VideoLearnVO> updateDuration(@RequestBody JSONObject jsonParam,
                                               HttpServletRequest request) {
        VideoLearnRecord learnRecord;
        try {
            Integer vlid = jsonParam.getInteger("vlid");
            Long uiid = (long) request.getAttribute(AuthInterceptor.RESULT_KEY);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            learnRecord = videoLearnRecordService.findByVlidAndUiid(vlid, uiid);
            if (learnRecord == null){
                return new Result<VideoLearnVO>().setStatus(500).setMsg("您尚未添加该视频课程");
            }
            /**
             * 简单的防作弊，
             */
            Date nowTime = new Date();
            Date oldTime = df.parse(learnRecord.getUpdatetime());
            long dur = (nowTime.getTime() - oldTime.getTime());
            if (dur < 9555){
                return new Result<VideoLearnVO>().setStatus(500).setMsg("你可能在作弊");
            }
            /**
             * 为什么要通过uiid和VLid去查询更新呢？
             * 为了防止uiid操作了不属于自己的lrid。
             */
            // 如果时间超过文档时间，设置为文档时间
            if(learnRecord.getDuration()+10>learnRecord.getVideoList().getDuration()){
                learnRecord.setDuration(learnRecord.getVideoList().getDuration());
                learnRecord=videoLearnRecordService.save(learnRecord);
            }else{// 否则 +10s
                learnRecord = videoLearnRecordService.save(learnRecord.setDuration(learnRecord.getDuration()+10));//设置新的时间并更新
            }

            //返回一条业务数据  （flag and percent）
            double percent = (double)learnRecord.getDuration()/learnRecord.getVideoList().getDuration()*100;
            DecimalFormat df1 = new DecimalFormat("#.0");
            percent = Double.parseDouble( df1.format(percent));
            VideoLearnVO learnVO = new VideoLearnVO().setPercent(percent);
            return new Result<VideoLearnVO>().setStatus(200).setMsg("更新时间成功").setData(learnVO);

        } catch (Exception e) {
            return new Result<VideoLearnVO>().setStatus(500).setMsg("更新时间失败");
        }

    }

}
