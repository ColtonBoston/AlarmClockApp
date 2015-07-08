package com.ColtonBoston.AlarmClock;


import com.codename1.system.NativeLookup;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import userclasses.StateMachine;

public class AlarmClock {
   
    private Form current;

    public void init(Object context) {
        // Pro users - uncomment this code to get crash reports sent to you automatically
        /*Display.getInstance().addEdtErrorHandler(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                evt.consume();
                Log.p("Exception in AppName version " + Display.getInstance().getProperty("AppVersion", "Unknown"));
                Log.p("OS " + Display.getInstance().getPlatformName());
                Log.p("Error " + evt.getSource());
                Log.p("Current Form " + Display.getInstance().getCurrent().getName());
                Log.e((Throwable)evt.getSource());
                Log.sendLog();
            }
        });*/
        
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if(current != null){
            current.show();
            return;
        }
        String currentDate;
        Form hi = new Form("Alarm");
        BorderLayout bl = new BorderLayout();
        hi.setLayout(bl);
        ComponentGroup cg = new ComponentGroup();
        final TextField time = new TextField();
        final Label lblTime = new Label();
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);
        if(hours > 12)
            hours -= 12;
        if(mins < 10)
            currentDate = hours + ":0" + mins;
        else
            currentDate = hours + ":" + mins;
        lblTime.setText(currentDate);
        lblTime.setVerticalAlignment(Label.CENTER);
        hi.addComponent(BorderLayout.CENTER, lblTime);
        hi.show();
        //new StateMachine("/theme");        
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
}
