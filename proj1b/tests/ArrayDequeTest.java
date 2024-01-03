import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    @Test
    @DisplayName("Test 1: When the object type is integer, we test if the deque is empty and size is 0 by simply initialising it.")
    /** This test whether an empty deque has been constructed successfully and if the size of the deque is correct. */
    public void isEmptyAndSizeTest01() {
        Deque<Integer> ad = new ArrayDeque<>(); // []
        assertWithMessage("This deque should be empty").that(ad.isEmpty()).isEqualTo(true);
        assertWithMessage("This deque size should be 0.").that(ad.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Test 2: When the object type is string, we test if the deque is empty and size is 0 by simply initialising it.")
    public void isEmptyAndSizeTest02() {
        Deque<String> ad = new ArrayDeque<>(); // []
        assertWithMessage("This deque should be empty").that(ad.isEmpty()).isEqualTo(true);
        assertWithMessage("This deque size should be 0.").that(ad.size()).isEqualTo(0);
    }
    @Test
    @DisplayName("Test 3: We pass one item in integer type to see if isEmpty returns false and size is 1.")
    public void isEmptyAndSizeTest03() {

        Deque<Integer> ad = new ArrayDeque<>(); // []
        ad.addLast(25);  // [25]
        assertWithMessage("This deque should not be empty").that(ad.isEmpty()).isEqualTo(false);
        assertWithMessage("This deque size should be 1.").that(ad.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test 4: We pass one item in string type to see if isEmpty returns false and size is 1.")
    public void isEmptyAndSizeTest04() {
        Deque<String> ad = new ArrayDeque<>(); // []
        ad.addFirst("Alyssa"); // ["Alyssa"]
        assertWithMessage("This deque should not be empty").that(ad.isEmpty()).isEqualTo(false);
        assertWithMessage("This deque size should be 1.").that(ad.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test 5: We pass multiple item in integer type to see if isEmpty returns false and size is 4 .")
    public void isEmptyAndSizeTest05() {
        Deque<Integer> ad = new ArrayDeque<>(); // []
        ad.addLast(25);
        ad.addLast(7); // [25, 7]
        ad.addFirst(94); // [25, 7, 94]
        ad.addFirst(93); // [25, 7, 94, 93]
        assertWithMessage("This deque should not be empty").that(ad.isEmpty()).isEqualTo(false);
        assertWithMessage("This deque size should be 4.").that(ad.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Test 6: We pass multiple item in string type to see if isEmpty returns false and size is 3.")
    public void isEmptyAndSizeTest06() {
        Deque<String> ad = new ArrayDeque<>(); // []
        ad.addLast("Alyssa");
        ad.addFirst("Yoongi"); // ["Alyssa", "Yoongi"]
        ad.addFirst("Happy"); // ["Alyssa", "Yoongi", "Happy"]
        assertWithMessage("This deque should not be empty").that(ad.isEmpty()).isEqualTo(false);
        assertWithMessage("This deque size should be 3.").that(ad.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Test 1: Initialise an empty deque and let it return the 1st item.")
    /** This test checks the function of get(). If a correct item is returned by using get(int i) function. */
    public void getTest01() {
        Deque<Integer> ad = new ArrayDeque<>(); // []
        assertWithMessage("Test 1: This deque should return null.").that(ad.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("Test 2: Passes an integer to the deque and let it return the 1st item.")
    public void getTest02() {
        Deque<Integer> ad = new ArrayDeque<>(); // []
        ad.addFirst(3); // [3]
        assertWithMessage("Test 2: This deque should return 3.").that(ad.get(0)).isEqualTo(3);
    }

    @Test
    @DisplayName("Test 3: Passes multiple integers to the deque and let it return the 2nd item.")
    public void getTest03() {
        Deque<Integer> ad = new ArrayDeque<>(); // []
        ad.addFirst(3); // [3]
        ad.addFirst(9); // [3, 9]
        ad.addFirst(93); // [3, 9, 93]
        ad.addLast(31); // [3, 9, 93, 31]
        assertWithMessage("Test 3: This deque should return 9.").that(ad.get(1)).isEqualTo(9);
    }

    @Test
    @DisplayName("Test 4: Passes multiple integers to the deque and let it return the 2nd item.")
    public void getTest04() {
        Deque<Integer> ad = new ArrayDeque<>(); // []
        ad.addFirst(3); // [3]
        ad.addFirst(9); // [3, 9]
        ad.addFirst(93); // [3, 9, 93]
        ad.addLast(31); // [3, 9, 93, 31]
        assertWithMessage("Test 4: This deque should return null.").that(ad.get(49)).isEqualTo(null);
    }

    @Test
    @DisplayName("Test 5: Initialise an empty string type deque and let it return the 7st item.")
    public void getTest05() {
        Deque<String> ad = new ArrayDeque<>(); // []
        assertWithMessage("Test 5: This deque should return null.").that(ad.get(6)).isEqualTo(null);
    }

    @Test
    @DisplayName("Test 6: Passes a string to the deque and let it return the 1st item.")
    public void getTest06() {
        Deque<String> ad = new ArrayDeque<>(); // []
        ad.addFirst("Alyssa"); // ["Alyssa"]
        assertWithMessage("Test 6: This deque should return Alyssa.").that(ad.get(0)).isEqualTo("Alyssa");
    }

    @Test
    @DisplayName("Test 7: Passes multiple strings to the deque and let it return the 3rd item.")
    public void getTest07() {
        Deque<String> ad = new ArrayDeque<>(); // []
        ad.addFirst("Alyssa"); // ["Alyssa"]
        ad.addFirst("Yoongi"); // ["Yoongi", "Alyssa"]
        ad.addFirst("Confidence"); // ["Confidence", "Yoongi", "Alyssa"]
        ad.addLast("Calm"); // ["Confidence", "Yoongi", "Alyssa", "Calm"]
        assertWithMessage("Test 7: This deque should return 9.").that(ad.get(2)).isEqualTo("Alyssa");
    }

    @Test
    @DisplayName("Test 8: let the deque return the 75th item and it should return null.")
    public void getTest08() {
        Deque<String> ad = new ArrayDeque<>(); // []
        ad.addFirst("Alyssa"); // ["Alyssa"]
        assertWithMessage("Test 8: This deque should return null.").that(ad.get(74)).isEqualTo(null);
    }

    @Test
    @DisplayName("Test 9: let the deque return the -75th item and it should return null.")
    public void getTest09() {
        Deque<String> ad = new ArrayDeque<>(); // []
        ad.addFirst("Alyssa"); // ["Alyssa"]
        assertWithMessage("Test 9: This deque should return null.").that(ad.get(-74)).isEqualTo(null);
    }
}
