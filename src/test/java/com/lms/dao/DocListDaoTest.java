package com.lms.dao;

import com.lms.JavaLmsWebApplication;
import com.lms.entity.DocList;
import com.lms.service.DocListService;
import com.lms.vo.DocListDTO;
import com.lms.vo.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JavaLmsWebApplication.class})
@WebAppConfiguration
public class DocListDaoTest {
//    @Autowired
//    DocListService docListService;

    @Autowired
    DocListDao docListDao;

    @Autowired
    UserDAO userDAO;

    @Test
    public void findAll() {
//        UserDTO userDTO = userDAO.findByPhonenum("13223232323");
//        PageRequest pageable = PageRequest.of(1, 1, Sort.Direction.DESC, "updatetime");
//        List<DocList> docLists = docListService.findAllOrder();
//        String username = userDTO.getUsername();
//        Long uiid = userDTO.getUiid();
//        List<DocListDTO> docListDTOS = docListDao.findAllByOrderByCreatetimeAsc();
//        DocList res = docListDao.deleteDocListByDlid(128);
    }
}