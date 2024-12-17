package ro.sapientia.furniture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.model.EnchantingTable;
import ro.sapientia.furniture.service.EnchantingTableService;

@RestController
@RequestMapping("/enchantingTable")
public class EnchantingTableController {

    private final EnchantingTableService enchantingTableService;

    public EnchantingTableController(final EnchantingTableService enchantingTableService) {
        this.enchantingTableService = enchantingTableService;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<EnchantingTable>> getAllEnchantingTables(){
        final List<EnchantingTable> enchantingTables = enchantingTableService.findAllEnchantingTables();
        return new ResponseEntity<>(enchantingTables,HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EnchantingTable> getEnchantingTableById(@PathVariable("id") Long id){
        final EnchantingTable enchantingTable = enchantingTableService.findEnchantingTableById(id);
        return new ResponseEntity<>(enchantingTable,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EnchantingTable> addEnchantingTable(@RequestBody EnchantingTable enchantingTable){
        final EnchantingTable persistenEnchantingTable = enchantingTableService.create(enchantingTable);
        return new ResponseEntity<>(persistenEnchantingTable,HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<EnchantingTable> updateEnchantingTable(@RequestBody EnchantingTable enchantingTable){
        final EnchantingTable persistenEnchantingTable = enchantingTableService.update(enchantingTable);
        return new ResponseEntity<>(persistenEnchantingTable,HttpStatus.OK);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> deleteEnchantingTableById(@PathVariable("id") Long id){
        enchantingTableService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

// ''/] klpoiuj89juikmnbvcxz`sdfggh' written by wifey