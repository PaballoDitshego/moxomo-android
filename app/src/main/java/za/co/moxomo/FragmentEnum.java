package za.co.moxomo;

import lombok.Getter;

@Getter
public enum FragmentEnum {

    CREATE_ALERT(R.id.createAlertFragment, "Create Alert"),
    VIEW_ALERT(R.id.viewAlertsFragment, "Alerts"),
    EDIT_ALERT(R.id.viewAlertsFragment, "Edit Alert");

    private int fragmentId;
    private String title;

    FragmentEnum(int fragmentId, String title){
        this.fragmentId=fragmentId;
        this.title =title;

    }
}
