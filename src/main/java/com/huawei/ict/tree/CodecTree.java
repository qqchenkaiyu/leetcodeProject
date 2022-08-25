package com.huawei.ict.tree;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class CodecTree {
    LinkedList<String> res = new LinkedList<>();
    String nullStr = "null";

    public String serialize(TreeNode root) {
        serializeTraverse(root);
        StringJoiner joiner = new StringJoiner(",");
        res.forEach(joiner::add);
        return joiner.toString();
    }

    // 将以当前节点为根的树进行输出 -- 若当前节点不为空 然后继续遍历左右子树
    public void serializeTraverse(TreeNode root) {
        if (root == null) {
            res.add(nullStr);
            return;
        }
        res.add(String.valueOf(root.val));
        serializeTraverse(root.left);
        serializeTraverse(root.right);
    }

    LinkedList<TreeNode> characterQueue;

    public TreeNode deserialize(String data) {
        characterQueue = Arrays.stream(data.split(",")).map(s -> buildTreeNode(s))
                .collect(Collectors.toCollection(LinkedList::new));
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        TreeNode root = characterQueue.poll();
        treeNodeQueue.offer(root);
        deserializeTraverse(root);
        return root;
    }

    // 构建以root为跟的左右子树 若root不为空 则分别构建左右子树
    public void deserializeTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        if (!characterQueue.isEmpty()) {
            root.left = characterQueue.poll();
            deserializeTraverse(root.left);
            root.right = characterQueue.poll();
            deserializeTraverse(root.right);
        }
    }

    private TreeNode buildTreeNode(String poll) {
        return poll.equals(nullStr) ? null : new TreeNode(Integer.valueOf(poll));
    }
}
