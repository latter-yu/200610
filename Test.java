import java.util.*;

public class Test {
    
    public static void main1(String[] args) {
        // 单步调试运行
        System.out.println(new B().getValue());
    }
    static class A {

        protected int value;

        public A(int v) {
            setValue(v);
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {

            try {
                value++;
                return value;
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                this.setValue(value);
                System.out.println(value); // 22 34 17
            }
            return value;
        }
    }

    static class B extends A {

        public B() {
            super(5);
            setValue(getValue() - 3);
        }

        public void setValue(int value) {
            super.setValue(2 * value);
        }
    }
}
class Test2 {

    static int cnt = 6;

    static {
        cnt += 9;
    }

    public static void main2(String[] args) {
        System.out.println(cnt); // 5
    }

    static {
        cnt /= 3;
    }
}

class Main {

    public static void main3(String[] args) {
        // 有一个数组a[N]顺序存放0~N-1,要求每隔两个数删掉一个数，到末尾时循环至开头继续进行，
        // 求最后一个被删掉的数的原始下标位置。以8个数(N=7)为例:
        // {0,1, 2, 3, 4, 5, 6, 7}，0->1->2(删除)->3->4->5<(删除)->6->7->0(删除)，如此循环直到最后一个数被删除

        // 每组数据为一行一个整数n(小于等于1000)，为数组成员数,如果大于1000，则对a[999]进行计算
        // 一行输出最后一个被删掉的数的原始下标位置

        // 例如: 输入 8    输出 6
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];

            // 1. 实现一个长度为 n 的数组
            for (int cur = 0; cur < arr.length; cur++) {
                arr[cur] = cur;
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < arr.length; i++) {
                // 2. 将数组元素按顺序加到队列中
                queue.add(arr[i]);
            }

            while (queue.size() != 1) {
                int count = 0;
                while (count != 2) {
                    // 3. 若 count != 2, 将队首的元素检索并加到队尾, 使数组实现循环
                    queue.add(queue.peek());
                    // queue.peek(); 检索但不删除此队列的头，如果此队列为空，则返回 null

                    // 4. 队首元素检索完, 删除队首元素, 使队列长保持不变, 便于检索下一个元素
                    queue.poll();
                    // 检索并删除此队列的头，如果此队列为空，则返回 null
                    count++;
                }
                // count = 2 时, 直接删除队首元素.
                queue.poll();
            }

            //  5. 当 queue.size() != 1, 即队列内仅有一个元素时, 输出元素(数组元素与下标相同)
            System.out.println(queue.element());
            // queue.element(); 检索，但不删除，这个队列的头
        }
        sc.close();
        // 关闭此扫描仪
    }

    public static void main(String[] args) {
        // 找出n个数里最小的k个

        // 每个测试输入包含空格分割的 n + 1个整数，最后一个整数为 k 值, n 不超过100.
        // 输出 n 个整数里最小的 k 个数. 升序输出

        // 输入  3 9 6 8 -10 7 -11 19 30 12 23 5
        // 输出  -11 -10 3 6 7

        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        while (!sc.hasNext("stop")) {
            // 1. 将输入的元素加入到 list 中.
            list.add(sc.nextInt());
        }

        // 2. 将 list 的最后一个数取出, 作为 k
        int k = list.get(list.size() - 1);
        list.remove(list.size() - 1);

        // 3. 将剩下的元素放入一个数组中
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        // 4. 冒泡排序找出 k 个最小值
        for (int bound = 0; bound < arr.length; bound++) {
            for (int cur = arr.length - 1; cur > bound; cur--) {
                if (arr[cur - 1] > arr[cur]) {
                    int tmp = arr[cur - 1];
                    arr[cur - 1] = arr[cur];
                    arr[cur] = tmp;
                }
            }
        }

        // 5. 输出前 k 个元素即为 k 个最小值
        for (int i = 0; i < k ; i++) {
            System.out.println(arr[i]);
        }
    }
}