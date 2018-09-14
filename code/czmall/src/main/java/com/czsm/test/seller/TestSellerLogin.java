package com.czsm.test.seller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.czsm.SpringBootApllication;

/**
 * Created by Mac 刘平 20180912
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApllication.class)
@AutoConfigureMockMvc
public class TestSellerLogin {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setupMockMvc() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testBuyerUsernameExise() throws Exception {

		MvcResult mvcResult = mockMvc.perform(// 1请求
				MockMvcRequestBuilders.get("/buyerUsernameExise") // 2 路径
						.param("username", "admin") // 3 参数
		).andReturn();// 4 返回
		// 调用接口，传入添加的用户参数

		int status = mvcResult.getResponse().getStatus(); // 5返回状态码
		System.out.println(status + "===============***=================");
		String responseString = mvcResult.getResponse().getContentAsString();
		System.out.println(responseString);// 返回结果

	}
}
