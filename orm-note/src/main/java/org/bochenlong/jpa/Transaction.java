package org.bochenlong.jpa;

import javax.persistence.MappedSuperclass;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
@MappedSuperclass
public class Transaction {
    private String version = "1.0.0";
    private URI dst;
    private URL cbUrl;
    private List<String> prevTXs;
    private String sigAlgo;
    private Map<String, byte[]> sig;
    private byte[] salt;
    private long timestamp = System.currentTimeMillis();
    private String encAlgo;
    private Map<String, byte[]> enc;
    private byte[] token;
    private String authz;
    Map<String, byte[]> meta;
    private byte[] body;

    public Transaction() {
    }

    public String getVersion() {
        return this.version;
    }

    public URI getDst() {
        return this.dst;
    }

    public void setDst(URI dst) {
        this.dst = dst;
    }

    public URL getCbUrl() {
        return this.cbUrl;
    }

    public void setCbUrl(URL cbUrl) {
        this.cbUrl = cbUrl;
    }

    public List<String> getPrevTXs() {
        return this.prevTXs;
    }

    public void setPrevTXs(List<String> prevTXs) {
        this.prevTXs = prevTXs;
    }

    public String getSigAlgo() {
        return this.sigAlgo;
    }

    public void setSigAlgo(String sigAlgo) {
        this.sigAlgo = sigAlgo;
    }

    public Map<String, byte[]> getSig() {
        return this.sig;
    }

    public void setSig(Map<String, byte[]> sig) {
        this.sig = sig;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getEncAlgo() {
        return this.encAlgo;
    }

    public void setEncAlgo(String encAlgo) {
        this.encAlgo = encAlgo;
    }

    public Map<String, byte[]> getEnc() {
        return this.enc;
    }

    public void setEnc(Map<String, byte[]> enc) {
        this.enc = enc;
    }

    public byte[] getToken() {
        return this.token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getAuthz() {
        return this.authz;
    }

    public void setAuthz(String authz) {
        this.authz = authz;
    }

    public Map<String, byte[]> getMeta() {
        return this.meta;
    }

    public void setMeta(Map<String, byte[]> meta) {
        this.meta = meta;
    }

    public byte[] getBody() {
        return this.body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
