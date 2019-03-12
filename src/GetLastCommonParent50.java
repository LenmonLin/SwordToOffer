import common.MakeTree;
import common.TreeNodeCommon;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LemonLin
 * @Description :GetLastCommonParent
 * @date 2018/9/20-10:55
 *题目：树中两个结点的最低公共祖先
 *
 * 解法一：假设是二叉搜索树（二叉搜索树是一个排序的二叉树，左子树的结点小于根结点，右子树的结点大于
 * 根节点），故找到一个节点，使其大于左子节点小于右子结点即可。
 *
 * 程序思路有三部分:
 *      一、考虑异常输入
 *      二、考虑正确的输入结果
 *      三、考虑向左递归还是向右递归
 *
 * 解法二：假设是普通的树，但是每个子结点都有指向父结点的指针，这样的话类似与
 * 前面的链表找公共结点一样。
 * （ 总体思路：1、首先要知道链表是单向链表。如果两个单向链表有公共的结点，那么两个链表从某一个结点开始，
 *   它们的next都指向同一个结点。
 *   2、先求出两个链表的长度，进而求出两个链表的长度差，然后先遍历长的链表长度差，然后同时出发，当
 *   遍历了同一个结点的时候，就是公共结点）
 *
 *   解法三：假设是普通的树
 *   思路：分两步，
 *      一、分别记录根结点到两个目标结点的路径;
 *              利用的是图的深度优先遍历的简化版来记录根结点到目标节点的路径
 *
 *      二、然后把题目转换为求两个链表的最后的公共结点;
 *              默认是根结点到叶子节点的情况，两个链表一样长
 *
 */
public class GetLastCommonParent50 {


    /*
     需要用到递归的想法
     */
    public TreeNodeCommon GetLastCommonParentOne(TreeNodeCommon tRoot,
                                                 TreeNodeCommon tLeft,TreeNodeCommon tRight){

        TreeNodeCommon treeNodeCommon = null;

        if (tRoot == null || tRight.data<tLeft.data){
            return  null;
        }

        if (tRoot.data >tRight.data){
            return treeNodeCommon = GetLastCommonParentOne(tRoot.left,tLeft,tRight);
        }

        if (tRoot.data <tLeft.data){
            return treeNodeCommon = GetLastCommonParentOne(tRoot.right,tLeft,tRight);
        }

        if (tRoot.data > tLeft.data && tRoot.data < tRight.data){
            return  tRoot;
        }

        return  treeNodeCommon;
    }


    //获取路径的方法
   public  void  getNodePath(TreeNodeCommon tRoot, TreeNodeCommon targetNode,
                             ArrayList tempList ,ArrayList path){
        if (tRoot == targetNode || tRoot == null){
            return ;
        }
        tempList.add(tRoot);

       ArrayList<TreeNodeCommon> childs = new ArrayList<>();
        if (tRoot.left != null){
           childs.add(tRoot.left);
        }

       if (tRoot.right!= null){
           childs.add(tRoot.right);
       }

        for (TreeNodeCommon node :childs ){

            //注意这里用的是比较data,不能直接比较结点，否则会包括比较结点下面的子结点，导致无法进入if
            if (node.data == targetNode.data){
                path.addAll(tempList);
                break;
            }
            getNodePath(node,targetNode,tempList,path);
        }

        //不包括目标节点，把目标节点排除
        tempList.remove(tempList.size()-1);
   }

   //获取两个路径的最后一个公共结点，重点是最后两个字
   public  TreeNodeCommon getLastCommonParent(ArrayList<TreeNodeCommon> path1,
                                        ArrayList<TreeNodeCommon> path2){


       TreeNodeCommon treeNode  = null;
        for (int i=0;i<path1.size();i++){
            if (path1.get(i)!=path2.get(i)){
                break;
            }

            treeNode = path1.get(i);
        }
        return treeNode;
   }


   public  TreeNodeCommon GetLastCommonParentTwo(TreeNodeCommon tRoot ,
                                                 TreeNodeCommon node1 ,TreeNodeCommon node2){
        ArrayList path1 = new ArrayList();
        ArrayList path2 = new ArrayList();

        ArrayList temp = new ArrayList();


        getNodePath(tRoot,node1,temp,path1);
        getNodePath(tRoot,node2,temp,path2);

        //如果路径不存在，返回空
       if (path1.size()==0 || path2.size() ==0){
           return null;
       }

       return getLastCommonParent(path1,path2);
   }

    public static void main(String[] args) {
        MakeTree makeTree = new MakeTree();

        TreeNodeCommon pRoot = makeTree.makeTree();
        TreeNodeCommon tLeft = new TreeNodeCommon(5);
        TreeNodeCommon tRight = new TreeNodeCommon(7);

        GetLastCommonParent50 getLastCommonParent50 = new GetLastCommonParent50();
        //TreeNodeCommon lastCommonParent = getLastCommonParent50.GetLastCommonParentOne(pRoot,tLeft,tRight);
        //System.out.println(lastCommonParent.data);


        TreeNodeCommon lastCommonParent = getLastCommonParent50.GetLastCommonParentTwo(pRoot
        ,tLeft,tRight);
        System.out.println(lastCommonParent.data);

    }
}