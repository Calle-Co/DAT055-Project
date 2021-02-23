package global;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;


/**
 * En klass för applikationens alla knappar.
 * Knapparna kommer i tre olika storlekar, small, medium och large.
 * Om man hovrar över knapparna så uppstår en liten effekt.
 * @author Carl Classon
<<<<<<< Updated upstream
 * @version 2021-02-
=======
 * @version 2021-02-16
>>>>>>> Stashed changes
 */
@SuppressWarnings("serial")
public class AllButtons extends JButton{
    
    public enum size{
        SMALL,
        MEDIUM,
        LARGE
    }

    /**
     * A method for creating specific buttons. The buttons have a hover-effect when you mouse 
     * over them.
     * 
     * En metod för att skapa specifika knappar. Knapparna har en liten effekt när man 
     * för muspekaren över dem.
     * 
     * @param s Storleken du vill ha på din knapp, kan vara någon av : SMALL, MEDIUM, LARGE.
     * @param title Texten du vill ha på din knapp
     */

    public AllButtons(size s, String title){
        
        super(title);

        if(s == size.SMALL){
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(209,237,242,200));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setBackground(UIManager.getColor("control"));
                }
            });
            setPreferredSize(new Dimension(80,40));
            setFont(new Font("Basic", Font.BOLD,15));
        }
        else if(s == size.MEDIUM){
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(209,237,242,200));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setBackground(UIManager.getColor("control"));
                }
            });
            setPreferredSize(new Dimension(140,60));
            setFont(new Font("Arial", Font.BOLD,25));

        }
        else if(s == size.LARGE){
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(209,237,242,200));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setBackground(UIManager.getColor("control"));
                }
            });
            setPreferredSize(new Dimension(240, 80));
            setFont(new Font("Basic", Font.BOLD,45));
        }
    }

}
