import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static java.lang.Integer.parseInt;

/**
 * Write a description of class GraphicalTable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
class Table
{
    protected Player player;
    protected Dealer dealer;
    protected int bet = 0;
    protected int pot;
    private JFrame frame;
    private JTextField input;
    private JButton check;
    private JButton fold;
    private JButton betButton;
    private JLabel dealerStash;

    private JLabel playerStash;

    private JLabel result;
    private JLabel potLabel;
    private JPanel dealerHand;
    private JPanel playerHand;

    private JPanel center;


    private JDialog contPrompt;

    public Table()
    {
        pot = 0;
        player = new Player();
        // Create the dealer with 5x the money of the player
        dealer = new Dealer(player.getStash() * 5, true);

        makeFrame();

    }

    public void makeFrame()
    {
        frame = new JFrame("Blackjack");
        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new BorderLayout(3,3));

        JPanel west = new JPanel();
        west.setLayout(new BoxLayout(west, 3));
        contentPane.add(west, BorderLayout.WEST);

        JPanel east = new JPanel();
        east.setLayout(new BoxLayout(east, 3));
        contentPane.add(east, BorderLayout.EAST);

        JPanel north = new JPanel();
        north.setLayout(new FlowLayout());
        contentPane.add(north, BorderLayout.NORTH);

        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        contentPane.add(south, BorderLayout.SOUTH);

        center = new JPanel();
        center.setLayout(new GridLayout(1,2));
        contentPane.add(center, BorderLayout.CENTER);

        JLabel playerName = new JLabel(player.getName());
        west.add(playerName, BorderLayout.WEST);
        playerStash = new JLabel("Stash: " + player.getStash());
        west.add(playerStash, BorderLayout.WEST);


        JLabel dealerName = new JLabel("Dealer");
        east.add(dealerName, BorderLayout.EAST);
        dealerStash = new JLabel("Stash: " + dealer.getStash());
        east.add(dealerStash, BorderLayout.EAST);


        betButton = new JButton("Bet");
        south.add(betButton, BorderLayout.SOUTH);
        betButton.addActionListener(e -> getBets());
        input = new JTextField(5);
        south.add(input, BorderLayout.SOUTH);
        check = new JButton("Check");
        south.add(check, BorderLayout.SOUTH);
        check.addActionListener(e -> check());
        fold = new JButton("Fold");
        south.add(fold, BorderLayout.SOUTH);
        fold.addActionListener(e -> fold());

        potLabel = new JLabel("Pot total: " + getPot());
        north.add(potLabel, BorderLayout.NORTH);
        result = new JLabel();
        north.add(result, BorderLayout.NORTH);

        playerHand = new JPanel();
        playerHand.setLayout(new FlowLayout());
        center.add(playerHand, BorderLayout.CENTER);


        JLabel playerHandDes = new JLabel(player.getName() + "'s hand");
        playerHand.add(playerHandDes, BorderLayout.CENTER);

        dealerHand = new JPanel();
        dealerHand.setLayout(new FlowLayout());
        center.add(dealerHand, BorderLayout.CENTER);

        JLabel dealerHandDes = new JLabel("Dealer's Hand");
        dealerHand.add(dealerHandDes, BorderLayout.CENTER);



        check.setEnabled(false);
        fold.setEnabled(false);

        frame.pack();
        frame.setVisible(true);
    }

    private void setLayouts(Container contentPane)
    {
        contentPane.setLayout(new BorderLayout());

        JPanel west = new JPanel();
        west.setLayout(new BoxLayout(west, 3));
        contentPane.add(west, BorderLayout.WEST);

        JPanel east = new JPanel();
        west.setLayout(new BoxLayout(east, 3));
        contentPane.add(east, BorderLayout.EAST);

        JPanel north = new JPanel();
        west.setLayout(new FlowLayout());
        contentPane.add(north, BorderLayout.NORTH);

        JPanel south = new JPanel();
        west.setLayout(new FlowLayout());
        contentPane.add(south, BorderLayout.SOUTH);

        JPanel center = new JPanel();
        west.setLayout(new GridLayout(2,1));
        contentPane.add(center, BorderLayout.CENTER);
    }

    private void updatePlayerLabels()
    {
        playerStash.setText("Stash: " + player.getStash());

        dealerStash.setText("Stash: " + dealer.getStash());

        dealerHand.removeAll();
        dealerHand.add(new JLabel("Dealer's Hand"),BorderLayout.CENTER);
        for(Card card : dealer.getHand())
        {
            dealerHand.add(new JLabel(new ImageIcon(card.getGraphic())), BorderLayout.CENTER);
        }

        playerHand.removeAll();
        playerHand.add(new JLabel(player.getName() + "'s Hand"),BorderLayout.CENTER);
        for(Card card : player.getHand())
        {
            playerHand.add(new JLabel(new ImageIcon(card.getGraphic())), BorderLayout.CENTER);
        }
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /*
     * Get a valid bet from the player, and match it or go all-in by the dealer
     */
    private void getBets()
    {
        // Get the bet for this hand from the player.  Validate, and take from player's stash and put in the pot
        if(input.getText() != null)
        {

            Integer bets = parseInt(input.getText());
            bet = bets;
            pot = bet;
            if(bet > player.getStash())
            {
                result.setText("You don't have enough money! Try again.");
            }
            else if(bet <= 0)
            {
                result.setText("Bets must be a positive value! Try again.");
            }
            player.setStash(player.getStash() - bet);



            // Dealer will match the bet or go all-in if not enough
            if (dealer.getStash() >= bet)
            {
                pot += bet;
                dealer.setStash(dealer.getStash() - bet);
            }
            else
            {
                pot += dealer.getStash();
                dealer.setStash(0);
            }
            potLabel.setText("Pot Total: " + pot);
            frame.repaint();

            Card dealtCard = dealer.deal();
            dealer.receiveCard(dealtCard, true);
            dealerHand.add(new JLabel(new ImageIcon(dealtCard.getGraphic())));


            dealtCard = dealer.deal();
            player.receiveCard(dealtCard, true);
            playerHand.add(new JLabel(new ImageIcon(dealtCard.getGraphic())));


            updatePlayerLabels();

            pause();

            check.setEnabled(true);
            fold.setEnabled(true);

            if(player.getHand().size() == 5)
            {
                scoreGame();
            }
        }
        else
        {

        }
    }

    private void check()
    {
        Card dealtCard = dealer.deal();
        player.receiveCard(dealtCard, true);
        playerHand.add(new JLabel(new ImageIcon(dealtCard.getGraphic())));

        dealtCard = dealer.deal();
        dealer.receiveCard(dealtCard, true);
        dealerHand.add(new JLabel(new ImageIcon(dealtCard.getGraphic())));

        updatePlayerLabels();


        frame.repaint();

        check.setEnabled(true);
        fold.setEnabled(true);

        pause();

        if(player.getHand().size() == 5)
        {
            scoreGame();
        }
    }

    private void fold()
    {
        result.setText("Fold!");
        dealer.setStash(dealer.getStash() + pot);
        reset();
    }

    /*
     * Score the game based on the two player's hands and declare a winner
     */
    private void scoreGame()
    {
        dealer.showAllCards();
        updatePlayerLabels();
        pause();
        // Compare both player's score and declare winner.  Award pot to winner and clear player's hands
        if (player.scoreHand() < dealer.scoreHand())
        {
            result.setText("Dealer Wins!");

            dealer.setStash(dealer.getStash() + pot);
        }
        else if (player.scoreHand() > dealer.scoreHand())
        {
            result.setText("Player Wins!");

            player.setStash(player.getStash() + pot);
        }
        else
        {
            result.setText("Tie!?");

            // Return money to both players
            if (pot == bet * 2)
            {
                player.setStash(player.getStash() + bet);
                dealer.setStash(dealer.getStash() + bet);
            }
            else // Dealer went all-in
            {
                player.setStash(player.getStash() + bet);
                dealer.setStash(dealer.getStash() + (pot - bet));
            }
        }
        updatePlayerLabels();
        pause();
        pause();
        pause();
        reset();
    }

    private void reset()
    {

        JOptionPane.showMessageDialog(frame, "Continue?");


        pot = 0;
        potLabel.setText("Pot Total: " + pot);
        frame.repaint();

        for(int i = 0; i < 2; i++)
        {
            pause();
        }

        playerHand.removeAll();

        JLabel playerHandDes = new JLabel(player.getName() + "'s hand");
        playerHand.add(playerHandDes, BorderLayout.CENTER);

        dealerHand.removeAll();

        JLabel dealerHandDes = new JLabel("Dealer's Hand");
        dealerHand.add(dealerHandDes, BorderLayout.CENTER);

        player.clearHand();
        dealer.clearHand();
        dealer.resetDeck();

        betButton.setEnabled(true);
        check.setEnabled(false);
        fold.setEnabled(false);
        updatePlayerLabels();



    }

    private void quit()
    {
        Runtime.getRuntime().exit(0);
    }

    private void cont()
    {
        contPrompt.setVisible(false);
        contPrompt.setModal(false);
    }

    /*
     * Pause for 2 seconds
     */
    protected static void pause()
    {
        try
        {
            Thread.sleep(500);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    private int getPot()
    {
        return pot;
    }


}