import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    @Nested
    /** This test whether an empty deque has been constructed successfully and if the size of the deque is correct. */
    class isEmptyAndSizeTest {
        @Test
        @DisplayName("Test 1: When the object type is integer, we test if the deque is empty and size is 0 by simply initialising it.")
        public void isEmptyAndSizeTest01() {
            Deque<Integer> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            assertWithMessage("This deque should be empty").that(ad.isEmpty()).isEqualTo(true);
            assertWithMessage("This deque size should be 0.").that(ad.size()).isEqualTo(0);
        }

        @Test
        @DisplayName("Test 2: When the object type is string, we test if the deque is empty and size is 0 by simply initialising it.")
        public void isEmptyAndSizeTest02() {
            Deque<String> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            assertWithMessage("This deque should be empty").that(ad.isEmpty()).isEqualTo(true);
            assertWithMessage("This deque size should be 0.").that(ad.size()).isEqualTo(0);
        }

        @Test
        @DisplayName("Test 3: We pass one item in integer type to see if isEmpty returns false and size is 1.")
        public void isEmptyAndSizeTest03() {

            Deque<Integer> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            ad.addLast(25);  // [25, null, null, null, null, null, null, null]
            assertWithMessage("This deque should not be empty").that(ad.isEmpty()).isEqualTo(false);
            assertWithMessage("This deque size should be 1.").that(ad.size()).isEqualTo(1);
        }

        @Test
        @DisplayName("Test 4: We pass one item in string type to see if isEmpty returns false and size is 1.")
        public void isEmptyAndSizeTest04() {
            Deque<String> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            ad.addFirst("Alyssa"); // ["Alyssa", null, null, null, null, null, null, null]
            assertWithMessage("This deque should not be empty").that(ad.isEmpty()).isEqualTo(false);
            assertWithMessage("This deque size should be 1.").that(ad.size()).isEqualTo(1);
        }

        @Test
        @DisplayName("Test 5: We pass multiple item in integer type to see if isEmpty returns false and size is 4 .")
        public void isEmptyAndSizeTest05() {
            Deque<Integer> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            ad.addLast(25);// [null, 25, null, null, null, null, null, null]
            ad.addLast(7); // [null, 25, 7, null, null, null, null, null]
            ad.addFirst(94); // [94, 25, 7, null, null, null, null, null]
            ad.addFirst(93); // [94, 25, 7, null, null, null, null, 93]
            assertWithMessage("This deque should not be empty").that(ad.isEmpty()).isEqualTo(false);
            assertWithMessage("This deque size should be 4.").that(ad.size()).isEqualTo(4);
        }

        @Test
        @DisplayName("Test 6: We pass multiple item in string type to see if isEmpty returns false and size is 3.")
        public void isEmptyAndSizeTest06() {
            Deque<String> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            ad.addLast("Alyssa");// [null, "Alyssa", null, null, null, null, null, ]
            ad.addFirst("Yoongi"); // ["Yoongi","Alyssa", null, null, null, null, null, null]
            ad.addFirst("Happy"); // ["Yoongi","Alyssa", null, null, null, null, null, "Happy"]
            assertWithMessage("This deque should not be empty").that(ad.isEmpty()).isEqualTo(false);
            assertWithMessage("This deque size should be 3.").that(ad.size()).isEqualTo(3);
        }
    }
    @Nested
    /** This test checks the function of get(). If a correct item is returned by using get(int i) function. */
    class getTest {
        @Test
        @DisplayName("Test 1: Initialise an empty deque and let it return the 1st item.")
        public void getTest01 () {
            Deque<Integer> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            assertWithMessage("Test 1: This deque should return null.").that(ad.isEmpty()).isEqualTo(true);
        }

        @Test
        @DisplayName("Test 2: Passes an integer to the deque and let it return the 1st item.")
        public void getTest02 () {
            Deque<Integer> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            ad.addFirst(3); // [3, null, null, null, null, null, null, null]
            assertWithMessage("Test 2: This deque should return 3.").that(ad.get(0)).isEqualTo(3);
        }

        @Test
        @DisplayName("Test 3: Passes multiple integers to the deque and let it return the 2nd item.")
        public void getTest03 () {
            Deque<Integer> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            ad.addFirst(3); // [3, null, null, null, null, null, null, null]
            ad.addFirst(9); // [3, null, null, null, null, null, null, 9]
            ad.addFirst(93); // [3, null, null, null, null, null, 93, 9]
            ad.addLast(31); // [3, 31, null, null, null, null, 93, 9]
            assertWithMessage("Test 3: This deque should return 31.").that(ad.get(1)).isEqualTo(31);
            assertWithMessage("Test 3: This deque should return 93.").that(ad.get(6)).isEqualTo(93);
        }

        @Test
        @DisplayName("Test 4: Passes multiple integers to the deque and let it return the 2nd item.")
        public void getTest04 () {
            Deque<Integer> ad = new ArrayDeque<>(); // []
            ad.addFirst(3); // [3, null, null, null, null, null, null, null]
            ad.addFirst(9); // [3, null, null, null, null, null, null, 9]
            ad.addFirst(93); // [3, null, null, null, null, null, 93, 9]
            ad.addLast(31); // [3, 31, null, null, null, null, 93, 9]
            assertWithMessage("Test 4: This deque should return null.").that(ad.get(49)).isEqualTo(null);
        }

        @Test
        @DisplayName("Test 5: Initialise an empty string type deque and let it return the 7st item.")
        public void getTest05 () {
            Deque<String> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            assertWithMessage("Test 5: This deque should return null.").that(ad.get(6)).isEqualTo(null);
        }

        @Test
        @DisplayName("Test 6: Passes a string to the deque and let it return the 1st item.")
        public void getTest06 () {
            Deque<String> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            ad.addFirst("Alyssa"); // ["Alyssa", null, null, null, null, null, null, null]
            assertWithMessage("Test 6: This deque should return Alyssa.").that(ad.get(0)).isEqualTo("Alyssa");
        }

        @Test
        @DisplayName("Test 7: Passes multiple strings to the deque and let it return the 3rd item.")
        public void getTest07 () {
            Deque<String> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            ad.addFirst("Alyssa"); // ["Alyssa", null, null, null, null, null, null, null]
            ad.addFirst("Yoongi"); // ["Alyssa", null, null, null, null, null, null, "Yoongi"]
            ad.addFirst("Confidence"); // ["Alyssa", null, null, null, null, null, "Confidence", "Yoongi"]
            ad.addLast("Calm"); // ["Alyssa", "Calm", null, null, null, null, "Confidence", "Yoongi"]
            assertWithMessage("Test 7: This deque should return null.").that(ad.get(2)).isEqualTo(null);
            assertWithMessage("Test 7: This deque should return Calm").that(ad.get(1)).isEqualTo("Calm");
        }

        @Test
        @DisplayName("Test 8: let the deque return the 75th item and it should return null.")
        public void getTest08 () {
            Deque<String> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            ad.addFirst("Alyssa"); // ["Alyssa", null, null, null, null, null, null, null]
            assertWithMessage("Test 8: This deque should return null.").that(ad.get(74)).isEqualTo(null);
        }

        @Test
        @DisplayName("Test 9: let the deque return the -75th item and it should return null.")
        public void getTest09 () {
            Deque<String> ad = new ArrayDeque<>(); // [null, null, null, null, null, null, null, null]
            ad.addFirst("Alyssa"); // ["Alyssa", null, null, null, null, null, null, null]
            assertWithMessage("Test 9: This deque should return null.").that(ad.get(-74)).isEqualTo(null);
        }
    }
}
