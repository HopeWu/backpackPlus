package com.cmcc.common.utils;

//import com.cmcc.project.invoice.domain.Invoice;

/*
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

 */

/**
 * 背包算法，吴浩鹏提供
 */
// 默认 plus_Work 为100，先提前把容量算到plus_Work往前
public class BagUtils {
    public static void getBastIntChoose(int[] deal_Array, int _sum) {
        int plus_Work = 100;
        int sum = _sum + plus_Work;
        int dp[] = new int[sum+1];
        char state[][] = new char[deal_Array.length][sum+1]; /* 记录路径的二维数组 */
        int i, j;
        int M = sum; // 待查找近似值
        /* 01背包 */
        for (i = 0; i < deal_Array.length; ++i) {
            for (j = M; j >= deal_Array[i]; --j) {
                int tmp = dp[j - deal_Array[i]] + deal_Array[i];
                if (tmp > dp[j]) {
                    dp[j] = tmp;
                    state[i][j] = 1;
                }
            }
        }
        i = deal_Array.length; // 第几个商品

        // 确定背包的容量，从 _sum 开始一直到 _sum + plus_Work
        int capacity = _sum;
        while( dp[capacity] < _sum && capacity <= _sum + plus_Work){
            capacity++;
        }
        // 判断是哪个原因跳出while循环，如果不是因为第一个，则说明plus_Work不够大，在此输出提示信息
        if(dp[capacity] < _sum){
            System.out.println("警告：plus_Work不够大，选择出的发票总金额仍小于报销金额！请增大plus_Work重试。建议100起步。");
            // capacity已经是_sum + plus_Work + 1将capacity回退为_sum + plus_Work让程序继续运行
            capacity -= 1;
        }

        j = capacity;// 当前背包容量
        System.out.println("============================");
        int all = 0;
        String str = "";
        while ((--i) >= 0) {
            if (state[i][j] == 1) {
                all+=deal_Array[i];
                str += deal_Array[i]+"+";
                j -= deal_Array[i];
            }
        }
        str = str.substring(0,str.length()-1)+"="+all;
        System.out.println(str);
    }

    // 利用重栽函数模拟java的参数设置默认值功能
    public static void getBastIntChoose(int[] deal_Array, int _sum, int plus_Work) {
        int sum = _sum + plus_Work;
        int dp[] = new int[sum+1];
        char state[][] = new char[deal_Array.length][sum+1]; /* 记录路径的二维数组 */
        int i, j;
        int M = sum; // 待查找近似值
        /* 01背包 */
        for (i = 0; i < deal_Array.length; ++i) {
            for (j = M; j >= deal_Array[i]; --j) {
                int tmp = dp[j - deal_Array[i]] + deal_Array[i];
                if (tmp > dp[j]) {
                    dp[j] = tmp;
                    state[i][j] = 1;
                }
            }
        }
        i = deal_Array.length; // 第几个商品
        // 确定背包的容量，从 _sum 开始一直到 _sum + plus_Work
        int capacity = _sum;
        while( dp[capacity] < _sum && capacity <= _sum + plus_Work){
            capacity++;
        }
        // 判断是哪个原因跳出while循环，如果不是因为第一个，则说明plus_Work不够大，在此输出提示信息
        if(dp[capacity] < _sum){
            System.out.println("警告：plus_Work不够大，选择出的发票总金额仍小于报销金额！请增大plus_Work重试。建议100起步。");
            // capacity已经是_sum + plus_Work + 1将capacity回退为_sum + plus_Work让程序继续运行
            capacity -= 1;
        }

        j = capacity;// 当前背包容量
        System.out.println("============================");
        int all = 0;
        String str = "";
        while ((--i) >= 0) {
            if (state[i][j] == 1) {
                all+=deal_Array[i];
                str += deal_Array[i]+"+";
                j -= deal_Array[i];
            }
        }
        str = str.substring(0,str.length()-1)+"="+all;
        System.out.println(str);
    }

    /*
    public static List<Invoice> getBastInvoiceChose(List<Invoice> invoices, double bxed) {
        if(invoices == null || invoices.size()==0 || bxed<0.01){
            //入参不合格
            return null;
        }
        int sum = (int)bxed*100;
        int dp[] = new int[sum+1];
        int size = invoices.size();
        char state[][] = new char[size][sum+1]; // 记录路径的二维数组
        int i, j;
        int M = sum; // 待查找近似值

        // 01背包
        for (i = 0; i < size; ++i) {
            int fpiValue =(int)invoices.get(i).getJshjxx().doubleValue()*100;//第i张发票的金额*100
            for (j = M; j >= fpiValue; --j) {
                int tmp = dp[j - fpiValue] + fpiValue;
                if (tmp > dp[j]) {
                    dp[j] = tmp;
                    state[i][j] = 1;
                }
            }
        }
        i = size; // 第几个商品
        j = M;// 当前背包容量
        int all = 0;
        String str = "";
        List<Invoice> result = new ArrayList<>();
        while ((--i) >= 0) {
            if (state[i][j] == 1) {
                int vali = (int)invoices.get(i).getJshjxx().doubleValue()*100;
                result.add(invoices.get(i));
                all+=vali;
                str += vali+"+";
                j -= vali;
            }
        }
        str = str.substring(0,str.length()-1)+"="+all;
        System.out.println(str);
        return result;
    }

    public static void test1(){
        int[] ints = {343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225,
                264,343,288,343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254,
                225, 264,343,288,343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183,
                254, 225, 264,343,288,343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206,
                183, 254, 225, 264,343,288,343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207,
                206, 183, 254, 225, 264,343,288,343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209,
                207, 206, 183, 254, 225, 264,343,288,343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281,
                209, 207, 206, 183, 254, 225, 264,343,288,343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253,
                281, 209, 207, 206, 183, 254, 225, 264,343,288,343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171,
                253, 281, 209, 207, 206, 183, 254, 225, 264,343,288,343,288, 197, 197, 221, 195, 240, 237, 257, 110,
                171, 253, 281, 209, 207, 206, 183, 254, 225, 264,343,288,343,288, 197, 197, 221, 195, 240, 237, 257,
                110, 171, 253, 281, 209, 207, 206, 183, 254, 225, 264,343,288,343,288, 197, 197, 221, 195, 240, 237,
                257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225, 264,343,288,343,288, 197, 197, 221, 195, 240,
                237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225, 264,343,288,343,288, 197, 197, 221, 195,
                240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225, 264,343,288,343,288, 197, 197, 221,
                195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225, 264,343,288,343,288, 197, 197,
                221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225, 264,343,288,343,288, 197,
                197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225, 264,343,288,343,288,
                197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225, 264,343,288,343,
                288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225, 264,343,288,
                343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225, 264,343,
                288,343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225, 264,
                343,288,343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254, 225,
                264,343,288,343,288, 197, 197, 221, 195, 240, 237, 257, 110, 171, 253, 281, 209, 207, 206, 183, 254};
        getBastIntChoose(ints,15678);
    }



    public static void test2(){
        Invoice inv1 = new Invoice();
        Invoice inv2 = new Invoice();
        inv1.setJshjxx(new BigDecimal(190.08));
        inv2.setJshjxx(new BigDecimal(101.33));
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(inv1);
        invoices.add(inv2);
        List<Invoice> choose = getBastInvoiceChose(invoices, 100);
        for(Invoice in : choose){
            System.out.println(in.getJshjxx().doubleValue());
        }
    }
     */

    public static void test3(){
        int[] ints = {4,6,10,8,20,30};
        getBastIntChoose(ints,57);
    }

    public static void main(String[] args) {
        //测试
        test3();
    }
}
