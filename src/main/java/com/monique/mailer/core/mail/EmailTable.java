package com.monique.mailer.core.mail;

import java.util.ArrayList;
import java.util.Arrays;

public class EmailTable {
    private String sender;
    private ArrayList<String> recipients = new ArrayList<String>();
    private String subject;
    private String text;

    public EmailTable() { }

    public EmailTable(String sender, String subject, String text, String... recipients) {
        this.sender = sender;
        setRecipients(recipients);
        this.subject = subject;
        this.text = text;
    }

    public EmailTable(String sender, String subject, String text) {
        this.sender = sender;
        this.subject = subject;
        this.text = text;
    }

    public boolean isValid() {
        if (!sender.isBlank() && sender.trim().contains("@") && isRecipientValid() && !subject.isBlank() && !text.isBlank()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRecipientValid() {
        if (recipients.isEmpty()) return false;
        for (String email : recipients) {
            if (!email.contains("@")) {
                return false;
            }
        }
        return true;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ArrayList<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(ArrayList<String> recipients) {
        this.recipients = recipients;
    }

    public void setRecipients(String... recipients) {
        this.recipients = new ArrayList<String>(Arrays.asList(recipients));
        this.recipients.removeIf((String e) -> {
            if (e.isBlank()) return true;
            else return false;
        });
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
