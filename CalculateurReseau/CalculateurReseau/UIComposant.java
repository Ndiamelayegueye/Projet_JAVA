/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
import javax.swing.*;

public abstract class UIComposant {
    protected JFrame frame;
    protected JTextField champAdresseIP, champMasque;
    protected JTextArea zoneResultats;

    public UIComposant() {
        frame = new JFrame("Calculateur de Réseau");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
    }

    protected abstract void initialiserUI();
    protected abstract void mettreAJourResultats(String resultats);

    public void afficher() {
        frame.setVisible(true);
    }
}

