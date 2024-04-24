package casino.games.blackjack;

public class Card {

    /*************************************************************************/
    private Suit id_suit;

    private int id_number;

    private Joker id_joker;

    /*************************************************************************/

    public enum Suit {
        Clubs, Diamonds, Hearts, Spades, Joker
    }

    public enum Joker {
        Red, Black, Null,
    }

    /*************************************************************************/

    public Card(int number, Suit suit) {
        this.id_number = number;
        this.id_suit = suit;
    }

    public Card(Joker joker){
        this.id_number = 0;
        this.id_joker = joker;
        this.id_suit = Suit.Joker;
    }

    /*************************************************************************/
    public int getNumber() { return id_number; }

    public Suit getSuit() { return id_suit; }

    public boolean isJoker() { return id_suit == Suit.Joker; }

    public Joker getJokerColor(){
        if(id_suit == Suit.Joker){
            return id_joker;
        } else {
            return Joker.Null;
        }
    }
    /*************************************************************************/

    public String getCardName(){
        String name = "";
        if(id_suit == Suit.Joker){
            name += id_joker.toString() + " Joker";
        }
        else {
            if(id_number == 1){
                name += "Ace";
            }

            else if(id_number <= 10){
                name += id_number;
            } else {
                if(id_number == 11) {
                    name += "Jack";
                }
                else if(id_number == 12) {
                    name += "Queen";
                }
                else if(id_number == 13) {
                    name += "King";
                }
            }
            name += " of " + id_suit;
        }
        return name;
    }
}
