public class CalculateurReseau
{
    public static void main(String[] args) 
    {
        try {
            // Exemple d'adresse IP et de masque
            Reseau reseau = new Reseau("192.168.1.10", "255.255.255.0");

            // Afficher les résultats
            System.out.println("Masque en bits: " + reseau.getMasqueEnBits());
            System.out.println("Adresse début: " + reseau.getAdresseDebut());
            System.out.println("Adresse fin: " + reseau.getAdresseFin());
        } catch (InvalidIPException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}

