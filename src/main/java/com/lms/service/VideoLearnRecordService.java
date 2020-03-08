package com.lms.service;

import com.lms.dao.VideoLearnRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoLearnRecordService {
    @Autowired
    VideoLearnRecordDao videoLearnRecordDao;
}
