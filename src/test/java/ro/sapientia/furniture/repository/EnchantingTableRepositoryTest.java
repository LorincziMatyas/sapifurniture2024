package ro.sapientia.furniture.repository;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import ro.sapientia.furniture.model.EnchantingTable;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class EnchantingTableRepositoryTest {

    @Autowired
    EnchantingTableRepository repository;

    @Test
    public void testForEmptyDB() {
        var result = repository.findAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testForFindFirst() {
        EnchantingTable et = new EnchantingTable();
        et.setMagicLevel(20);
        et.setMaterial("wood");
        et.setOwner("John Doe");
        var savedET = repository.save(et);
        var result = repository.findAll();
        assertEquals(1, result.size());
        
        var foundObj = repository.findEnchantingTableById(savedET.getId());
        
        assertEquals(savedET, foundObj);
    }
}
