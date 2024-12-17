package ro.sapientia.furniture.service;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ro.sapientia.furniture.model.EnchantingTable;
import ro.sapientia.furniture.repository.EnchantingTableRepository;

public class EnchantingTableServiceTest {
    
    private EnchantingTableRepository repositoryMock;

    private EnchantingTableService service;

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(EnchantingTableRepository.class);
        service = new EnchantingTableService(repositoryMock);
    }

    @Test
    public void testFindAllEnchantingTables_emptyList() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<EnchantingTable> enchantingTables =  service.findAllEnchantingTables();
        
        assertEquals(0, enchantingTables.size());
    }

    @Test
    public void testFindAllEnchantingTables_null() {
        when(repositoryMock.findAll()).thenReturn(null);
        final List<EnchantingTable> enchantingTables =  service.findAllEnchantingTables();
        
        assertNull(enchantingTables);
    }

    @Test
    public void testFindAllEnchantingTables_notEmptyList() {
        when(repositoryMock.findAll()).thenReturn(Collections.singletonList(new EnchantingTable()));
        final List<EnchantingTable> enchantingTables =  service.findAllEnchantingTables();
        
        assertEquals(1, enchantingTables.size());
    }
}
