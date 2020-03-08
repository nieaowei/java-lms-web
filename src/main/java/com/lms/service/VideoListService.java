package com.lms.service;

import com.lms.dao.VideoListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoListService {
    @Autowired
    VideoListDao videoListDao;
}
