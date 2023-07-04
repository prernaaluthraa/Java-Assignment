import smtplib,ssl
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.application import MIMEApplication

def sendOutlookMail(obj):
    port = int(obj.config['ExchangePort'])  # For SSL
    SERVER = obj.config['ExchangeServer']
    msg=MIMEMultipart('alternative')
    # asset=obj.get_password(obj.config['asset_email'])
    # frm=asset[0]
    # msg=MIMEMultipart('alternative')
    asset=obj.get_password(obj.config['asset_email'])
    frm=asset[0]
    #frm=r'svcs_rpa_prod_bot9@mortgagefamily.com'
    # pwd= "hvtaJ$m7"
    # frm=obj.config['mailId']
    disclamerfile= open(obj.config['SIG'],'r')
    disclamer=disclamerfile.read()
    disclamerfile.close()
    to=obj.config['ExchEmailTo'].split(";")
    cc=obj.config['ExchEmailCc'].split(";")
    msg['From']=frm
    msg['To']=','.join(to)
    msg['Cc']=','.join(cc)
    BODY=''
    to+=cc
    if obj.SystemException:
        msg['Subject']=obj.config['BU']+"-"+obj.config['PROCESSNAME']+" "+obj.SystemException+str(obj.metrics.StartTime)[:-7].replace("-","/")
    elif obj.BusinessRuleException:
        msg['Subject']=obj.config['BU']+"-"+obj.config['PROCESSNAME']+" Failed!"+str(obj.metrics.StartTime)[:-7].replace("-","/")
        BODY+=obj.config['ExchEmailBodyBRE']
    elif obj.TransactionData is None:
        msg['Subject']=obj.config['BU']+"-"+obj.config['PROCESSNAME']+" Input File is missing"+str(obj.metrics.StartTime)[:-7].replace("-","/")
        BODY+=obj.config['ExchEmailBodyNOFILE']
    elif len(obj.TransactionData)==0:
        msg['Subject']=obj.config['BU']+"-"+obj.config['PROCESSNAME']+" No Valid Data found in the Input File"+str(obj.metrics.StartTime)[:-7].replace("-","/")
        BODY+=obj.config['ExchEmailBodyNODATA']
    elif obj.metrics.JobStatus!=1:
        msg['Subject']=obj.config['BU']+"-"+obj.config['PROCESSNAME']+" "+str(obj.metrics.StartTime)[:-7].replace("-","/")
        BODY+=obj.config['ExchEmailBodySuccess']
        STATS=obj.config['ExchEmailBodyReport'].replace('XXXXX',str(obj.totalLoans)).replace('YYYYY',str(obj.ProcessedLoans)).replace('ZZZZZ',str(obj.metrics.SuccessCount))
        RATIO=obj.config['ExchEmailBodyTime'].replace('AAAAA',str(obj.metrics.StartTime)[:-7].replace("-","/")).replace('BBBBB',str(obj.metrics.EndTime)[:-7].replace("-","/"))
        BODY+=STATS+RATIO
        with open(obj.outputfile,'rb') as atch:
            part=MIMEApplication(atch.read(),Name=obj.outputfile)
        part['Content-Disposition']='attachment;filename="{}"'.format(obj.outputfile.split('/')[-1])
        msg.attach(part)
    BODY+=disclamer
    msg.attach(MIMEText(BODY,'plain'))
    msg.attach(MIMEText(BODY,'html'))
    context = ssl.create_default_context()
    server=smtplib.SMTP(SERVER,port)
    server.starttls(context=context)
    #server.login(frm,asset[1])
    # server.login(frm,obj.config['mailPass'])
    server.sendmail(frm,to,msg.as_string())
    server.quit()
