File "/application/RPA/COMMON/CleanupFiles/UpdatedCleanup.py", line 156, in sendOutlookMail
    asset=obj.get_password(obj.config['asset_email'])
  File "/application/RPA/COMMON/CleanupFiles/UpdatedCleanup.py", line 139, in get_password
    cursor=self.asset_connection.cursor()
AttributeError: 'Obj' object has no attribute 'asset_connection'
