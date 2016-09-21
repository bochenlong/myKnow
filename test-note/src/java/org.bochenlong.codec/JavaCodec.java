package org.bochenlong.codec;

import java.io.*;

/**
 * Created by bcl on 2016/8/30.
 */
public class JavaCodec<T> {
    public byte[] toBytes(T t) {
        try {
            try (
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos)
            ) {
                oos.writeObject(t);
                oos.flush();
                return bos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T toObject(byte[] bytes) {
        try (
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bis)
        ) {
            return (T) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
