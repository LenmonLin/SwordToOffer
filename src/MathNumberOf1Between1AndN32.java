/**
 * @author LemonLin
 * @Description :MathNumberOf1Between1AndN32
 * @date 2019/3/14-19:14
 * 题目描述
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包
 * 含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,
 * 并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 *
 * 解题思路：
 * 笨方法：就是从1开始遍历，对数进行判断。
 * 稍微聪明的一点方法寻找数出现一的规律。注意审题：不是含1数字的个数.......而是1的个数。
 *
 * 设N = abcde ,其中abcde分别为十进制中各位上的数字。
 * 如果要计算百位上1出现的次数，它要受到3方面的影响：百位上的数字，百位以下（低位）的数字，百位以
 * 上（高位）的数字。
 * ① 如果百位上数字为0，百位上可能出现1的次数由更高位决定。比如：12013，则可以知道百位出现1的情况
 * 可能是：100~199，1100~1199,2100~2199，，...，11100~11199，一共1200个。可以看出是由更高
 * 位数字（12）决定，并且等于更高位数字（12）乘以 当前位数（100）。
 * ② 如果百位上数字为1，百位上可能出现1的次数不仅受更高位影响还受低位影响。比如：12113，则可以知
 * 道百位受高位影响出现的情况是：100~199，1100~1199,2100~2199，，....，11100~11199，一
 * 共1200个。和上面情况一样，并且等于更高位数字（12）乘以 当前位数（100）。但同时它还受低位影响，
 * 百位出现1的情况是：12100~12113,一共14个，等于低位数字（13）+1。
 * ③ 如果百位上数字大于1（2~9），则百位上出现1的情况仅由更高位决定，比如12213，则百位出现1的情
 * 况是：100~199,1100~1199，2100~2199，...，11100~11199,12100~12199,一共有1300个，并且
 * 等于更高位数字+1（12+1）乘以当前位数（100）。
 *
 * 以上是计算百位的方法：个位，十位等等同理
 */
public class MathNumberOf1Between1AndN32 {
    public int NumberOf1Between1AndN_Solution(int n) {
        //1的个数
        int count =0;
        //当前位
        int i=1;
        //高位数字,当前位数字，低位数字
        int before=0,current=0,after=0;
        //123/10 =12，123%10 = 3,n/i的目的是把当前位放到个位，方便后续处理
        while (n/i!=0){
            before = (n/i)/10;
            current = (n/i)%10;
            after = n-(n/i)*i;
            //如果为0,出现1的次数由高位决定,等于高位数字 * 当前位数
            if (current ==0){
                count+=before*i;
            }
            //如果为1,出现1的次数由高位和低位决定,高位*当前位+低位+1
            else if (current ==1){
                count += before * i + after + 1;
            }
            //如果大于1,出现1的次数由高位决定,//（高位数字+1）* 当前位数
            else{
                count += (before + 1) * i;
            }
            //前移一位
            i = i*10;
        }


        return count;
    }

    public static void main(String[] args) {

    }
}
