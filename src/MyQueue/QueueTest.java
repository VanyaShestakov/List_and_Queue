package MyQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QueueTest {
    static final int MAX_VALUE = 4;
    static final int MIN_VALUE = 1;

    @Test
    void pushTest() {
        Queue<Integer> queue = new Queue<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            queue.push(i);
        }
        int[] expectedArr = {1, 2, 3, 4};
        int[] actualArr = new int[4];
        for (int i = 0; i < actualArr.length; i++) {
            actualArr[i] = queue.poll();
        }
        Assertions.assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    void pollTest() {
        Queue<Integer> queue = new Queue<>();
        Assertions.assertNull(queue.poll());
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            queue.push(i);
        }
        int[] actualArr = new int[2];
        int[] expectedArr = {1, 2};
        for (int i = 0 ; i < actualArr.length; i++) {
            actualArr[i] = queue.poll();
        }
        Assertions.assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    void sizeTest() {
        Queue<Integer> queue = new Queue<>();
        Assertions.assertEquals(0, queue.size());
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            queue.push(i);
        }
        Assertions.assertEquals(4, queue.size());
    }

    @Test
    void clearTest() {
        Queue<Integer> queue = new Queue<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            queue.push(i);
        }
        queue.clear();
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertEquals(0, queue.size());
    }

    @Test
    void containsTest() {
        Queue<Integer> queue = new Queue<>();
        Assertions.assertFalse(queue.contains(1));
        queue.push(1);
        Assertions.assertTrue(queue.contains(1));
    }

    @Test
    void isEmptyTest() {
        Queue<Integer> queue = new Queue<>();
        Assertions.assertTrue(queue.isEmpty());
        queue.push(1);
        Assertions.assertFalse(queue.isEmpty());
    }

    @Test
    void toStringTest() {
        Queue<Integer> queue = new Queue<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            queue.push(i);
        }
        String expectedStr = "[1, 2, 3, 4]";
        Assertions.assertEquals(expectedStr, queue.toString());
    }
}