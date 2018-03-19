import java.util.Arrays;
import java.util.Collection;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/3/2  12:26
 * Version: V1.0
 * Description:排序 算法
 * ======================
 */
public class Alograthm {

    /**
     * 插入排序
     *
     *
     * 先指定选取任意一个元素作为起始排好的序列 -- 称为哨兵  可以使用第一个元素 作为 哨兵 进行比较
     * 其余的元素再分别 与 排好的 元素 进行比较  插入 指定的位置
     */
    public static int[] insertionSort(int[] arr){

        //获取数组中的每一个元素
        for(int i=0 ; i<arr.length;i++){
            //将 下标为i 先预存 与之前保存的数值 一一进行比较，看要插入到那个位置
            int element = arr[i];
            int j; //定义一个全局的 比较位置的变量，下面比较到那个位置 就保存在那个位置
            for(j=i;j>0 && element<arr[j-1];j--){
                arr[j] = arr[j-1];
            }
            arr[j] = element; //将比较的元素 直接保存在 下标为j的位置
        }
        return arr;
    }

  /*  public static int[] bubbleSort(int[] arr){


    }*/



    public static void main(String[] args){
        int [] arr = {10,6,3,18,30,20,2,1,50,100};
        System.out.println("插入排序好以后："+ Arrays.toString(insertionSort(arr)));




    }



}
