package com.trust5.odpbuilder;

import com.trust5.odpbuilder.model.Workspace;
import com.trust5.odpbuilder.view.MainFrame;
import com.trust5.odpbuilder.view.WorkspacePicker;

public class App 
{
	private static void createAndShowGUI() {
		try{
        Workspace.initialize(null);
        if(Workspace.getLocation() == null){
            new WorkspacePicker();
        } else{
            new MainFrame();
        }
		}catch (Exception e){
			new WorkspacePicker();
		}


    }
	
    public static void main( String[] args )
    {
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
