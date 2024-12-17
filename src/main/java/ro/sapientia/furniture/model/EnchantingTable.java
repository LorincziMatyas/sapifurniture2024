package ro.sapientia.furniture.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "enchanting_table")
public class EnchantingTable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_enchanting_table")
    @SequenceGenerator(name="pk_enchanting_table", sequenceName="pk_enchanting_table") 
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "magic_level")
    private int magicLevel;

    @Column(name = "material")
    private String material;

    @Column(name = "owner")
    private String owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMagicLevel() {
        return magicLevel;
    }

    public void setMagicLevel(int magicLevel) {
        this.magicLevel = magicLevel;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "EnchantingTable [id=" + id + ", magicLevel=" + magicLevel + ", material=" + material + ", owner=" + owner + "]";
    }
}
