package org.bochenlong.bean;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bochenlong on 16-10-17.
 */
@Entity
@Table(name = "LeMeta")
public class LeMeta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "txid")
    private LeTransaction leTransaction;

    private String keyq;
    private byte[] valueq;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LeTransaction getLeTransaction() {
        return leTransaction;
    }

    public void setLeTransaction(LeTransaction leTransaction) {
        this.leTransaction = leTransaction;
    }

    public String getKeyq() {
        return keyq;
    }

    public void setKeyq(String keyq) {
        this.keyq = keyq;
    }

    public byte[] getValueq() {
        return valueq;
    }

    public void setValueq(byte[] valueq) {
        this.valueq = valueq;
    }
}
