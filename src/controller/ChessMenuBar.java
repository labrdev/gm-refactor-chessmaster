package controller;

import main.ChessPanel;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;
// -------------------------------------------------------------------------
/**
 * Represents the north menu-bar that contains various controls for the game.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class ChessMenuBar
    extends JMenuBar{
    // ----------------------------------------------------------
    /**
     * Create a new ChessMenuBar object.
     */
    public ChessMenuBar(){
        String[] menuCategories = { "File", "Options", "Help" };
        String[] menuItemLists =
        { "New game/restart,Exit", "Toggle graveyard,Toggle game log",
          "About" };
        for ( int i = 0; i < menuCategories.length; i++ ){
            JMenu currMenu = new JMenu( menuCategories[i] );
            String[] currMenuItemList = menuItemLists[i].split( "," );
            for (String currMenuItemList1 : currMenuItemList) {
                JMenuItem currItem = new JMenuItem(currMenuItemList1);
                currItem.addActionListener( new MenuListener() );
                currMenu.add( currItem );
            }
            this.add( currMenu );
        }
    }
    /**
     * Listener for the north menu bar.
     *
     * @author Ben Katz (bakatz)
     * @author Myles David II (davidmm2)
     * @author Danielle Bushrow (dbushrow)
     * @version 2010.11.17
     */
    private class MenuListener
        implements ActionListener
    {
        /**
         * Takes an appropriate action based on which menu button is clicked
         *
         * @param event
         *            the mouse event from the source
         */
        @Override
        public void actionPerformed( ActionEvent event ){
            String buttonName = ( (JMenuItem)event.getSource() ).getText();
            switch (buttonName) {
                case "About":
                    aboutHandler();
                    break;
                case "New game/restart":
                    restartHandler();
                    break;
                case "Toggle game log":
                    toggleGameLogHandler();
                    break;
                case "Exit":
                    exitHandler();
                    break;
                default:
                    toggleGraveyardHandler();
                    break;
            }
        }
    }
    // ----------------------------------------------------------
    /**
     * Takes an appropriate action if the about button is clicked.
     */
    private void aboutHandler(){
        JOptionPane.showMessageDialog(
            this.getParent(),
            "YetAnotherChessGame v1.0 by:\nBen Katz\nMyles David\n"
                + "Danielle Bushrow\n\nFinal Project for CS2114 @ VT" );
    }
    /**
     * Takes an appropriate action if the restart button is clicked.
     */
    private void restartHandler(){
        ( (ChessPanel)this.getParent() ).getGameEngine().reset();
    }
    /**
     * Takes an appropriate action if the exit button is clicked.
     * Uses Tony Allevato's code for exiting a GUI app without System.exit()
     * calls.
     */
    private void exitHandler(){
        JOptionPane.showMessageDialog( this.getParent(), "Thanks for leaving"
            + ", quitter! >:(" );
        Component possibleFrame = this;
        while ( possibleFrame != null && !( possibleFrame instanceof JFrame ) ){
            possibleFrame = possibleFrame.getParent();
        }
        JFrame frame = null;
        if (possibleFrame instanceof JFrame) {
            frame = (JFrame) possibleFrame;
        }
        if (frame != null) {
            frame.setVisible(false);
            frame.dispose();
        }
    }
    /**
     * Takes an appropriate action if the toggle graveyard button is clicked.
     */
    private void toggleGraveyardHandler(){
        ( (ChessPanel)this.getParent() ).getGraveyard( 1 ).setVisible(
            !( (ChessPanel)this.getParent() ).getGraveyard( 1 ).isVisible() );
        ( (ChessPanel)this.getParent() ).getGraveyard( 2 ).setVisible(
            !( (ChessPanel)this.getParent() ).getGraveyard( 2 ).isVisible() );
    }
    /**
     * Takes an appropriate action if the toggle game log button is clicked.
     */
    private void toggleGameLogHandler(){
        ( (ChessPanel)this.getParent() ).getGameLog().setVisible(
            !( (ChessPanel)this.getParent() ).getGameLog().isVisible() );
        ( (ChessPanel)this.getParent() ).revalidate();
    }
}
