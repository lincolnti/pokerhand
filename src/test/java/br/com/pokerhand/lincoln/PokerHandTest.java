package br.com.pokerhand.lincoln;

import br.com.pokerhand.lincoln.domain.PokerHand;
import br.com.pokerhand.lincoln.enumerateds.Result;
import br.com.pokerhand.lincoln.exceptions.InvalidCardException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PokerHandTest {

    @Test
    public void shouldBeWIN() throws InvalidCardException {
        // given
        PokerHand pokerHand1 = new PokerHand("AC KC QC JC TC");
        PokerHand pokerHand2 = new PokerHand("9C 9H 5C 5H AC");

        // when
        Result result = pokerHand1.compareWith(pokerHand2);

        // then
        Assert.assertEquals(Result.WIN, result);
    }

    @Test
    public void shouldBeLOSS() throws InvalidCardException {
        // given
        PokerHand pokerHand1 = new PokerHand("TS TD KC JC 7C");
        PokerHand pokerHand2 = new PokerHand("JS JC AS KC TD");

        // when
        Result result = pokerHand1.compareWith(pokerHand2);

        // then
        Assert.assertEquals(Result.LOSS, result);
    }

    @Test
    public void shouldBeDRAW() throws InvalidCardException {
        // given
        PokerHand pokerHand1 = new PokerHand("2S 3H 4H 5H 6D");
        PokerHand pokerHand2 = new PokerHand("2S 3H 4D 5H 6C");

        // when
        Result result = pokerHand1.compareWith(pokerHand2);

        // then
        Assert.assertEquals(Result.DRAW, result);
    }
}
