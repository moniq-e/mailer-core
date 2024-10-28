package com.monique.mailer.core.mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailSender {
    private String hostName;
    private String sslSmtpPort;
    private String authUsername;
    private String authPassword;
    private boolean startTLSRequired;
    private boolean sslOnConnect;
    
    public EmailSender(String hostName, String sslSmtpPort, String authUsername, String authPassword, boolean startTLSRequired, boolean sslOnConnect) {
        this.hostName = hostName;
        this.sslSmtpPort = sslSmtpPort;
        this.authUsername = authUsername;
        this.authPassword = authPassword;
        this.startTLSRequired = startTLSRequired;
        this.sslOnConnect = sslOnConnect;
    }

    public EmailSender(String hostName, String sslSmtpPort, String authUsername, String authPassword) {
        this.hostName = hostName;
        this.sslSmtpPort = sslSmtpPort;
        this.authUsername = authUsername;
        this.authPassword = authPassword;

        startTLSRequired = true;
        sslOnConnect = true;
    }

    public void send(EmailTable table) {
        if (table.isValid()) {
            SimpleEmail email = new SimpleEmail();
            email.setContentType(EmailConstants.TEXT_HTML);
            email.setHostName(hostName);
            email.setSslSmtpPort(sslSmtpPort);
            email.setAuthenticator(new DefaultAuthenticator(authUsername, authPassword));
            email.setStartTLSRequired(startTLSRequired);
            email.setSSLOnConnect(sslOnConnect);

            try {
                email.setFrom(table.getSenderAddress(), table.getSenderName());
                email.setSubject(table.getSubject());
                email.setMsg(table.getText());
                table.getRecipients().forEach(e -> {
                    try {
                        email.addTo(e);
                    } catch (EmailException err) {
                        err.printStackTrace();
                    }
                });

                email.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Inválido");
        }
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getSslSmtpPort() {
        return sslSmtpPort;
    }

    public void setSslSmtpPort(String sslSmtpPort) {
        this.sslSmtpPort = sslSmtpPort;
    }

    public String getAuthUsername() {
        return authUsername;
    }

    public void setAuthUsername(String authUsername) {
        this.authUsername = authUsername;
    }

    public String getAuthPassword() {
        return authPassword;
    }

    public void setAuthPassword(String authPassword) {
        this.authPassword = authPassword;
    }

    public boolean isStartTLSRequired() {
        return startTLSRequired;
    }

    public void setStartTLSRequired(boolean startTLSRequired) {
        this.startTLSRequired = startTLSRequired;
    }

    public boolean isSslOnConnect() {
        return sslOnConnect;
    }

    public void setSslOnConnect(boolean sslOnConnect) {
        this.sslOnConnect = sslOnConnect;
    }
}