import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 * 测试用例格式：
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1，第二个节点值
 * 为 2，以此类推。该图在测试用例中使用邻接列表表示。
 * 邻接列表是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆
 * 图的引用返回。
 * 示例 1：
 *图片：https://leetcode-cn.com/problems/clone-graph/
 * 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
 * 输出：[[2,4],[1,3],[2,4],[1,3]]
 * 解释：
 * 图中有 4 个节点。
 * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 * 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 * 示例 2：
 *图片：https://leetcode-cn.com/problems/clone-graph/
 * 输入：adjList = [[]]
 * 输出：[[]]
 * 解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
 * 示例 3：
 * 图片：https://leetcode-cn.com/problems/clone-graph/
 * 输入：adjList = []
 * 输出：[]
 * 解释：这个图是空的，它不含任何节点。
 * 示例 4：
 *图片：https://leetcode-cn.com/problems/clone-graph/
 * 输入：adjList = [[2],[1]]
 * 输出：[[2],[1]]
 * 提示：
 * 节点数介于 1 到 100 之间。
 * 每个节点值都是唯一的。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 * 图是连通图，你可以从给定节点访问到所有节点。
 * @author LemonLin
 * @Description :DFScloneGraph133LeetCode
 * @date 20.2.14-11:11
 * 思路：利用DFS或者BFS都可以。主要是图的DFS和BFS不是太熟。
 * 参考：https://leetcode-cn.com/problems/clone-graph/solution/
 * xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-9/
 * 注意几点：1、遍历生成的结点需要找个地方存，这是图的特点，为啥要存起来，因为图
 * 与树不同，树不可能连通的，图有可能是连通的，有可能形成环，所以需要判断某个结点
 * 是否访问过，不然图无法回退。怎么判断是否访问过，把访问过的结点放入hashMap，然
 * 后用containsKey来判断是否存在
 */
public class DFScloneGraph133LeetCode {
    /*
// Definition for a Node.
*/
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
    //DFS
    public Node cloneGraph(Node node) {
        if (node ==null){
            return node;
        }
        HashMap<Integer,Node> hashMap = new HashMap<>();
        return dfs(node,hashMap);
    }
    private Node dfs(Node node,HashMap<Integer,Node> hashMap){
    //递归递归一定要有归，先入为主的观念，应该写if(node == null) return null,为啥本
        // 题不写null ,因为本题是图，图是有环的，所以只要碰到了环就可以归，所以用
        //hashMap.containsKey(node.val)来代表归，同时因为如果是null，那么遍历操
        // 作 for (Node temp:node.neighbors){是不会遍历到的，所以无需考虑Node为null
        // 的情况，是不会发生的。除非最开始传入的是null，这个情况已经在cloneGraph函
        // 数进行了处理。
    //归的出口是这个原始是否遍历过，一旦遍历过就回退。这里是返回Node,有点不容易理解
        if (hashMap.containsKey(node.val)){
            return hashMap.get(node.val);
        }
        Node  n = new Node();
        n.val = node.val;
        n.neighbors = new ArrayList<>();
        hashMap.put(n.val,n);
        for (Node temp:node.neighbors){
            n.neighbors.add(dfs(temp,hashMap));
        }
        return n;
    }
}
