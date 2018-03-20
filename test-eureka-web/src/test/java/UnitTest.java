import com.test.eureka.web.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/3/19  21:23
 * Version: V1.0
 * Description:单元测试
 * 使用spring 执行单元测试
 * ======================
 */
@RunWith(SpringJUnit4ClassRunner.class) //开启 运行单元测试的列子
@SpringBootTest(classes = UserController.class)  //用于测试的接口类
@WebAppConfiguration //使用该注解测试时 会真正启动一个web服务 由于测试的是Controller所以需要web服务器
public class UnitTest {

    public MockMvc mockMvc; //可用于模拟调用controlller的接口

    //初始化调用接口的测试类配置
    @Before
    public void begin() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }


    @Test
    public void test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/menage/user/id").accept(MediaType.APPLICATION_JSON_UTF8_VALUE).param("id","xxxxxxxxx")) //设置请求的url 以及接收的类型 以及设置参数和值 模拟请求
                .andExpect(status().isOk()) //如果请求成功
                .andReturn().getResponse().getContentAsString(); //则将结果打印出来
    }


}
