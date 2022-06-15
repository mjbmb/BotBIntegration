package technikum.automation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.IOException;


public class AuftragSender implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws IOException {
        String businessKey = delegateExecution.getProcessBusinessKey();

        String value = (String) delegateExecution.getVariable("auftrag");

        delegateExecution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("AuftragMessage").setVariable("auftrag", value).setVariable("businessKey", businessKey).correlate();

        FileAdministrator fileAdministrator = new FileAdministrator();
        fileAdministrator.makeDirInRootDir("/Auftraege");
        fileAdministrator.writeToFile(fileAdministrator.getRootDir()+"/Auftraege"+"/"+businessKey+"--Auftrag.txt",value,false);
        fileAdministrator.convertTxtToPdf(fileAdministrator.getRootDir()+"/Auftraege"+"/"+businessKey+"--Auftrag.txt", fileAdministrator.getRootDir()+"/Auftraege"+"/"+businessKey+"--Auftrag.pdf", 15);
        fileAdministrator.deleteFile(fileAdministrator.getRootDir()+"/Auftraege"+"/"+businessKey+"--Auftrag.txt");
        fileAdministrator.deleteFile(fileAdministrator.getRootDir()+"/Message"+"/"+businessKey+"--message.txt");
    }
}

