package jaunty.copydetect.swing;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * @author hellojinjie
 * @version May 22, 2010 11:46:24 PM
 */
public class GUITest {

    public static void main(String[] args) {
        JOptionPane pane = new JOptionPane("fsdfsd");
        JDialog dialog = pane.createDialog(pane, "hello");
        dialog.setVisible(true);
    }
}
