package com.monique.mailer.core.mail;

import java.util.ArrayList;
import java.util.Arrays;

public class EmailTable {
    private String senderName;
    private String senderAddress;
    private ArrayList<String> recipients = new ArrayList<String>();
    private String subject;
    private String text;

    public EmailTable() {}

    /**
     * @param senderName Name from
     * @param senderAddress Email from
     * @param subject Title
     * @param text Text/HTML
     * @param recipients Emails to
     */
    public EmailTable(String senderName, String senderAddress, String subject, String text, String... recipients) {
        this.senderName = senderName.trim();
        this.senderAddress = senderAddress.trim();
        setRecipients(recipients);
        this.subject = subject.trim();
        this.text = text;
    }

    /**
     * @param senderName Name from
     * @param senderAddress Email from
     * @param subject Title
     * @param text Text/HTML
     */
    public EmailTable(String sender, String senderAddress, String subject, String text) {
        this.senderName = sender.trim();
        this.senderAddress = senderAddress.trim();
        this.subject = subject.trim();
        this.text = text;
    }

    public boolean isValid() {
        if (!senderName.isBlank() && senderAddress.contains("@") && isRecipientValid() && !subject.isBlank() && !text.isBlank()) {
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName.trim();
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress.trim();
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
        this.subject = subject.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
