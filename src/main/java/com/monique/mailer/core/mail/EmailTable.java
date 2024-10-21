package com.monique.mailer.core.mail;

import java.util.ArrayList;
import java.util.Arrays;

public class EmailTable {
    private String remetente;
    private ArrayList<String> destinatarios = new ArrayList<String>();
    private String assunto;
    private String texto;

    public EmailTable() { }

    public EmailTable(String r, String a, String t, String... d) {
        remetente = r;
        setDestinatarios(d);
        assunto = a;
        texto = t;
    }

    public EmailTable(String r, String a, String t) {
        remetente = r;
        assunto = a;
        texto = t;
    }

    public boolean isValid() {
        if (!remetente.isBlank() && remetente.trim().contains("@") && isDestinataryValid() && !assunto.isBlank() && !texto.isBlank()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDestinataryValid() {
        if (destinatarios.isEmpty()) return false;
        for (String email : destinatarios) {
            if (!email.contains("@")) {
                return false;
            }
        }
        return true;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public ArrayList<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(ArrayList<String> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public void setDestinatarios(String... destinatarios) {
        this.destinatarios = new ArrayList<String>(Arrays.asList(destinatarios));
        this.destinatarios.removeIf((String e) -> {
            if (e.isBlank()) return true;
            else return false;
        });
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
