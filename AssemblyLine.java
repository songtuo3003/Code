package cn.monitor4all.miaoshadao.dao;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AssemblyLine {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int x=s.nextInt();
        int y=s.nextInt();
        int [][] arrays = new int[x][y];//初始化数组
        for(int i=0;i<x;i++)//循环输入
            for(int j=0;j<y;j++)
                arrays[i][j]=s.nextInt();
        arrays = solve(arrays);
        for(int i=0;i<x;i++){//循环输出
            for(int j=0;j<y;j++)
                System.out.print(arrays[i][j]+"\t");
            System.out.println("\n");
        }
//        5 7 2 4
//        3 2 1 5
//        3 6 2 6
//        1	0	3	4
//
//        2	4	5	1
//
//        3	1	3	0


//        2 1 2
//        5 7 5
//        2 5 2
//        1	4	1
//
//        1	0	1
//
//        2	1	2

    }
    public static int[][] solve(int[][] array){
        int n = array.length, m = array[0].length;
        PriorityQueue<Person> queue = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.value - o1.value;
            }
        });
        int[][] res = new int[n][m];
        for(int i = 0; i < n; i++){
            int count = 0;
            Person prePerson = null;
            for(int j = 0; j < m; j++){
                queue.add(new Person(j, array[i][j]));
            }
            while (!queue.isEmpty()){
                Person person = queue.remove();
                if(prePerson != null && person.value == prePerson.value){
                    res[i][person.index] = res[i][prePerson.index];
                }else{
                    res[i][person.index] = count;
                }
                prePerson = person;
                count++;
            }
        }
        int[][] res1 = new int[n][m];
        for(int j = 0; j < m; j++){
            int count = 0;
            Person prePerson = null;
            for(int i = 0; i < n; i++){
                queue.add(new Person(i, array[i][j]));
            }
            while (!queue.isEmpty()){
                Person person = queue.remove();
                if(prePerson != null && person.value == prePerson.value){
                    res1[person.index][j] = res1[prePerson.index][j];
                }else{
                    res1[person.index][j] = count;
                }
                prePerson = person;
                count++;
            }
        }
        int[][] resFinal = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                resFinal[i][j] = res[i][j] + res1[i][j];
            }
        }
        return resFinal;
    }
    static class Person{
        int index;
        int value;
        public  Person(int in, int val){
            this.index = in;
            this.value = val;
        }
    }

}
