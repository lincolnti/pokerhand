package br.com.pokerhand.lincoln;

import br.com.pokerhand.lincoln.domain.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest {

    @Test
    public void isRoyalFlush() {
        // given
        String cards = "AC KC QC JC TC";

        // when
        Boolean result = Category.isRoyalFlush(Arrays.asList(cards.split(" ")));

        // then
        Assert.assertEquals(true, result);
    }

    @Test
    public void isStraight() {
        // given
        String cards = "5S 6S 7S 8S 9S";

        // when
        Boolean result = Category.isStraight(Arrays.asList(cards.split(" ")));

        // then
        Assert.assertEquals(true, result);
    }

    @Test
    public void isFourOfaKind() {
        // given
        String cards = "8S AS AS AS AS";

        // when
        Boolean result = Category.isFourOfAKind(Arrays.asList(cards.split(" ")));

        // then
        Assert.assertEquals(true, result);
    }

    @Test
    public void isFullHouse() {
        // given
        String cards = "10S 10S 10S AS AS";

        // when
        Boolean result = Category.isFullHouse(Arrays.asList(cards.split(" ")));

        // then
        Assert.assertEquals(true, result);
    }

    @Test
    public void isFlush() {
        // given
        String cards = "10S 10S 10S AS AS";

        // when
        Boolean result = Category.isFlush(Arrays.asList(cards.split(" ")));

        // then
        Assert.assertEquals(true, result);
    }
}
