import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

     @Test
     @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
     void noNonTrivialFields() {
         Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
         List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
     }

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

//     Below, you'll write your own tests for LinkedListDeque.
    @Test
    /** This test whether an empty deque has been constructed successfully and if the size of the deque is correct. */
    public void isEmptyAndSizeTest() {
        /* Test 1: When the object type is integer, we test if the deque is empty and size is 0 by simply initialising it. */
        Deque<Integer> lld1 = new LinkedListDeque<>(); // []
        assertWithMessage("This deque should be empty").that(lld1.isEmpty()).isEqualTo(true);
        assertWithMessage("This deque size should be 0.").that(lld1.size()).isEqualTo(0);

        /* Test 2: When the object type is string, we test if the deque is empty and size is 0 by simply initialising it. */
        Deque<String> lld2 = new LinkedListDeque<>(); // []
        assertWithMessage("This deque should be empty").that(lld2.isEmpty()).isEqualTo(true);
        assertWithMessage("This deque size should be 0.").that(lld2.size()).isEqualTo(0);

        /* Test 3: We pass one item in integer type to see if isEmpty returns false and size is 1. */
        Deque<Integer> lld3 = new LinkedListDeque<>(); // []
        lld3.addLast(25);  // [25]
        assertWithMessage("This deque should not be empty").that(lld3.isEmpty()).isEqualTo(false);
        assertWithMessage("This deque size should be 1.").that(lld3.size()).isEqualTo(1);

        /* Test 4: We pass one item in string type to see if isEmpty returns false and size is 1. */
        Deque<String> lld4 = new LinkedListDeque<>(); // []
        lld4.addFirst("Alyssa"); // ["Alyssa"]
        assertWithMessage("This deque should not be empty").that(lld4.isEmpty()).isEqualTo(false);
        assertWithMessage("This deque size should be 1.").that(lld4.size()).isEqualTo(1);

        /* Test 5: We pass multiple item in integer type to see if isEmpty returns false and size is 4 . */
        lld3.addLast(7); // [25, 7]
        lld3.addFirst(94); // [25, 7, 94]
        lld3.addFirst(93); // [25, 7, 94, 93]
        assertWithMessage("This deque should not be empty").that(lld3.isEmpty()).isEqualTo(false);
        assertWithMessage("This deque size should be 4.").that(lld3.size()).isEqualTo(4);

        /* Test 6: We pass multiple item in string type to see if isEmpty returns false and size is 3. */
        lld4.addFirst("Yoongi"); // ["Alyssa", "Yoongi"]
        lld4.addFirst("Happy"); // ["Alyssa", "Yoongi", "Happy"]
        assertWithMessage("This deque should not be empty").that(lld4.isEmpty()).isEqualTo(false);
        assertWithMessage("This deque size should be 3.").that(lld4.size()).isEqualTo(3);
    }

    @Test
    /** This test checks the function of get(). If a correct item is returned by using get(int i) function. */
    public void getTest() {
        /* Test 1: Initialise an empty deque and let it return the 1st item. */
        Deque<Integer> lld1 = new LinkedListDeque<>(); // []
        assertWithMessage("Test 1: This deque should return null.").that(lld1.isEmpty()).isEqualTo(true);

        /* Test 2: Passes an integer to the deque and let it return the 1st item. */
        lld1.addFirst(3); // [3]
        assertWithMessage("Test 2: This deque should return 3.").that(lld1.get(0)).isEqualTo(3);

        /* Test 3: Passes multiple integers to the deque and let it return the 2nd item. */
        lld1.addFirst(9); // [3, 9]
        lld1.addFirst(93); // [3, 9, 93]
        lld1.addLast(31); // [3, 9, 93, 31]
        assertWithMessage("Test 3: This deque should return 9.").that(lld1.get(1)).isEqualTo(9);

        /* Test 4: let the deque return the 50th item and it should return null. */
        assertWithMessage("Test 4: This deque should return null.").that(lld1.get(49)).isEqualTo(null);

        /* Test 5: Initialise an empty string type deque and let it return the 7st item. */
        Deque<String> lld2 = new LinkedListDeque<>(); // []
        assertWithMessage("Test 5: This deque should return null.").that(lld2.get(6)).isEqualTo(null);

        /* Test 6: Passes a string to the deque and let it return the 1st item. */
        lld2.addFirst("Alyssa"); // ["Alyssa"]
        assertWithMessage("Test 6: This deque should return Alyssa.").that(lld2.get(0)).isEqualTo("Alyssa");

        /* Test 7: Passes multiple strings to the deque and let it return the 3rd item. */
        lld2.addFirst("Yoongi"); // ["Yoongi", "Alyssa"]
        lld2.addFirst("Confidence"); // ["Confidence", "Yoongi", "Alyssa"]
        lld2.addLast("Calm"); // ["Confidence", "Yoongi", "Alyssa", "Calm"]
        assertWithMessage("Test 7: This deque should return 9.").that(lld2.get(2)).isEqualTo("Alyssa");

        /* Test 8: let the deque return the 75th item and it should return null. */
        assertWithMessage("Test 8: This deque should return null.").that(lld2.get(74)).isEqualTo(null);

        /* Test 9: let the deque return the -75th item and it should return null. */
        assertWithMessage("Test 9: This deque should return null.").that(lld2.get(-74)).isEqualTo(null);
    }

    @Test
    /** In this test, we check if this function can return the correct item by passing the parameter - index. */
    public void getRecursiveTest() {
        /* Test 1: Initialise an empty deque and let it return the 1st item. */
        Deque<Integer> lld1 = new LinkedListDeque<>(); // []
        assertWithMessage("Test 1: This deque should return null.").that(lld1.isEmpty()).isEqualTo(true);

        /* Test 2: Passes an integer to the deque and let it return the 1st item. */
        lld1.addFirst(3); // [3]
        assertWithMessage("Test 2: This deque should return 3.").that(lld1.getRecursive(0)).isEqualTo(3);

        /* Test 3: Passes multiple integers to the deque and let it return the 2nd item. */
        lld1.addFirst(9); // [3, 9]
        lld1.addFirst(93); // [3, 9, 93]
        lld1.addLast(31); // [3, 9, 93, 31]
        assertWithMessage("Test 3: This deque should return 9.").that(lld1.getRecursive(1)).isEqualTo(9);

        /* Test 4: let the deque return the 50th item and it should return null. */
        assertWithMessage("Test 4: This deque should return null.").that(lld1.getRecursive(49)).isEqualTo(null);

        /* Test 5: Initialise an empty string type deque and let it return the 7st item. */
        Deque<String> lld2 = new LinkedListDeque<>(); // []
        assertWithMessage("Test 5: This deque should return null.").that(lld2.getRecursive(6)).isEqualTo(null);

        /* Test 6: Passes a string to the deque and let it return the 1st item. */
        lld2.addFirst("Alyssa"); // ["Alyssa"]
        assertWithMessage("Test 6: This deque should return Alyssa.").that(lld2.getRecursive(0)).isEqualTo("Alyssa");

        /* Test 7: Passes multiple strings to the deque and let it return the 3rd item. */
        lld2.addFirst("Yoongi"); // ["Yoongi", "Alyssa"]
        lld2.addFirst("Confidence"); // ["Confidence", "Yoongi", "Alyssa"]
        lld2.addLast("Calm"); // ["Confidence", "Yoongi", "Alyssa", "Calm"]
        assertWithMessage("Test 7: This deque should return 9.").that(lld2.getRecursive(2)).isEqualTo("Alyssa");

        /* Test 8: let the deque return the 75th item and it should return null. */
        assertWithMessage("Test 8: This deque should return null.").that(lld2.getRecursive(74)).isEqualTo(null);

        /* Test 9: let the deque return the -75th item and it should return null. */
        assertWithMessage("Test 9: This deque should return null.").that(lld2.getRecursive(-74)).isEqualTo(null);
    }

    @Test
    /** In this test, we remove the first or last item to see if this function works well. */
    public void removeFirstAndRemoveLast() {
        Deque<Integer>lld1 = new LinkedListDeque<>(); // []
        /* Test 1: Remove the first item in an empty deque. The removeFirst function should return null. */
        lld1.removeFirst();
        assertWithMessage("This deque should be null.").that(lld1.isEmpty()).isEqualTo(true);

        /* Test 2: Remove the last item in an empty deque. The removeFirst function should return null. */
        lld1.removeLast();
        assertWithMessage("This deque should be null.").that(lld1.isEmpty()).isEqualTo(true);

        /* Test 3: Pass multiple integers and remove First. */
        lld1.addFirst(7);
        lld1.addLast(25);
        lld1.addLast(3);
        lld1.addLast(9);
        lld1.addLast(77);
        lld1.addFirst(88);
        lld1.removeFirst();
        assertWithMessage("removeFirst function failed to remove the first item in the deque").
                that(lld1.toList()).containsExactly( 7, 25, 3, 9, 77).inOrder();

        /* Test 4: Remove the last item. */
        lld1.removeLast();
        assertWithMessage("removeLast function failed to remove the last item in the deque").
                that(lld1.toList()).containsExactly(7, 25, 3, 9);

        /* Test 5: Remove the last item three times. */
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        assertWithMessage("removeLast function failed to remove the last item in the deque").
                that(lld1.toList()).containsExactly(7);

        /* Test 6: Remove the first item three times. Keep remove items although the deque is already null. */
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        assertWithMessage("removeFirst function failed to remove the first item in the deque").
                that(lld1.isEmpty()).isEqualTo(true);

        /* Test 7: Add two items and remove the first item. */
        lld1.addLast(23);
        lld1.addFirst(33);
        lld1.removeLast();
        assertWithMessage("removeLast function failed to remove the last item in the deque").
                that(lld1.toList()).containsExactly(33);

    }
}