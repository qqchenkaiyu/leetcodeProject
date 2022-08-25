package com.huawei.ict.tree;


import java.util.LinkedList;
import java.util.Queue;

public class CodecStack {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
         StringBuilder stringBuilder = new StringBuilder();
        Queue<TreeNode> treeNodeStack = new LinkedList<>();
        treeNodeStack.offer(root);
        TreeNode tmp = null;
        while (!treeNodeStack.isEmpty()){
            tmp = treeNodeStack.poll();
            if(tmp==null){
                stringBuilder.append("null").append(",");
                continue;
            }else {
                stringBuilder.append(tmp.val).append(",");
                treeNodeStack.offer(tmp.left);
                treeNodeStack.offer(tmp.right);
            }
        }
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        Queue<String> characterQueue = new LinkedList<>();
//        for (char c : str.toCharArray()) {
//            characterQueue.offer(c);
//        }
        for (String s : split) {
            characterQueue.offer(s);
        }
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        TreeNode root = buildTreeNode(characterQueue.poll());
        treeNodeQueue.offer(root);
        TreeNode tmp = null;
        while (!characterQueue.isEmpty()){
            tmp = treeNodeQueue.poll();
            if(tmp!=null){
                tmp.left=buildTreeNode(characterQueue.poll());
                tmp.right=buildTreeNode(characterQueue.poll());
                treeNodeQueue.offer(tmp.left);
                treeNodeQueue.offer(tmp.right);
            }
        }
        return root;
    }
     private static TreeNode buildTreeNode(String poll) {
        return (poll==null||poll.equals("null"))?null:new TreeNode(Integer.valueOf(poll+""));
    }
}
