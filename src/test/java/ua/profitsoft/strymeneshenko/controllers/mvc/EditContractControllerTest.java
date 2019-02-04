package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.profitsoft.strymeneshenko.data.Contract;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:mvc-context.xml")
public class EditContractControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void before(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void goEdit() throws Exception{
        mockMvc.perform(get("/goEditContract").param("contractId", "12"))
                .andExpect(view().name("editContract"))
                .andExpect(model().attributeExists("editContract"))
                .andReturn();
    }

    @Test
    public void applyEdit() throws Exception{
        mockMvc.perform(post("/applyEditContract").sessionAttr("editContract", new Contract()))
                .andExpect(model().attributeExists("editContract"))
                .andExpect(view().name("redirect:/"))
                .andReturn();
    }

    @Test
    public void edit() throws Exception{
        mockMvc.perform(post("/editContract").param("dateConclusion", "1222-11-1111")
                .param("startDate", "1222-11-1111").param("endDate", "1222-11-1111").sessionAttr("editContract", new Contract()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("editContract"))
                .andExpect(view().name("editContract"))
                .andReturn();
    }
}