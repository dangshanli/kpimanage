package com.luzj.kpimanage;

import com.luzj.kpimanage.addkpis.TestHello;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//引入，是status() content() equalsTo()等函数可用
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * @author luzj
 * @description: 测试TestHello类,编写一个测试范例，便于复制模仿
 * @date 2018/3/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class Chapter1ApplicationTests {
private MockMvc mvc;

@Before
public void setUp() throws Exception{
    mvc = MockMvcBuilders.standaloneSetup(new TestHello()).build();
}

@Test
public void getHello() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("hello")));
}

}
