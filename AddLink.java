import os
import time
import csv

rpa_path = "/application/RPA"
folders_to_check = ["LOGS", "SCREENSHOTS", "ARCHIVE", "OUT"]
three_days_ago = time.time() - (180 * 24 * 60 * 60)
log_count=0
screenshot_count=0
others_count=0
size=0

output_filename = "/application/RPA/COMMON/CleanupFiles/LOGS/output_" + time.strftime("%Y%m%d-%H%M%S") + ".csv"
with open(output_filename, 'w', newline='') as csvfile:
    csvwriter = csv.writer(csvfile)
    csvwriter.writerow(["Action", "File Path", "Modification Date"])

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
                                log_count+=1

                                #os.remove(file_path)
                    else:
                        pass 

                            

                elif folder_name== "SCREENSHOTS":
                    print(folder_path)
                    for root,dirs,files in os.walk(folder_path,topdown=True):
                        for file in files:
                            file_path=os.path.abspath(os.path.join(root, file))
                            
                            if os.path.isfile(file_path) and os.path.getmtime(file_path) < three_days_ago:
                                mod_time = os.path.getmtime(file_path)
                                mod_date = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(mod_time))
                                csvwriter.writerow(["File path", file_path, mod_date])
                                print(file)
                                screenshot_count+=1
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
                            others_count+=1

                            #os.remove(file_path) 
