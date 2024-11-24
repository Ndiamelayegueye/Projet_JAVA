/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */

   public class AdresseIP {
    private int[] octets;
   private char classe;

    public AdresseIP(String adresse) throws InvalidIPException {
        this.octets = validerAdresse(adresse);
        this.classe = getClasse();
    }
    
   
            
    public int[] validerAdresse(String adresse) throws InvalidIPException {
        String[] parties = adresse.split("\\.");
        if (parties.length != 4) {
            throw new InvalidIPException("Adresse IP invalide : elle doit contenir 4 octets.");
        }

        int[] octets = new int[4];
        for (int i = 0; i < 4; i++) {
            try {
                octets[i] = Integer.parseInt(parties[i]);
                if (octets[i] < 0 || octets[i] > 255) {
                    throw new InvalidIPException("Octet hors plage (0-255) : " + octets[i]);
                }
            } catch (NumberFormatException e) {
                throw new InvalidIPException("Octet non valide : " + parties[i]);
            }
        }
    
        return octets;
    }

    public char getClasse() 
{        int premierOctet = this.octets[0];
        if (premierOctet >= 1 && premierOctet <= 126) return 'A';
        if (premierOctet >= 128 && premierOctet <= 191) return 'B';
        if (premierOctet >= 192 && premierOctet <= 223) return 'C';
        if (premierOctet >= 224 && premierOctet <= 239) return 'D';
        return 'E';
    }

    public int[] getOctets() {
        return this.octets;
    }

    public char getClasseActuelle() {
        return this.classe;
    }
}

