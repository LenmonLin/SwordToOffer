/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置
 * （索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 图片见：https://leetcode-cn.com/classic/problems/linked-list-cycle-ii/description/
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 图片见：https://leetcode-cn.com/classic/problems/linked-list-cycle-ii/description/
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 * 图片见：https://leetcode-cn.com/classic/problems/linked-list-cycle-ii/description/
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 * @author LemonLin
 * @Description :DoublePointerdetectCycle142LeetCode
 * @date 20.2.2-17:42
 * 思路：和剑指offer上题目一样，但是要会证明就有点难度。
 * 参考：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/
 * linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
 * 思路：分成两步走，第一步：设置两个快慢指针，慢指针一次走一步，快指针一次走两步，
 * 如果相遇了，肯定在圈内。
 * 第二步：头指针和相遇指针一起开始走，头指针和相遇指针第二次相遇的话，就是所求的
 * 入口。
 * 证明：要设置六个参数，两个公式
 * 0、证明有环：fast 指针走过链表末端，说明链表无环； 若有环，两指针一定会相遇。因
 * 为每走 1 轮，fast 与 slow 的间距 +1，fast 终会追上 slow；
 * 1、设置节点数(第一、二个参数)：设链表共有 x+y个节点，其中 链表头部到链表入口
 * 有 x 个节点（不计链表入口节点，链表环有 y个节点
 * 2、设置指针走过的步数(第三、第四个参数)：两指针分别走了 f，s 步
 * 3、辅助参数(第五)：结合
 * 公式一：fast 比 slow多走了 n个环的长度，即f = s + ny，
 * 公式二：fast 走的步数是slow步数的 2 倍，即 f = 2s
 * 公式推出：f=2ny(这个没用),s=ny(这个有用);
 * 4、辅助参数(第六)：如果让指针从链表头部一直向前走并统计步数k，那么所有走到链
 * 表入口节点时的步数 是：k=a+nb，将slow和k对比，推出只要再走x步相遇位置就是
 * 入口。证明结束
 * bug1:
 * []
 * -1
 * java.lang.NullPointerException
 * bug2：
 * 最后执行的输入：
 * [1,2]
 * -1
 * java.lang.NullPointerException
 * bug3:
 * [1]
 * -1
 * java.lang.NullPointerException
 * bug4:
 * [-21,10,17,8,4,26,5,35,33,-7,-16,27,-12,6,29,-12,5,9,20,14,14,2,13,-24,21,23,-21,5]
 * -1
 * java.lang.NullPointerException
 */
public class DoublePointerdetectCycle142LeetCode {
    /**
     * Definition for singly-linked list.
     *      */
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode detectCycle(ListNode head) {
        //处理bug1
        if (head==null)return null;
        ListNode fast = head;
        ListNode slow = head;
        ListNode temp =head;
        //处理bug4,不能写成fast.next!=null,而且处理bug2和bug3的代码要放到while
        // 循环里面。
        while (fast!=null){
            //处理bug2
            if (fast.next==null)return null;
            //处理bug3
            if (fast.next.next==null)return null;
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast){
                while (slow!=temp){
                    slow=slow.next;
                    temp=temp.next;
                }
                return temp;
            }
        }
        return null;
    }
}
