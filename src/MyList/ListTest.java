package MyList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Optional;

class ListTest {
    static final int MAX_VALUE = 4;
    static final int MIN_VALUE = 1;

    @Test
    void addTest() {
        List<Integer> list = new List<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            list.add(i);
        }
        int[] expectedArr = {1, 2, 3, 4};
        int[] actualArr = new int[4];
        for (int i = 0; i < MAX_VALUE; i++) {
            actualArr[i] = list.get(i);
        }
        Assertions.assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    void removeAtTest() {
        List<Integer> list = new List<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            list.add(i);
        }
        list.removeAt(1); //removable item = 2(index = 1)
        list.removeAt(1); //removable item = 3(index = 1)
        int[] expectedArr = {1, 4};
        int[] actualArr = new int[2];
        for (int i = 0; i < 2; i++) {
            actualArr[i] = list.get(i);
        }
        Assertions.assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    void removeAllTest() {
        List<Integer> list = new List<>();
        list.add(1);
        list.add(1);
        list.add(3);
        list.add(1);
        list.add(2);
        list.removeAll(1);
        String expected = "{3 -> 2}";
        Assertions.assertEquals(list.toString(), expected);
    }

    @Test
    void clearTest() {
        List<Integer> list = new List<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            list.add(i);
        }
        list.clear();
        Assertions.assertTrue(list.isEmpty());
        Assertions.assertEquals(0, list.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> list.get(100));
    }

    @Test
    void getTest() {
        List<Integer> list = new List<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            list.add(i);
        }
        Assertions.assertEquals(Optional.of(4), Optional.ofNullable(list.get(3)));
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> list.get(100));
    }

    @Test
    void indexOfTest() {
        List<Integer> list = new List<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            list.add(i);
        }
        Assertions.assertEquals(-1, list.indexOf(10));
        Assertions.assertEquals(2, list.indexOf(3));
    }

    @Test
    void containsTest() {
        List<Integer> list = new List<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            list.add(i);
        }
        Assertions.assertTrue(list.contains(1));
        Assertions.assertFalse(list.contains(100));
    }

    @Test
    void isEmptyTest() {
        List<Integer> list = new List<>();
        Assertions.assertTrue(list.isEmpty());
        list.add(1);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void sizeTest() {
        List<Integer> list = new List<>();
        Assertions.assertEquals(0, list.size());
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            list.add(i);
        }
        Assertions.assertEquals(4, list.size());
    }

    @Test
    void toStringTest() {
        List<Integer> list = new List<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            list.add(i);
        }
        String expectedStr = "{1 -> 2 -> 3 -> 4}";
        Assertions.assertEquals(expectedStr, list.toString());
    }

    @Test
    void equalsTest() {
        List<Integer> firstList = new List<>();
        List<Integer> secondList = new List<>();
        List<Integer> otherList = new List<>();
        LinkedList<Integer> otherClassObj = new LinkedList<>();

        firstList.add(1);
        firstList.add(2);
        firstList.add(3);

        secondList.add(1);
        secondList.add(2);
        secondList.add(3);

        otherClassObj.add(1);
        otherClassObj.add(2);
        otherClassObj.add(3);

        otherList.add(3);
        otherList.add(2);
        otherList.add(1);

        Assertions.assertTrue(firstList.equals(secondList));
        Assertions.assertTrue(firstList.equals(firstList));
        Assertions.assertFalse(firstList.equals(null));
        Assertions.assertFalse(firstList.equals(otherClassObj));
        Assertions.assertFalse(firstList.equals(otherList));
        secondList.removeAt(2);
        Assertions.assertFalse(firstList.equals(secondList));

    }
}