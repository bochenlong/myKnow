package org.bochenlong.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bochenlong on 16-10-17.
 */
@Entity
@Table(name = "LeTransaction")
public class LeTransaction implements Serializable {

    @Id
    @Column(name = "txid", columnDefinition = "varchar(64)")
    private String txid;
    @Column(name = "chain", columnDefinition = "varchar(64)")
    private String chain;
    @Column(name = "block", columnDefinition = "varchar(64)")
    private String block;
    @Column(name = "body", columnDefinition = "text")
    private String body;

    @OneToMany(mappedBy = "leTransaction")
    private List<LeMeta> leMeta;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<LeMeta> getLeMeta() {
        return leMeta;
    }

    public void setLeMeta(List<LeMeta> leMeta) {
        this.leMeta = leMeta;
    }
}
