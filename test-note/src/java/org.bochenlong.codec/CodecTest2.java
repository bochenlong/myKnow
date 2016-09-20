package org.bochenlong.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.bochenlong.time8.TimeUt;


/**
 * Created by bochenlong on 16-9-20.
 */
public class CodecTest2 {
    public static void main(String[] args) throws InvalidProtocolBufferException {

        long t = TimeUt.currT();

        byte[] bytes = DemoProtoBean2.Demo2.newBuilder()
                .setId(1).setName("bochenlong").setAddress("zhongguancun")
                .setMail("bochenlong@163.com").build().toByteArray();
        for (int i = 0; i < 10000000; i++) {
            DemoProtoBean2.Demo2 demo = DemoProtoBean2.Demo2.parseFrom(bytes);
        }
        TimeUt.useTP(t);

        long t1 = TimeUt.currT();
        Demo2 d = new Demo2(1, "bochenlong", "bochenlong@163.com", "zhongguancun");
        Schema<Demo2> schema = RuntimeSchema.createFrom(Demo2.class);
        byte[] bytes2 = ProtostuffIOUtil.toByteArray(d, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        Demo2 d1 = new Demo2();
        for (int i = 0; i < 10000000; i++) {
            ProtostuffIOUtil.mergeFrom(bytes2, d1, schema);
        }
        TimeUt.useTP(t1);
    }

    // byte result 10000000 : 4429-2149
    // parse result 10000000 : 3050-2137
    // all result 10000000 : 7132-4164
}
