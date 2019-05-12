package za.co.moxomo.dagger;


import javax.inject.Singleton;

import dagger.Component;
import za.co.moxomo.activities.AlertActivity;
import za.co.moxomo.activities.MainActivity;
import za.co.moxomo.fragments.CreateAlertFragment;
import za.co.moxomo.fragments.EditAlertFragment;
import za.co.moxomo.fragments.HomePageFragment;
import za.co.moxomo.fragments.NotificationFragment;
import za.co.moxomo.fragments.ViewAlertsFragment;
import za.co.moxomo.fcm.FCMListenerService;

@Component(modules = {InjectionModule.class})
@Singleton
public interface InjectionComponent {

    void inject(MainActivity activity);

    void inject(AlertActivity alertActivity);

    void inject(HomePageFragment homePageFragment);

    void inject(NotificationFragment notificationFragment);

    void inject(CreateAlertFragment createAlertFragment);

    void inject(ViewAlertsFragment viewAlertsFragment);

    void inject(FCMListenerService fcmListenerService);

    void inject (EditAlertFragment editAlertFragment);


}
