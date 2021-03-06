/**
*  * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二
 * 叉树定义如下：
*  struct Node {
*       Node *left;
*       Node *right;
*       Node *next;
*  }
*  填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧
 *  节点，则将 next 指针设置为 NULL。
*  初始状态下，所有 next 指针都被设置为 NULL。
 *  示例图片见：
 *https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 *输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,
 * "right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,
 * "right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6",
 * "left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7",
 * "left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 * 输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4",
 * "left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,
 * "right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,
 * "val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},
 * "val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个
 * 右侧节点，如图 B 所示。
 * 提示：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * 思路：重点：常数空间。 所以：排除用队列BFS的思路。用了队列就是N的空间复杂度。
 *  其实就是将每一层的点都连接起来。每个结点的next指针都指向右边的结点，这样分
 *  两种情况：
 *  1、如果右边结点是亲兄弟结点，则在遍历到父节点的时候就root->left->next
 *  = root->right;
 *  2、如果右边结点是堂兄弟结点，则在遍历到父节点的时候（此时父节点
 *  的next指针已经指向了右边的兄弟节点），通过父节点的next指针与右边结点相接：
 *  root->right->next = root->next->left;在每一层的时候把下一层的next连好。
 *  等到了下一层，由于上一层的next已经连好，所以可以root.right.next = root.next.left。
 *  因为默认情况下next是指向null，所以只要找到右边有节点的节点的next进行指定即可，
 *  不用考虑右边没有节点的节点的next的指向，已经有默认值了，而且默认值满足题目要求
 *  指向了null
 *  问题：如果右边节点是亲兄弟节点这个怎么判断，怎么用代码表示，没想明白？这里用到
 *  了完美二叉树的特点，只要root.left!= null,那么右边就一定是亲兄弟节点，堂兄弟节点
 *  的判断是root.next!=null,因为完美二叉树的构造，亲兄弟节点root.next都已经进行了
 *  链接，只剩下堂兄弟节点的root.next没有链接。
 *  思路2：参考
 *  https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 *  solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--27/
 *  层次遍历每次通过队列保存下一层节点的顺序，比如访问第0层时，就把第0层的的左右
 *  孩子结点进队，这样第1层的左右结点顺序就保存下来了。观察本题，虽然不能让用队列
 *  但是有一个next指针，只要访问第0层的时候，就把第1层的结点的next指针链接上，这
 *  样第1层的左右结点的顺序也被保存下来了，当访问第1层的时候，根据next指针访问就
 *  可以了。如此反复，访问第一层的时候，把第二层结点的next指针也链接上。
 * @author LemonLin
 * @Description :Treeconnect116LeetCode
 * @date 2020/1/10-21:22
 */
public class Treeconnect116LeetCode {
    /*
// Definition for a Node.*/
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    //本解法利用了LeetCode117的解法，和117不同的是，不用判断第二层节点是否右节点
    //一定存在
    public Node connect(Node root) {
        Node cur = root;
        Node dummy=new Node();
        Node tail = null;
        //这一层的cur指的是第一层的节点
        while(cur!=null){
            dummy.next = null;
            tail = dummy;
            //这里的cur以及经过更新到第二层了。
            while(cur!=null){
                if (cur.left!=null){
                    tail.next = cur.left;
                    tail= tail.next;
                    tail.next = cur.right;
                    tail=tail.next;
                }
                //本层的下一个结点
                cur = cur.next;
            }
            //到下一层
            cur = dummy.next;
        }
        return root;
    }

    //层次遍历next指针方法，这里根据特点需要三个指针，
    // start，记录每一层的最左边的开头结点，当cur遍历到每一层末尾时，利用start找到
    // 下一层的开头
    // cur,遍历每一层结点，遍历的时候就把下一层节点的next指针链接起来
    // pre,始终在cur之前，为了解决堂兄弟指针的next链接问题。
    public Node connect2(Node root) {
        if(root==null)return root;
        Node start = root;
        Node cur = null;
        Node pre = start;
        //start.left 表示下一层的开头结点
        while (start.left!=null){
            //下一层的头一个结点的next指针先链接上，方便pre和cur指定
            start.left.next = start.right;
            //说明这一层右侧还有结点
            while (cur!=null){
                pre.right.next = cur.left;
                cur.left.next = cur.right;
                pre = pre.next;
                cur = cur.next;
            }
            //到下一层
            pre=start.left;
            cur = start.right;
            start = pre;
        }
        return root;
    }

    //递归方法
    public Node connect1(Node root) {
        if(root == null) return null;
        //这里需要结合完美二叉树的图形看，比较容易理解
        if(root.left!= null){
            root.left.next = root.right;
            if (root.next!=null) root.right.next = root.next.left;
        }
        connect1(root.left);
        connect1(root.right);
        return root;
    }
}
