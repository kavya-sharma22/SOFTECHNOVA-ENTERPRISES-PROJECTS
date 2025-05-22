
class Emsg {

    static String logAlert(String holderName) {
        String emailBody = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; }"
                + ".container { width: 80%; margin: 0 auto; padding: 20px; border: 1px solid #dcdcdc; border-radius: 10px; background-color: #f9f9f9; }"
                + ".header { font-size: 24px; font-weight: bold; margin-bottom: 20px; }"
                + ".content { font-size: 16px; line-height: 1.6; }"
                + ".footer { font-size: 14px; color: #555555; margin-top: 20px; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>Login Notification</div>"
                + "<div class='content'>"
                + "Dear " + holderName + ",<br><br>"
                + "Recently, you logged into Apna Bank. If this was not you, please check your account immediately.<br><br>"
                + "If you have any questions or need further assistance, please do not hesitate to contact our support team.<br><br>"
                + "Best regards,<br>"
                + "Apna Bank"
                + "</div>"
                + "<div class='footer'>"
                + "This is an automated message, please do not reply."
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
        return emailBody;
    }

    static String pinChange(String holderName) {
        String emailBody = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f6f6f6; padding: 20px; }"
                + ".container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }"
                + ".header { font-size: 24px; font-weight: bold; margin-bottom: 20px; color: #333333; }"
                + ".content { font-size: 16px; line-height: 1.6; color: #333333; }"
                + ".footer { font-size: 14px; color: #555555; margin-top: 20px; }"
                + ".footer a { color: #0066cc; text-decoration: none; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>Security Alert: PIN Changed</div>"
                + "<div class='content'>"
                + "Dear " + holderName + ",<br><br>"
                + "We wanted to let you know that your PIN has been successfully changed. If you did not request this change, please contact our support team immediately to secure your account.<br><br>"
                + "For your security, please do not share your PIN with anyone and ensure that you use a strong and unique PIN.<br><br>"
                + "If you have any questions or need further assistance, please do not hesitate to contact our support team.<br><br>"
                + "Best regards,<br>"
                + "Apna Bank"
                + "</div>"
                + "<div class='footer'>"
                + "This is an automated message, please do not reply. If you need assistance, please contact our support team."
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
        return emailBody;
    }

    static String moneyDeposited(String holderName, String amount, String accountNumber, String updatedBalance) {
        String emailBody = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f6f6f6; padding: 20px; }"
                + ".container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }"
                + ".header { font-size: 24px; font-weight: bold; margin-bottom: 20px; color: #333333; }"
                + ".content { font-size: 16px; line-height: 1.6; color: #333333; }"
                + ".footer { font-size: 14px; color: #555555; margin-top: 20px; }"
                + ".footer a { color: #0066cc; text-decoration: none; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>Deposit Confirmation</div>"
                + "<div class='content'>"
                + "Dear " + holderName + ",<br><br>"
                + "We are pleased to inform you that ₹ " + amount + " has been successfully deposited into your account No. " + accountNumber + ".<br><br>"
                + "Your updated balance is ₹ " + updatedBalance + ".<br><br>"
                + "If you have any questions or need further assistance, please do not hesitate to contact our support team.<br><br>"
                + "Best regards,<br>"
                + "Apna Bank"
                + "</div>"
                + "<div class='footer'>"
                + "This is an automated message, please do not reply. If you need assistance, please contact our support team."
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
        return emailBody;
    }
    static String moneyWithdrawn(String holderName, String amount, String accountNumber, String updatedBalance) {
        String emailBody = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f6f6f6; padding: 20px; }"
                + ".container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }"
                + ".header { font-size: 24px; font-weight: bold; margin-bottom: 20px; color: #333333; }"
                + ".content { font-size: 16px; line-height: 1.6; color: #333333; }"
                + ".footer { font-size: 14px; color: #555555; margin-top: 20px; }"
                + ".footer a { color: #0066cc; text-decoration: none; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>Withdrawal Confirmation</div>"
                + "<div class='content'>"
                + "Dear " + holderName + ",<br><br>"
                + "We are writing to inform you that ₹ " + amount + " has been successfully withdrawn from your account No. " + accountNumber + ".<br><br>"
                + "Your updated balance is ₹" + updatedBalance + ".<br><br>"
                + "If you have any questions or need further assistance, please do not hesitate to contact our support team.<br><br>"
                + "Best regards,<br>"
                + "Apna Bank"
                + "</div>"
                + "<div class='footer'>"
                + "This is an automated message, please do not reply. If you need assistance, please contact our support team."
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
        return emailBody;
    }
    static String accountStatement(String holderName, String accountNumber) {
        String emailBody = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f6f6f6; padding: 20px; }"
                + ".container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }"
                + ".header { font-size: 24px; font-weight: bold; margin-bottom: 20px; color: #333333; }"
                + ".content { font-size: 16px; line-height: 1.6; color: #333333; }"
                + ".footer { font-size: 14px; color: #555555; margin-top: 20px; }"
                + ".footer a { color: #0066cc; text-decoration: none; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>Account Statement</div>"
                + "<div class='content'>"
                + "Dear " + holderName + ",<br><br>"
                + "Your account statement for " + accountNumber + " is ready.<br><br>"
                + "Please find your account statement attached to this email.<br><br>"
                + "If you have any questions or need further assistance, please do not hesitate to contact our support team.<br><br>"
                + "Best regards,<br>"
                + "Apna Bank"
                + "</div>"
                + "<div class='footer'>"
                + "This is an automated message, please do not reply. If you need assistance, please contact our support team."
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
        return emailBody;
    }
    static String otpMessage(String otp) {
        String emailBody = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f6f6f6; padding: 20px; }"
                + ".container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }"
                + ".header { font-size: 24px; font-weight: bold; margin-bottom: 20px; color: #333333; }"
                + ".content { font-size: 16px; line-height: 1.6; color: #333333; }"
                + ".footer { font-size: 14px; color: #555555; margin-top: 20px; }"
                + ".footer a { color: #0066cc; text-decoration: none; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>Verification</div>"
                + "<div class='content'>"
                + "Dear User,<br><br>"
                + "Your OTP for verification is: <strong>" + otp + "</strong>.<br><br>"
                + "Please use this OTP to complete the verification process.<br><br>"
                + "If you did not initiate this request, please disregard this email.<br><br>"
                + "Best regards,<br>"
                + "Apna Bank"
                + "</div>"
                + "<div class='footer'>"
                + "This is an automated message, please do not reply. If you need assistance, please contact our support team."
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
        return emailBody;
    }
    static String registrationSuccess(String name, String accountNumber, String cardNumber, String pinNumber) {
        String emailBody = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f6f6f6; padding: 20px; }"
                + ".container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }"
                + ".header { font-size: 24px; font-weight: bold; margin-bottom: 20px; color: #333333; }"
                + ".content { font-size: 16px; line-height: 1.6; color: #333333; }"
                + ".footer { font-size: 14px; color: #555555; margin-top: 20px; }"
                + ".footer a { color: #0066cc; text-decoration: none; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>Successfully Registered</div>"
                + "<div class='content'>"
                + "Dear " + name + ",<br><br>"
                + "Your Bank Account has been successfully created.<br><br>"
                + "Here are your account details:<br>"
                + "Account Number: " + accountNumber + "<br>"
                + "Card Number: " + cardNumber + "<br>"
                + "PIN Number: " + pinNumber + "<br><br>"
                + "If you have any questions or need further assistance, please do not hesitate to contact our support team.<br><br>"
                + "Best regards,<br>"
                + "Apna Bank"
                + "</div>"
                + "<div class='footer'>"
                + "This is an automated message, please do not reply. If you need assistance, please contact our support team."
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
        return emailBody;
    }
    static String moneyTransferSuccess(String recipientName,String acc1,String acc2, double amountTransferred, double newRecipientBalance) {
        String emailBody = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f6f6f6; padding: 20px; }" +
                ".container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }" +
                ".header { font-size: 24px; font-weight: bold; margin-bottom: 20px; color: #333333; }" +
                ".content { font-size: 16px; line-height: 1.6; color: #333333; }" +
                ".footer { font-size: 14px; color: #555555; margin-top: 20px; }" +
                ".footer a { color: #0066cc; text-decoration: none; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<div class='header'>Money Transfer Successful</div>" +
                "<div class='content'>" +
                "Dear " + recipientName + ",<br><br>" +
                "You have successfully  a transfer of ₹ " + String.format("%.2f", amountTransferred) + ".<br>" +
                "Account No : " + acc1 + ".<br>" +
                "From : " + acc2 + ".<br>" +
                "Your new account balance is: ₹ " + String.format("%.2f", newRecipientBalance) + ".<br><br>" +
                "Thank you for using our service.<br><br>" +
                "Best regards,<br>" +
                "Apna Bank Name" +
                "</div>" +
                "<div class='footer'>" +
                "This is an automated message, please do not reply. If you need assistance, please contact our support team." +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";
        return emailBody;
    }

    static String moneyReciverSuccess(String recipientName,String acc1,String acc2, double amountTransferred, double newRecipientBalance) {
        String emailBody = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f6f6f6; padding: 20px; }" +
                ".container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }" +
                ".header { font-size: 24px; font-weight: bold; margin-bottom: 20px; color: #333333; }" +
                ".content { font-size: 16px; line-height: 1.6; color: #333333; }" +
                ".footer { font-size: 14px; color: #555555; margin-top: 20px; }" +
                ".footer a { color: #0066cc; text-decoration: none; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<div class='header'>Money Received Successful</div>" +
                "<div class='content'>" +
                "Dear " + recipientName + ",<br><br>" +
                "You have successfully received of ₹ " + String.format("%.2f", amountTransferred) + ".<br>" +
                "Account No : " + acc1 + ".<br>" +
                "From : " + acc2 + ".<br>" +
                "Your new account balance is: ₹ " + String.format("%.2f", newRecipientBalance) + ".<br><br>" +
                "Thank you for using our service.<br><br>" +
                "Best regards,<br>" +
                "Apna Bank Name" +
                "</div>" +
                "<div class='footer'>" +
                "This is an automated message, please do not reply. If you need assistance, please contact our support team." +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";
        return emailBody;
    }
}
