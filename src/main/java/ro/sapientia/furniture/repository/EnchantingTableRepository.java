package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.sapientia.furniture.model.EnchantingTable;

public interface EnchantingTableRepository extends JpaRepository<EnchantingTable, Long> {

    EnchantingTable findEnchantingTableById(Long id);
    
}
