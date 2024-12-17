package ro.sapientia.furniture.controller;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ro.sapientia.furniture.model.EnchantingTable;
import ro.sapientia.furniture.service.EnchantingTableService;

@WebMvcTest(controllers = EnchantingTableController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class EnchantingTableControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean(EnchantingTableService.class)
    private EnchantingTableService enchantingTableService;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        final EnchantingTable table = new EnchantingTable();
        table.setMagicLevel(10);
        when(enchantingTableService.findAllEnchantingTables()).thenReturn(List.of(table));

        this.mockMvc.perform(get("/enchantingTable/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].magicLevel", is(10)));
    }
}
