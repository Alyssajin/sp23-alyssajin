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

}
