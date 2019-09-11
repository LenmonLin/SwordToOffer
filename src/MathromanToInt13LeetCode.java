import java.util.HashMap;
import java.util.Scanner;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII,
 * 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 
 * IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9
 * 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * 示例 1:
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * 思路：
 * 1、首先将所有的组合可能性列出并添加到哈希表中
 * 2、然后对字符串进行遍历，由于组合只有两种，一种是 1 个字符，一种是 2 个字符，其中 2 个字符优
 * 先于 1 个字符
 * 3、先判断两个字符的组合在哈希表中是否存在，存在则将值取出加到结果 ans 中，并向后移2个字符
 * 不存在则将判断当前 1 个字符是否存在，存在则将值取出加到结果 ans 中，并向后移 1 个字符
 * 4、遍历结束返回结果 ans
 * @author LemonLin
 * @Description :MathromanToInt13LeetCode
 * @date 19.9.11-16:57
 */
public class MathromanToInt13LeetCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(new MathromanToInt13LeetCode().romanToInt(s));
    }
    public int romanToInt(String s) {
        HashMap hashMap = new HashMap();
        hashMap.put("I",1);
        hashMap.put("V",5);
        hashMap.put("X",10);
        hashMap.put("L",50);
        hashMap.put("C",100);
        hashMap.put("D",500);
        hashMap.put("M",1000);
        hashMap.put("IV",4);
        hashMap.put("IX",9);
        hashMap.put("XL",40);
        hashMap.put("XC",90);
        hashMap.put("CD",400);
        hashMap.put("CM",900);
        int result =0;
        for (int i=0;i<s.length();i++){
            if (i+1<s.length()&&hashMap.containsKey(s.substring(i,i+2))){
                result+=(int)hashMap.get(s.substring(i,i+2));
                //如果是取两个的话，需要下标加两次
                i++;
            }else {
                result+=(int)hashMap.get(s.substring(i,i+1));
            }
        }
        return result;
    }
}
