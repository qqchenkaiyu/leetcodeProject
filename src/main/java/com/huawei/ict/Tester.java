package com.huawei.ict;

import com.huawei.ict.tree.CodecTree;
import com.huawei.ict.tree.TreeNode;

public class Tester {
    public static void main(String[] args) {
        CodecTree codecTree = new CodecTree();
        String str = "1,2,null,null,3,4,null,null,5,null,null";
        TreeNode deserialize = codecTree.deserialize(str);
        String serialize = codecTree.serialize(deserialize);
        System.out.println(serialize);
        System.out.println(serialize.equals(str));
    }
}
