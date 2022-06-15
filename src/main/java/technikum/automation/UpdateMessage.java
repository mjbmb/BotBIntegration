package technikum.automation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class UpdateMessage implements JavaDelegate{
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String businessKey = (String) delegateExecution.getVariable("businessKey");
        if(delegateExecution.getProcessBusinessKey() != null){
            businessKey = delegateExecution.getProcessBusinessKey();
        }
        String produkt = "";
        produkt = (String) delegateExecution.getVariable("produktname");
        StringManager stringManager = new StringManager();
        String produktKey = stringManager.removeNumbers(produkt);
        String produktName = stringManager.removeCharacters(produkt);

        FileAdministrator fileAdministrator = new FileAdministrator();
        fileAdministrator.makeDirInRootDir("/Message");
        fileAdministrator.writeToFile(fileAdministrator.getRootDir()+"/Message/"+businessKey+"--message.txt", produktKey+"="+produktName,true);
    }
}
