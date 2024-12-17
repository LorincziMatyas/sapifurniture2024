package ro.sapientia.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.model.EnchantingTable;
import ro.sapientia.furniture.repository.EnchantingTableRepository;

@Service
public class EnchantingTableService {
    // Fields
    private final EnchantingTableRepository enchantingTableRepository;

    // Constructor
    public EnchantingTableService(final EnchantingTableRepository enchantingTableRepository) {
        // dependency injection
        this.enchantingTableRepository = enchantingTableRepository;
    }
    
    // Find all enchanting tables
    public List<EnchantingTable> findAllEnchantingTables() {
        return this.enchantingTableRepository.findAll();
    }

    // Find enchanting table by id
    public EnchantingTable findEnchantingTableById(final Long id) {
        return this.enchantingTableRepository.findEnchantingTableById(id);
    }

    // Create enchanting table
    public EnchantingTable create(EnchantingTable enchantingTable) {
        return this.enchantingTableRepository.saveAndFlush(enchantingTable);
    }

    // Update enchanting table
    public EnchantingTable update(EnchantingTable enchantingTable) {
        return this.enchantingTableRepository.saveAndFlush(enchantingTable);
    }

    // Delete enchanting table
    public void delete(Long id) {
        this.enchantingTableRepository.deleteById(id);
    }
}
