import os
import time
import csv
import pandas as pd
import smtplib,ssl
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.application import MIMEApplication

class Obj:
    def __init__(self,CofigPath):
        
        self.config=None
        self.CofigPath=CofigPath
        self.SystemException=None



    def cleanup(obj):
        try:

            rpa_path = "/application/RPA"
            folders_to_check = ["LOGS", "SCREENSHOTS", "ARCHIVE", "OUT"]
            three_days_ago = time.time() - (180 * 24 * 60 * 60)
            log_count = 0
            screenshot_count = 0
            others_count = 0
            size = 0

            output_filename = "/application/RPA/COMMON/CleanupFiles/LOGS/output_" + time.strftime("%Y%m%d-%H%M%S") + ".csv"
            with open(output_filename, 'w', newline='') as csvfile:
                csvwriter = csv.writer(csvfile)
                csvwriter.writerow(["Action", "File Path", "Modification Date"])

                # Get disk storage before running the script
                total_before = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_blocks
                free_before = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_bfree

                # Convert sizes to human-readable format
                total_before_str = f"{total_before / (1024 ** 3):.2f} GB"
                free_before_str = f"{free_before / (1024 ** 3):.2f} GB"

                # Print disk storage information before running the script
                csvwriter.writerow(["Disk Storage Before Script Execution"])
                csvwriter.writerow(["Total", total_before_str])
                csvwriter.writerow(["Free", free_before_str])
                csvwriter.writerow([])  # Empty row for separation

                for root, dirs, files in os.walk(rpa_path):
                    for folder_name in folders_to_check:
                        if folder_name in dirs:
                            folder_path = os.path.join(root, folder_name)
                            csvwriter.writerow(["Checking", folder_path, ""])

                            if folder_name == "LOGS":
                                runlogs_path = os.path.join(folder_path, "RUNLOGS")
                                if os.path.exists(runlogs_path):
                                    for filename in os.listdir(runlogs_path):
                                        file_path = os.path.join(runlogs_path, filename)
                                        if os.path.isfile(file_path) and os.path.getmtime(file_path) < three_days_ago:
                                            mod_time = os.path.getmtime(file_path)
                                            mod_date = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(mod_time))
                                            csvwriter.writerow(["File path:", file_path, mod_date])
                                            log_count += 1
                                            #os.remove(file_path)
                                else:
                                    pass 

                            elif folder_name == "SCREENSHOTS":
                                print(folder_path)
                                for root, dirs, files in os.walk(folder_path, topdown=True):
                                    for file in files:
                                        file_path = os.path.abspath(os.path.join(root, file))
                                        
                                        if os.path.isfile(file_path) and os.path.getmtime(file_path) < three_days_ago:
                                            mod_time = os.path.getmtime(file_path)
                                            mod_date = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(mod_time))
                                            csvwriter.writerow(["File path", file_path, mod_date])
                                            print(file)
                                            screenshot_count += 1
                                            #os.remove(file_path)
                                        
                                        else:
                                            pass

                            else:
                                for filename in os.listdir(folder_path):
                                    file_path = os.path.join(folder_path, filename)
                                    if os.path.isfile(file_path) and os.path.getmtime(file_path) < three_days_ago:
                                        mod_time = os.path.getmtime(file_path)
                                        mod_date = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(mod_time))
                                        csvwriter.writerow(["File path", file_path, mod_date])
                                        others_count += 1
                                        #os.remove(file_path) 

                # Get disk storage after running the script
                total_after = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_blocks
                free_after = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_bfree

                # Calculate used space
                used_before = total_before - free_before
                used_after = total_after - free_after

                # Convert sizes to human-readable format
                total_after_str = f"{total_after / (1024 ** 3):.2f} GB"
                used_after_str = f"{used_after / (1024 ** 3):.2f} GB"
                free_after_str = f"{free_after / (1024 ** 3):.2f} GB"

                # Print disk storage information after running the script
                csvwriter.writerow([])  # Empty row for separation
                csvwriter.writerow(["Disk Storage After Script Execution"])
                csvwriter.writerow(["Total", total_after_str])
                csvwriter.writerow(["Used", used_after_str])
                csvwriter.writerow(["Free", free_after_str])

        except Exception as ex:
            obj.SystemException = "Cleanup Script failed. Please look into it."
            raise Exception(f"error:", str(ex))
                

    def get_password(self,asset_name):
        try:
            query="select USER_ID,PASSWORD from RPA.PYTHON_ASSETS WHERE ASSET_NAME='{}'".format(asset_name.upper())
            cursor=self.asset_connection.cursor()
            result=cursor.execute(query)
            asset=result.fetchall()
            if len(asset)==0:
                raise Exception("BRE: Asset Not Found")
            count=int(asset[0][1].split('?')[1])
            password=asset[0][1].split('?')[0]
            for i in range(count):
                password=base64.b64decode(password)
            return [asset[0][0],password.decode('utf-8')]
        except:
            raise
    #email part
    def sendOutlookMail(obj):
        port = int(obj.config['ExchangePort'])  # For SSL
        SERVER = obj.config['ExchangeServer']
        msg=MIMEMultipart('alternative')
        asset=obj.get_password(obj.config['asset_email'])
        frm=asset[0]
        disclamerfile=open(obj.config['COMMON_REQUIRED']+"Disclaimer.htm",'r')
        disclamer=disclamerfile.read()
        disclamerfile.close()
        to=obj.config['ExchEmailTo'].split(";")
        cc=obj.config['ExchEmailCc'].split(";")
        msg['From']=frm
        msg['To']=','.join(to)
        msg['Cc']=','.join(cc)
        to+=cc
        BODY=''
        if obj.SystemException:
            msg['Subject']=obj.config['PROCESSNAME']+"FAILED"
            BODY+=obj.SystemException
        else :
            BODY+=obj.config['ExchEmailBodySuccess']+ "Disk Storage Before Script Execution: Total:" + total_before_str

                
        BODY+=disclamer
        msg.attach(MIMEText(BODY,'plain'))
        msg.attach(MIMEText(BODY,'html'))
        context = ssl.create_default_context()
        server=smtplib.SMTP(SERVER,port)
        server.starttls(context=context)
        #server.login(frm,asset[1])
        server.sendmail(frm,to,msg.as_string())
        server.quit()

def main():
    print("Process Started Successfully!")
    obj=Obj("/application/RPA/COMMON/CleanupFiles/Config.csv")

    
    try:
        #call functions here
    finally:
        mail.sendOutlookMail(obj)
    print("Execution Completed Successfully!")

if __name__=="__main__":
    display=Display(visible=0,size=(1366,768))
    display.start()
    main()
    display.stop()
