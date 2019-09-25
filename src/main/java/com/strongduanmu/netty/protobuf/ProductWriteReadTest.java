package com.strongduanmu.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

/**
 * Desc: Product写入读取测试
 * Date: 2019/9/20
 *
 * @author duanzhengqiang
 */
public class ProductWriteReadTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        //对象写入到字节数组中
        Product.ProductBasicInfo productBasicInfo = Product.ProductBasicInfo
                .newBuilder()
                .setName("Java编程思想")
                .setValidate(999)
                .setUnit("本").build();
        byte[] bytes = productBasicInfo.toByteArray();
        //[10, 16, 74, 97, 118, 97, -25, -68, -106, -25, -88, -117, -26, -128, -99, -26, -125, -77, 16, -25, 7, 26, 3, -26, -100, -84]
        System.out.println(Arrays.toString(bytes));
        //从字节数组中读取到对象中
        Product.ProductBasicInfo parseProductBasicInfo = Product.ProductBasicInfo.parseFrom(bytes);
        /*
         * name: "Java\347\274\226\347\250\213\346\200\235\346\203\263"
         * validate: 999
         * unit: "\346\234\254"
         */
        System.out.println(parseProductBasicInfo);
        //Java编程思想
        System.out.println(parseProductBasicInfo.getName());
        //999
        System.out.println(parseProductBasicInfo.getValidate());
        //本
        System.out.println(parseProductBasicInfo.getUnit());
    }
}
