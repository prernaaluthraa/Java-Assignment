import os
import time
import csv

# ... (existing code)

# Get disk storage before running the script
total_before = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_blocks
free_before = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_bfree

# ... (existing code)

output_filename = "/application/RPA/COMMON/CleanupFiles/LOGS/output_" + time.strftime("%Y%m%d-%H%M%S") + ".csv"
with open(output_filename, 'w', newline='') as csvfile:
    csvwriter = csv.writer(csvfile)
    csvwriter.writerow(["Action", "File Path", "Modification Date"])

    # ... (existing code)

    # Get disk storage after running the script
    total_after = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_blocks
    free_after = os.statvfs("/application/RPA").f_frsize * os.statvfs("/application/RPA").f_bfree

    # Calculate used space
    used_before = total_before - free_before
    used_after = total_after - free_after

    # Convert sizes to human-readable format
    total_before_str = f"{total_before / (1024 ** 3):.2f} GB"
    used_before_str = f"{used_before / (1024 ** 3):.2f} GB"
    free_before_str = f"{free_before / (1024 ** 3):.2f} GB"

    total_after_str = f"{total_after / (1024 ** 3):.2f} GB"
    used_after_str = f"{used_after / (1024 ** 3):.2f} GB"
    free_after_str = f"{free_after / (1024 ** 3):.2f} GB"

    # Write disk storage information in the CSV file
    csvwriter.writerow(["Disk Storage Before Script Execution"])
    csvwriter.writerow(["Total", total_before_str])
    csvwriter.writerow(["Used", used_before_str])
    csvwriter.writerow(["Free", free_before_str])
    csvwriter.writerow([])  # Empty row for separation
    csvwriter.writerow(["Disk Storage After Script Execution"])
    csvwriter.writerow(["Total", total_after_str])
    csvwriter.writerow(["Used", used_after_str])
    csvwriter.writerow(["Free", free_after_str])

    # ... (existing code)
