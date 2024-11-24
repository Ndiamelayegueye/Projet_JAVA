public class Reseau extends AdresseIP {
    private int[] masque;
    private String adresseDebut;
    private String adresseFin;
    
 

    public Reseau(String adresse, String masque) throws InvalidIPException {
        super(adresse);
        this.masque = validerAdresse(masque);
        calculerPlageAdresse();
    }

    // public String getMasqueEnBits() 
    // {
    //     StringBuilder bits = new StringBuilder();
    //     for (int octet : masque) {
    //         bits.append(String.format("%8s", Integer.toBinaryString(octet)).replace(' ', '0'));
    //     }
    //     return bits.toString();
    // }
    public String getMasqueEnBits() {
        StringBuilder bits = new StringBuilder();
        for (int octet : masque) {
            bits.append(String.format("%8s", Integer.toBinaryString(octet)).replace(' ', '0'));
        }
        return bits.toString();
    }
    
    public int getLongueurMasque() {
        int longueur = 0;
        for (int octet : masque) 
        {
            longueur += Integer.bitCount(octet);
        }
        return longueur;
    }
    

    private void calculerPlageAdresse()
    {
        int[] ip = getOctets();
        int[] debut = new int[4];
        int[] fin = new int[4];

        for (int i = 0; i < 4; i++) {
            debut[i] = ip[i] & masque[i];
            fin[i] = ip[i] | (~masque[i] & 0xFF);
        }

        adresseDebut = convertirEnString(debut);
        adresseFin = convertirEnString(fin);
    }

    private String convertirEnString(int[] octets) {
        return octets[0] + "." + octets[1] + "." + octets[2] + "." + octets[3];
    }

    public String getAdresseDebut() {
        return adresseDebut;
    }

    public String getAdresseFin() {
        return adresseFin;
    }
}

