import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CalculateurReseauSwingApp extends UIComposant {
    private HistoriqueCalculs historique;

    public CalculateurReseauSwingApp() {
        this.historique = new HistoriqueCalculs();
        initialiserUI();
        afficher();
    }

    @Override
    protected void initialiserUI() 
    {
        // Configuration du cadre principal
        frame = new JFrame("Calculateur d'Adresse Réseau");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10)); // Espacement entre les panneaux

        // Création des champs de saisie et labels
        champAdresseIP = new JTextField(15);
        champMasque = new JTextField(15);

        JPanel panelChamps = new JPanel(new GridLayout(2, 2, 5, 5)); // Grille pour les champs
        panelChamps.add(new JLabel("Adresse IP :"));
        panelChamps.add(champAdresseIP);
        panelChamps.add(new JLabel("Masque :"));
        panelChamps.add(champMasque);

        // Zone pour les résultats
        zoneResultats = new JTextArea(10, 40);
        zoneResultats.setEditable(false);

        // Bouton Calculer
        JButton boutonCalculer = new JButton("Calculer");
        boutonCalculer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String adresse = champAdresseIP.getText();
                    String masque = champMasque.getText();

                    // Logique de calcul du réseau
                    Reseau reseau = new Reseau(adresse, masque);
                    String resultats = "Adresse : " + adresse +
                        "\nClasse : " + reseau.getClasseActuelle() +    
                        "\nMasque en bits : " + reseau.getLongueurMasque() +
                        "\nPlage : " + reseau.getAdresseDebut() + " - " + reseau.getAdresseFin();

                    // Affichage des résultats
                    mettreAJourResultats(resultats);
                    
                    // Ajouter à l'historique
                    HistoriqueCalculs hc = new HistoriqueCalculs();
                    hc.ajouterCalcul(resultats);

                    // Enregistrer dans un fichier
                    String nameFichier = "Historique.txt";
                    hc.sauvegarderHistorique(nameFichier);

                } catch (InvalidIPException ex) {
                    // Gestion des erreurs liées à l'adresse IP
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    
                } catch (IOException ex) {
                    // Gestion des erreurs liées à la sauvegarde dans un fichier
                    JOptionPane.showMessageDialog(frame, "Erreur lors de la sauvegarde de l'historique : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Bouton pour sauvegarder l'historique
        JButton boutonSauvegarder = new JButton("Sauvegarder");
        boutonSauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String nameFichier = "Historique.txt";
                // historique.sauvegarderHistorique(nameFichier);

                // Confirmation de sauvegarde
                JOptionPane.showMessageDialog(frame, "Historique sauvegardé avec succès dans : " + nameFichier, "Succès", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });


        // Panneau pour les boutons
        JPanel panelBoutons = new JPanel();
        panelBoutons.add(boutonCalculer);
        panelBoutons.add(boutonSauvegarder);

        // Ajout des composants au panneau principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.add(panelChamps, BorderLayout.NORTH);
        panelPrincipal.add(panelBoutons, BorderLayout.SOUTH);  // Ajout du panneau de boutons en bas

        // Ajout des panneaux au cadre principal
        frame.add(panelPrincipal, BorderLayout.NORTH);
        frame.add(new JScrollPane(zoneResultats), BorderLayout.CENTER);

        frame.pack(); // Ajuste automatiquement la taille du cadre
    }

    @Override
    protected void mettreAJourResultats(String resultats) {
        zoneResultats.setText(resultats);
    }
}