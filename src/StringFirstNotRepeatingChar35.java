/**
 * 题目描述
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 * @author LemonLin
 * @Description :FirstNotRepeatingChar35
 * @date 2018/4/16-15:27
 * 第一点，考虑用hash表的时候，字符不要惯性的认为只有26个英文字符，还有大写，-、+等符号字符，
 * 所以根据字符的位数，应该是256位字符。
 * 第二点，第一遍扫描存次数，第二遍扫描还是根据输入的顺序进行扫描，扫描时然后参考hash值的
 * 次数。
 * 第三点，字符是ASCII编码，所以转换为数字下标，其实可以直接用即可。
 * 第四点，其实不用使用hash表，因为key值是数字，所以使用数组即可;
 *  解题思路：
 *       利用hash表，先扫描一遍输入字符串，key值是出现的字符，value值记录出现的字符的次数，
 *       第二遍顺序扫描直接读出value值是1的字符，因为第二遍也是按照顺序扫描的，所以不存在
 *       String s=aacb
 *  a b c ,不会先输出b,因为是按照s的顺序遍历的，不是按照数组的key遍历的
 *  2 1 1
 */
public class StringFirstNotRepeatingChar35 {
    public int FirstNotRepeatingChar(String str) {
        int [] intsMap = new int [256];
        char[] chars = str.toCharArray();
        for (int i=0;i<chars.length;++i){
            intsMap[chars[i]]++;
        }
        for (int i=0;i<chars.length;++i){
            if (intsMap[chars[i]]==1){
                return i;
            }
        }
        //牛客网错误的情况下，本题规定为-1
        return -1;
    }

    public static void main(String[] args) {
        String string = new String("abaccdeff");
        System.out.println(new StringFirstNotRepeatingChar35().FirstNotRepeatingChar(string));
    }
}
