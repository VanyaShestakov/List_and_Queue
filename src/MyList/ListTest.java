package MyList;

import org.junit.jupiter.api.*;

import java.util.LinkedList;
import java.util.Optional;

class ListTest {
    static final int MAX_VALUE = 4;
    static final int MIN_VALUE = 1;

    private List<Integer> list;

    @BeforeEach
    void init() {
        list = new List<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            list.add(i);
        }
    }

    @Test
    void addTest() {
        int[] expectedArr = {1, 2, 3, 4};
        int[] actualArr = new int[4];
        for (int i = 0; i < MAX_VALUE; i++) {
            actualArr[i] = list.get(i);
        }
        Assertions.assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    void removeAtTest() {
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
    @DisplayName("hello")
    void removeAllTest() {
        list = new List<>();
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
        list.clear();
        Assertions.assertTrue(list.isEmpty());
        Assertions.assertEquals(0, list.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> list.get(100));
    }

    @Test
    void getTest() {
        Assertions.assertEquals(Optional.of(4), Optional.ofNullable(list.get(3)));
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> list.get(100));
    }

    @Test
    void indexOfTest() {
        Assertions.assertEquals(-1, list.indexOf(10));
        Assertions.assertEquals(2, list.indexOf(3));
    }

    @Test
    void containsTest() {
        Assertions.assertTrue(list.contains(1));
        Assertions.assertFalse(list.contains(100));
    }

    @Test
    void isEmptyTest() {
        Assertions.assertFalse(list.isEmpty());
        list.clear();
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void sizeTest() {
        list = new List<>();
        Assertions.assertEquals(0, list.size());
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            list.add(i);
        }
        Assertions.assertEquals(4, list.size());
    }

    @Test
    void toStringTest() {
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

    @Test
    void hashCodeTest() {
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

        Assertions.assertEquals(firstList.hashCode(), secondList.hashCode());
        Assertions.assertEquals(firstList.hashCode(), secondList.hashCode());
        Assertions.assertNotEquals(firstList.hashCode(), otherList.hashCode());
        Assertions.assertNotEquals(firstList.hashCode(), otherClassObj.hashCode());

    }
}