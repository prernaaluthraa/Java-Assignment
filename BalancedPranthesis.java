subject = 'Disk Storage Information'
body = f"Disk Storage Before Script Execution:\nTotal: {total_before_str}\nFree: {free_before_str}\n\n" \
       f"Disk Storage After Script Execution:\nTotal: {total_after_str}\nUsed: {used_after_str}\nFree: {free_after_str}\n\n" \
       f"The CSV file '{output_filename}' is created at the below path:\n{os.path.abspath(output_filename)}"
attachments = [output_filename]  # Add any attachments here

send_email(subject, body, sender_email, recipient_emails, cc_emails, attachments)
