package com.example.therdsak.yeutsen.PagerActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class ChoiceDialogFragment extends DialogFragment{
    public static final String DATA = "items";

    public static final String SELECTED = "selected";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Resources res = getActivity().getResources();
        Bundle bundle = getArguments();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        dialog.setTitle("Please Select");
        //    dialog.setPositiveButton("Cancel", new PositiveButtonClickListener());

        List<String> list = (List<String>)bundle.get(DATA);
        int position = bundle.getInt(SELECTED);

        CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        dialog.setSingleChoiceItems(cs, position, selectItemListener);

        return dialog.create();
    }

//    class PositiveButtonClickListener implements DialogInterface.OnClickListener
//    {
//        @Override
//        public void onClick(DialogInterface dialog, int which)
//        {
//            dialog.dismiss();
//        }
//    }

    DialogInterface.OnClickListener selectItemListener = new DialogInterface.OnClickListener()
    {

        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            // process
            //which means position
            Toast.makeText(getActivity(),dialog.toString(),Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }

    };
}


