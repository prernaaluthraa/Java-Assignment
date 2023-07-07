def send_outlook_mail(self):
    try:
        port = int(self.config['ExchangePort'])
        SERVER = self.config['ExchangeServer']
        msg = MIMEMultipart('alternative')
        asset = self.get_password(self.config['asset_email'])
        frm = asset[0]
        disclamerfile = open(self.config['COMMON_REQUIRED'] + "Disclaimer.htm", 'r')
        disclamer = disclamerfile.read()
        disclamerfile.close()
        to = self.config['ExchEmailTo'].split(";")
        cc = self.config['ExchEmailCc'].split(";")
        msg['From'] = frm
        msg['To'] = ','.join(to)
        msg['Cc'] = ','.join(cc)
        to += cc
        BODY = ''
        if self.SystemException:
            msg['Subject'] = self.config['PROCESSNAME'] + " FAILED"
            BODY += self.SystemException
        else:
            # Get disk storage details before script execution
            total_before = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_blocks
            free_before = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_bfree
            total_before_str = f"{total_before / (1024 ** 3):.2f} GB"
            free_before_str = f"{free_before / (1024 ** 3):.2f} GB"

            BODY += self.config['ExchEmailBodySuccess'] + " Disk Storage Before Script Execution: Total: " + total_before_str

            # Calculate disk storage details after script execution
            total_after = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_blocks
            free_after = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_bfree
            total_after_str = f"{total_after / (1024 ** 3):.2f} GB"
            used_after_str = f"{(total_after - free_after) / (1024 ** 3):.2f} GB"
            free_after_str = f"{free_after / (1024 ** 3):.2f} GB"

            # Add disk storage details to the body
            BODY += "\nDisk Storage After Script Execution:"
            BODY += f"\nTotal: {total_after_str}"
            BODY += f"\nUsed: {used_after_str}"
            BODY += f"\nFree: {free_after_str}"

            # Add disk storage details before script execution
            BODY += "\n\nDisk Storage Before Script Execution:"
            BODY += f"\nTotal: {total_before_str}"
            BODY += f"\nFree: {free_before_str}"

            output_file_path = "/application/RPA/COMMON/CleanupFiles/LOGS/output_" + time.strftime("%Y%m%d-%H%M%S") + ".csv"
            BODY += "Output File Path: " + output_file_path

        BODY += disclamer
        msg.attach(MIMEText(BODY, 'plain'))
        msg.attach(MIMEText(BODY, 'html'))
        context = ssl.create_default_context()
        server = smtplib.SMTP(SERVER, port)
        server.starttls(context=context)
        # server.login(frm, asset[1])
        server.sendmail(frm, to, msg.as_string())
        server.quit()
    except Exception as ex:
        # Handle exception if sending email fails
        print("Error sending email:", str(ex))
