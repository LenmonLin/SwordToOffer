/**
 * 比较两个版本号 version1 和 version2。
 * 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1， 除此之外返回 0。
 * 你可以假设版本字符串非空，并且只包含数字和 . 字符。
 *  . 字符不代表小数点，而是用于分隔数字序列。
 * 例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
 * 你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）和第二
 * 级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
 * 示例 1:
 * 输入: version1 = "0.1", version2 = "1.1"
 * 输出: -1
 * 示例 2:
 * 输入: version1 = "1.0.1", version2 = "1"
 * 输出: 1
 * 示例 3:
 * 输入: version1 = "7.5.2.4", version2 = "7.5.3"
 * 输出: -1
 * 示例 4：
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 * 解释：忽略前导零，“01” 和 “001” 表示相同的数字 “1”。
 * 示例 5：
 * 输入：version1 = "1.0", version2 = "1.0.0"
 * 输出：0
 * 解释：version1 没有第三级修订号，这意味着它的第三级修订号默认为 “0”。
 * 提示：
 * 版本字符串由以点 （.） 分隔的数字字符串组成。这个数字字符串可能有前导零。
 * 版本字符串不以点开始或结束，并且其中不会有两个连续的点。
 * 思路：这题思路比较直白，统一把两个版本号整理成都是标准形式，即对齐。首先：处理前导
 * 0，直接用正则表达式处理s.replaceAll("^(0+)", "");其次：如果其中一个字符串某段没有数字
 * 的，补0处理。怎么补0，开辟一个新的字符数组，先复制短的版本，剩余长度补0；
 * 最后从头开始遍历，比较大小，如果其中一个字符串没有数字的补0处理。
 * @author LemonLin
 * @Description :StringCompareVersionNumbers
 * @date 19.6.23-23:24
 * 参考：https://leetcode-cn.com/problems/compare-version-numbers/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-40/
 * 思路按照「点」对版本号进行切割，然后依次比较每个数字即可。这里切割出来的是字符串，
 * 所以我们需要把字符串转为数字，因为字符串转数字不是这道题的重点，所以直接调用系统
 * 提供的 Integer.parseInt 即可。
 */
public class StringCompareVersionNumbers165LeetCode {

    //直接用 Integer.parseInt 省去了处理前导零的步骤，让代码简洁了
    public int compareVersion2(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < nums1.length || j < nums2.length) {
            //这个技巧经常用到，当一个已经遍历结束的话，我们将其赋值为 0
            String num1 = i < nums1.length ? nums1[i] : "0";
            String num2 = j < nums2.length ? nums2[j] : "0";
            int res = compare(num1, num2);
            if (res == 0) {
                i++;
                j++;
            } else {
                return res;
            }
        }
        return 0;
    }
    private int compare(String num1, String num2) {
        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        if (n1 > n2) {
            return 1;
        } else if (n1 < n2) {
            return -1;
        } else {
            return 0;
        }
    }
    //这种写法比较复杂
    public int compareVersion(String version1, String version2) {
        String[] v1=version1.split("\\.");
        String[] v2=version2.split("\\.");
        int v1length=v1.length;
        int v2length=v2.length;
        int length = v1length>v2length?v1length:v2length;
        String [] v3=new String[length];
        //补零处理
        if (v1length>v2length){
                for (int i=0;i<v2length;i++){
                    v3[i]=v2[i];
                }
                for (int i=0;i<v1length-v2length;i++){
                    v3[v2length+i]="0";
                }
        }else {
            for (int i=0;i<v1length;i++){
                v3[i]=v1[i];
            }
            for (int i=0;i<v2length-v1length;i++){
                v3[v1length+i]="0";
            }
        }
        for (int i=0;i<length;i++){
            char[]chars1;
            char[]chars2;
            if (v1length>v2length){
                chars1= reduceZero(v1[i]).toCharArray();
                chars2 = reduceZero(v3[i]).toCharArray();
            }else {
                chars1 = reduceZero(v3[i]).toCharArray();
                chars2 =  reduceZero(v2[i]).toCharArray();
            }
            if (chars1.length>chars2.length){
                return 1;
            }else if (chars1.length<chars2.length){
                return -1;
            }else {
                for (int j=0;j<chars1.length;j++){
                    int int1 =chars1[j]-'0';
                    int int2=chars2[j]-'0';
                    if (int1-int2>0){
                        return 1;
                    }else if (int1-int2<0){
                        return -1;
                    }else {
                        continue;
                    }
                }
            }
        }
        return 0;
    }

    //处理前导零函数
    public String reduceZero(String s){
        if (s.length()==1)return s;
        char[] chars = s.toCharArray();
        boolean flag =false;
        for (int i=0;i+1<chars.length;i++){
            if (chars[i]=='0'&&chars[i]==chars[i+1]){
                continue;
            }else {
                flag=true;
                break;
            }
        }
        if (flag==false)return "0";
        //用正则表达式匹配，多余的零用空格替代
        String result =s.replaceAll("^(0+)", "");
        return result;
    }
    public static void main(String[] args) {
        String v1="1.1";
        String v2="1.0010";
        System.out.println(new StringCompareVersionNumbers165LeetCode().compareVersion(v1, v2));
    }
}
