package technikum.automation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class RechnungErstellen implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String businessKey = (String) delegateExecution.getVariable("businessKey");
        if(delegateExecution.getProcessBusinessKey() != null){
            businessKey = delegateExecution.getProcessBusinessKey();
        }
        FileAdministrator fileAdministrator = new FileAdministrator();
        fileAdministrator.makeDirInRootDir("/Output");
        fileAdministrator.makePdfDeliveryConfirmation(fileAdministrator.getRootDir()+"/Message/"+businessKey+"--message.txt", fileAdministrator.getRootDir()+"/Output/"+businessKey+"--output.pdf", 15);
    }
}
