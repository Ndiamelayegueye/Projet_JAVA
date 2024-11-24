/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueCalculs {
    private List<String> historique;

    public HistoriqueCalculs() {
        this.historique = new ArrayList<>();
    }

    public void ajouterCalcul(String calcul) 
    {
        historique.add(calcul);
    }

    public void sauvegarderHistorique(String cheminFichier) throws IOException 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, true))) 
        {  // 'true' pour ajouter au fichier
            for (String calcul : historique) {
                writer.write(calcul);
                writer.newLine();
                writer.write("\n");
            }
        }
    }

    public List<String> getHistorique() {
        return historique;
    }
}