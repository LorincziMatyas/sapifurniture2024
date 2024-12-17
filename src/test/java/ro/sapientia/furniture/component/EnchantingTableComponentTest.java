package ro.sapientia.furniture.component;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ro.sapientia.furniture.model.EnchantingTable;
import ro.sapientia.furniture.repository.EnchantingTableRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class EnchantingTableComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EnchantingTableRepository repository;

    @Test
    public void integrationTestForComponent () throws Exception {
        EnchantingTable et = new EnchantingTable();
        et.setMagicLevel(20);
        et.setMaterial("wood");
        et.setOwner("John Doe");
        var savedET = repository.save(et);

        this.mockMvc.perform(get("/enchantingTable/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].magicLevel", is(20)));

        this.mockMvc.perform(get("/enchantingTable/find/"+savedET.getId())).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.owner", is("John Doe")));
    }

}
