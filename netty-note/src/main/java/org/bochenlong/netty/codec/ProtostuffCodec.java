package org.bochenlong.netty.codec;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.omg.IOP.Codec;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bcl on 2016/8/30.
 */
public class ProtostuffCodec<T> {
    private static ConcurrentHashMap<Class<?>, Schema<?>> cschema
            = new ConcurrentHashMap();

    public byte[] toBytes(T t) {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        Class<T> c = (Class<T>) t.getClass();
        Schema<T> schema = RuntimeSchema.createFrom(c);
        cschema.put(c, schema);
        return ProtostuffIOUtil.toByteArray(t, schema, buffer);
    }

    public T toObject(byte[] bytes, Class<T> c) {
        try {
            T obj = c.newInstance();
            ProtostuffIOUtil.mergeFrom(bytes, obj, (Schema<T>) cschema.getOrDefault(c, RuntimeSchema.createFrom(c)));
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
