package com.senseicoder.mastercookbook.util.dialogs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.util.enums.WeekDays;

import java.util.ArrayList;
import java.util.List;

public class ChooseDayDialog {
    private final Activity activity;
    private AlertDialog alertDialog;

    private Button confirmButton;
    private Button cancelButton;

    private ChipGroup chipGroup;
    private Chip saterdayChip;
    private Chip sundayChip;
    private Chip mondayChip;
    private Chip tuesdayChip;
    private Chip wednesdayChip;
    private Chip thursdayChip;
    private Chip fridaydayChip;

    public List<WeekDays> getSelectedDays() {
        return selectedDays;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    private List<WeekDays> selectedDays;

    public ChooseDayDialog(Activity activity) {
        this.activity = activity;
        selectedDays = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.plan_date_picker, null);
        builder.setView(view);

        alertDialog = builder.create();
        confirmButton = view.findViewById(R.id.planDialogConfirmButton);
        cancelButton = view.findViewById(R.id.planDialogCancelButton);
        chipGroup = view.findViewById(R.id.chipGroup);
        saterdayChip = view.findViewById(R.id.saterdayChip);
        sundayChip = view.findViewById(R.id.sundayChip);
        mondayChip = view.findViewById(R.id.mondayChip);
        tuesdayChip = view.findViewById(R.id.tuesdayChip);
        wednesdayChip = view.findViewById(R.id.wednesdayChip);
        thursdayChip = view.findViewById(R.id.thursdayChip);
        fridaydayChip = view.findViewById(R.id.fridayChip);

        setupListeners();

        alertDialog.show();
    }


    public void dismissDialog() {
        alertDialog.dismiss();
    }

    private void setupListeners() {
        cancelButton.setOnClickListener(v -> {
            dismissDialog();
        });
        saterdayChip.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if(isChecked) {
                        selectedDays.add(WeekDays.Saterday);
                    }
                    else
                        selectedDays.remove(WeekDays.Saterday);
                    confirmButton.setEnabled(!chipGroup.getCheckedChipIds().isEmpty());
                }
        );
        sundayChip.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if(isChecked) {
                        selectedDays.add(WeekDays.Sunday);
                    }
                    else
                        selectedDays.remove(WeekDays.Sunday);
                    confirmButton.setEnabled(!chipGroup.getCheckedChipIds().isEmpty());
                }
        );
        mondayChip.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if(isChecked)
                        selectedDays.add(WeekDays.Monday);
                    else
                        selectedDays.remove(WeekDays.Monday);
                    confirmButton.setEnabled(!chipGroup.getCheckedChipIds().isEmpty());
                }
        );
        tuesdayChip.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if(isChecked)
                        selectedDays.add(WeekDays.Tuesday);
                    else
                        selectedDays.remove(WeekDays.Tuesday);
                    confirmButton.setEnabled(!chipGroup.getCheckedChipIds().isEmpty());
                }
        );
        wednesdayChip.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if(isChecked)
                        selectedDays.add(WeekDays.Wednesday);
                    else
                        selectedDays.remove(WeekDays.Wednesday);
                    confirmButton.setEnabled(!chipGroup.getCheckedChipIds().isEmpty());
                }
        );
        thursdayChip.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if(isChecked)
                        selectedDays.add(WeekDays.Thursday);
                    else
                        selectedDays.remove(WeekDays.Thursday);
                    confirmButton.setEnabled(!chipGroup.getCheckedChipIds().isEmpty());
                }
        );
        fridaydayChip.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if(isChecked)
                        selectedDays.add(WeekDays.Friday);
                    else
                        selectedDays.remove(WeekDays.Friday);
                    confirmButton.setEnabled(!chipGroup.getCheckedChipIds().isEmpty());
                }
        );
    }
}
