package mx.edu.itchetumal.concursomovil8;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

//import java.text.DateFormat;


/**
 * Created by PC-04 on 24/11/2015.
 */
public class TimePick  extends DialogFragment {
    TimePickerDialog.OnTimeSetListener ond;



    public TimePick() {
    }

    public void setCallBack(TimePickerDialog.OnTimeSetListener on) {
        ond = on;
    }
    private int hora,min;
    private String apm;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
       hora = args.getInt("hora");
       min = args.getInt("min");
       apm = args.getString("AM_PM");

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), ond, hora, min,false);
    }




}
