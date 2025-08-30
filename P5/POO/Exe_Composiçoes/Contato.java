package Exe_Composiçoes;

public class Contato {
    private String telefoneResid;
    private String celular;
    private String email;

    public Contato(String telefoneResid, String celular, String email) {
        this.telefoneResid = telefoneResid;
        this.celular = celular;
        this.email = email;
    }

    // Getters
    public String getTelefoneResid() {
        return telefoneResid;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    // Setters (if needed for modification)
    // public void setTelefoneResid(String telefoneResid) { this.telefoneResid = telefoneResid; }
    // ...

    @Override
    public String toString() {
        return String.format("Tel. Residencial: %s\nCelular: %s\nEmail: %s",
                telefoneResid, celular, email);
    }
}
