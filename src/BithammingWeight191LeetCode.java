/**
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数
 * （也被称为汉明重量）。
 * 示例 1：
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出
 * 都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是
 * 无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例
 * 3 中，输入表示有符号整数 -3。
 * @author LemonLin
 * @Description :BithammingWeight191LeetCode
 * @date 20.7.2-15:44
 *1、注意输入的参数是无符号的int 型，所以因为int是32位的，所以不用考虑超过32位的
 * 情况，题目已经限定了32位了
 * 2、比较直白的做法：依次判断最低位是否是 1，然后把它加入到结果中。判断最低位
 * 是否是 1，我们只需要把原数字和 000000..001 相与，也就是和 1 相与即可。
 * 3、注意区分>>与>>>
 * >>表示带符号右移，如：int i=15; i>>2的结果是3，移出的部分将被抛弃。
 * 转为二进制的形式可能更好理解，0000 1111(15)右移2位的结果是0000 0011(3)，
 * 0001 1010(18)右移3位的结果是0000 0011(3)。
 * >>>无符号右移：
 * 按二进制形式把所有的数字向右移动对应巍峨位数，低位移出（舍弃），高位的空位补零。
 * 对于正数来说和带符号右移相同，对于负数来说不同。其他结构和>>相似。
 */
public class BithammingWeight191LeetCode {
    public int hammingWeight(int n) {
        int count =0;
        while (n!=0){
            count+=(n&1);
            n=n>>>1;
        }
        return count;
    }
}
