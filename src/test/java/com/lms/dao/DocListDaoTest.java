package com.lms.dao;

import com.lms.JavaLmsWebApplication;
import com.lms.entity.DocList;
import com.lms.service.DocListService;
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
    @Autowired
    DocListService docListService;

    @Test
    public void findAll() {

        PageRequest pageable = PageRequest.of(1, 1, Sort.Direction.DESC, "updatetime");
        List<DocList> docLists = docListService.findAllOrder();
    }
}