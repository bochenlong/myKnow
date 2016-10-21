package org.bochenlong.codec.test1;

import com.google.protobuf.InvalidProtocolBufferException;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bochenlong on 16-9-20.
 */
public class CodecTest {
    private static ConcurrentHashMap<Class, Schema> cschema
            = new ConcurrentHashMap();

    public static void main(String[] args) throws InvalidProtocolBufferException {
        Schema<HashMap> schema = RuntimeSchema.createFrom(HashMap.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "2");
        byte[] bytes2 = ProtostuffIOUtil.toByteArray(map, schema, LinkedBuffer
                .allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        System.out.println(bytes2.length);
        HashMap<String, String> map2 = new HashMap<>();

        ProtostuffIOUtil.mergeFrom(bytes2, map2, schema);
        System.out.println(map2.size());

        ArrayList arrayList = new ArrayList();
        arrayList.add("18518711111");
        Demo d = new Demo(1, "chenxiaochun", "zhongguancun", arrayList, map);
        Schema<Demo> schema1 = RuntimeSchema.createFrom(Demo.class);
        byte[] bytes3 = ProtostuffIOUtil.toByteArray(d, schema1, LinkedBuffer.allocate
                (LinkedBuffer.DEFAULT_BUFFER_SIZE));
        Demo d1 = new Demo();
        ProtostuffIOUtil.mergeFrom(bytes3, d1, schema1);
        System.out.println(d1.getTags());
//        ArrayList arrayList = new ArrayList();
//        arrayList.add("18518711111");
//        Map<String, String> map = new HashMap<>();
//        map.put("1", "2");
//
//        long t = TimeUt.currT();
//
//
//        for (int i = 0; i < 10000000; i++) {
//            byte[] bytes = DemoProtoBean.Demo.newBuilder().setId(1).setName("chenxiaochun").setAddress("zhongguancun")
//                    .addAllPhones(arrayList)
//                    .putAllTags(map)
//                    .build().toByteArray();
//            DemoProtoBean.Demo demo = DemoProtoBean.Demo.parseFrom(bytes);
//        }
//        TimeUt.useTP(t);
//
//        long t1 = TimeUt.currT();
//        Demo d = new Demo(1, "chenxiaochun", "zhongguancun", arrayList, map);
//        Schema<Demo> schema = RuntimeSchema.createFrom(Demo.class);
//        for (int i = 0; i < 10000000; i++) {
//            Schema<Demo> s = cschema.get(Demo.class);
//            if (s == null) {
//                cschema.put(Demo.class, RuntimeSchema.createFrom(Demo.class));
//            }
//            byte[] bytes2 = ProtostuffIOUtil.toByteArray(d, cschema.get(Demo.class), LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
//            Demo d1 = new Demo();
//            ProtostuffIOUtil.mergeFrom(bytes2, d1, cschema.get(Demo.class));
//        }
//        TimeUt.useTP(t1);
    }

    // byte result 10000000 : 8189-3095
    // parse result 10000000 : 5371-4980
    // all result 10000000 : 13312-8001
}
