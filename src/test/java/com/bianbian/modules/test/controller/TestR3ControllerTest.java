package com.bianbian.modules.test.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bianbian.modules.test.entity.TestR3;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * @author God
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class TestR3ControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private MockHttpSession session;

    @Before
    public void setupMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
    }


    @Test
    public void create() throws Exception {

        TestR3 user = new TestR3();
        user.setUsername("张三2");
        user.setAge(30);
        user.setMoney(10000L);
        user.setMarrage(true);
        user.setTimeNow(new Date());

        JSONObject json = JSONUtil.parseObj(user);

        String postStr = json.toString();
        mvc.perform(MockMvcRequestBuilders.post("/testR3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(postStr)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void page() throws Exception {

        String pageinfo = "page=1&pageSize=10&sort=id&order=desc";
        String pageTimeinfo = "&startDate=1519699822000&endDate=1551235822000";
        String testR3info = "&departmentId=departmentId&userId=";
        mvc.perform(MockMvcRequestBuilders.get("/testR3/getByPage?" + pageinfo + pageTimeinfo + testR3info)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void get() throws Exception {
        String id = "";
        mvc.perform(MockMvcRequestBuilders.get("/testR3/" + id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void update() throws Exception {

        String id = "";
        String str = "{\n\t\"name\":\"this is name updated\",\n\t\"remark\":\"this is remark updated\",\n\t\"bu_id\":\"this is buid updated\",\n\t\"status\":true,\n\t\"content\":\"this is content updated\"\n}";
        mvc.perform(MockMvcRequestBuilders.put("/testR3/" + id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(str)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
        String id = "";
        mvc.perform(MockMvcRequestBuilders.delete("/testR3/" + id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
