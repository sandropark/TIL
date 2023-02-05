package list.doublylinkedlist.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DoublyLinkedListTest {

    DoublyLinkedList numbers;

    @BeforeEach
    void setUp() {
        numbers = new DoublyLinkedList();
    }

    @DisplayName("맨 앞에 노드를 추가한다.")
    @Test
    void addFirst() throws Exception {
        numbers.addFirst(20);
        assertThat(numbers.length()).isEqualTo(1);
        assertThat(numbers.get(0)).isEqualTo(20);

        numbers.addFirst(10);
        assertThat(numbers.length()).isEqualTo(2);
        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(20);
    }

    @DisplayName("맨 뒤에 노드를 추가한다.")
    @Test
    void addLast() throws Exception {
        numbers.addLast(10);
        assertThat(numbers.length()).isEqualTo(1);
        assertThat(numbers.get(0)).isEqualTo(10);

        numbers.addLast(20);
        assertThat(numbers.length()).isEqualTo(2);
        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(20);
    }

    @DisplayName("해당 인덱스에 노드를 추가한다.")
    @Test
    void add() throws Exception {
        numbers.add(0, 10);
        assertThat(numbers.length()).isEqualTo(1);
        assertThat(numbers.get(0)).isEqualTo(10);

        numbers.add(1, 20);
        assertThat(numbers.length()).isEqualTo(2);
        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(20);

        numbers.add(1, 15);
        assertThat(numbers.length()).isEqualTo(3);
        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(15);
        assertThat(numbers.get(2)).isEqualTo(20);
    }

    @DisplayName("해당 인덱스의 노드를 반환한다. 인덱스에 따라서 탐색 시작시점이 달라진다.")
    @Test
    void node() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        assertThat(numbers.node(0).getValue()).isEqualTo(10);
        assertThat(numbers.node(2).getValue()).isEqualTo(30);
    }

    @DisplayName("처음 노드를 삭제한다.")
    @Test
    void removeFirst() throws Exception {
        assertThatThrownBy(numbers::removeFirst)
                .isInstanceOf(IllegalStateException.class);

        numbers.addLast(10);
        numbers.addLast(20);

        assertThat(numbers.removeFirst()).isEqualTo(10);
        assertThat(numbers.length()).isEqualTo(1);

        assertThat(numbers.removeFirst()).isEqualTo(20);
        assertThat(numbers.length()).isEqualTo(0);

        assertThatThrownBy(numbers::removeFirst)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("마지막 노드를 삭제한다.")
    @Test
    void removeLast() throws Exception {
        assertThatThrownBy(numbers::removeLast)
                .isInstanceOf(IllegalStateException.class);

        numbers.addLast(10);
        numbers.addLast(20);

        assertThat(numbers.removeLast()).isEqualTo(20);
        assertThat(numbers.length()).isEqualTo(1);
        assertThat(numbers.toString()).isEqualTo("[10]");

        assertThat(numbers.removeLast()).isEqualTo(10);
        assertThat(numbers.length()).isEqualTo(0);
        assertThat(numbers.toString()).isEqualTo("[]");

        assertThatThrownBy(numbers::removeLast)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("해당 인덱스의 노드를 삭제한다.")
    @Test
    void remove() throws Exception {
        assertThatThrownBy(() -> numbers.remove(0))
                .isInstanceOf(IllegalStateException.class);

        numbers.addFirst(30);
        numbers.addFirst(20);
        numbers.addFirst(10);

        assertThat(numbers.remove(1)).isEqualTo(20);
        assertThat(numbers.length()).isEqualTo(2);
        assertThat(numbers.toString()).isEqualTo("[10,30]");
    }

    @DisplayName("iterator를 사용해서 노드를 순회한다.")
    @Test
    void iterator() throws Exception {
        numbers.addFirst(30);
        numbers.addFirst(20);
        numbers.addFirst(10);

        DoublyLinkedList.ListIterator i = numbers.iterator();

        assertThat(i.next()).isEqualTo(10);
        assertThat(i.next()).isEqualTo(20);
        assertThat(i.next()).isEqualTo(30);
        assertThatThrownBy(i::next).isInstanceOf(IllegalStateException.class);

        assertThat(i.previous()).isEqualTo(30);
        assertThat(i.previous()).isEqualTo(20);
        assertThat(i.previous()).isEqualTo(10);
        assertThatThrownBy(i::previous).isInstanceOf(IllegalStateException.class);

        assertThat(i.next()).isEqualTo(10);
        assertThat(i.next()).isEqualTo(20);
        assertThat(i.next()).isEqualTo(30);
        assertThatThrownBy(i::next).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("iterator - 이전 노드가 없으면 false를 반환한다.")
    @Test
    void iterator_hasPrevious_false() throws Exception {
        DoublyLinkedList.ListIterator i = numbers.iterator();

        assertThat(i.hasPrevious()).isFalse();
    }

    @DisplayName("iterator - 이전 노드가 있으면 true를 반환한다.")
    @Test
    void iterator_hasPrevious_true() throws Exception {
        numbers.addFirst(10);

        DoublyLinkedList.ListIterator i = numbers.iterator();

        i.next();
        assertThat(i.hasPrevious()).isTrue();
    }

    @DisplayName("iterator - 요소를 추가한다.")
    @Test
    void iterator_add() throws Exception {
        DoublyLinkedList.ListIterator i = numbers.iterator();

        i.add(10);
        i.add(20);

        assertThat(numbers.length()).isEqualTo(2);
        assertThat(numbers.toString()).isEqualTo("[20,10]");

        assertThat(i.next()).isEqualTo(20);
        assertThat(i.next()).isEqualTo(10);

        i.add(30);
        assertThat(numbers.length()).isEqualTo(3);
        assertThat(numbers.toString()).isEqualTo("[20,10,30]");

        i.add(40);
        assertThat(numbers.length()).isEqualTo(4);
        assertThat(numbers.toString()).isEqualTo("[20,10,40,30]");

        assertThat(i.previous()).isEqualTo(10);
        assertThat(i.previous()).isEqualTo(20);
    }

}